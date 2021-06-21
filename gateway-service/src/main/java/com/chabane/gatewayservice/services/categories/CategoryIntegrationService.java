package com.chabane.gatewayservice.services.categories;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

public class CategoryIntegrationService {
    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "stubCategory")
    public Observable<Category> getCategory(final  String categoryId, final String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        return Observable.create(new Observable.OnSubscribe<Category>(){
            @Override
            public void call(Subscriber<? super Category> observer) {
                try {
                    if (!observer.isUnsubscribed()) {

                        observer.onNext(restTemplate.exchange("http://category-service/categories/{id}",
                                HttpMethod.GET, request, Category.class, categoryId).getBody());
                        observer.onCompleted();
                    }
                } catch (Throwable e) {
                    observer.onError(e);
                }

            }
        });
    }

    private Category stubCategory(final String CategoryId, final String token) {
        Category stub = new Category();
        stub.setId(1L);
        stub.setTitle("Service not available!!!");
        return stub;
    }
}
