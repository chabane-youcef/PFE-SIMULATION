package com.supermarket.clients.dao;

import com.supermarket.clients.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {

    Client findById(int id);
    Client findByEmail(String email);
    void deleteById(int id);
}
