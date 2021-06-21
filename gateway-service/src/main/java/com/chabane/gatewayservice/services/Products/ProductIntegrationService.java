package com.chabane.gatewayservice.services.Products;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.Collection;

public class ProductIntegrationService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "stubProduct")
    public Observable<Collection<Product>> getCategoryProducts(final int categoryId, final String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        return Observable.create(new Observable.OnSubscribe<Collection<Product>>() {
            @Override
            public void call(Subscriber<? super Collection<Product>> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext(restTemplate
                                .exchange("http://products-service/category/products/{id}"
                                        , HttpMethod.GET, request, ProductResource.class, categoryId)
                                .getBody().getContent());
                        observer.onCompleted();
                    }
                } catch (Throwable e) {
                    observer.onError(e);
                }

            }
        });
    }

    private Product stubProduct(final String productId, final String token) {
        Product stub = new Product();
        stub.setId(1L);
        stub.setName("Service not available!!!");
        return stub;
    }
}
