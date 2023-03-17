package com.codekat.joyprotein.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.codekat.joyprotein.domain.OrderItem;

@Repository
public class OrderItemRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(OrderItem orderItem){
        em.persist(orderItem);
    }

    public void remove(OrderItem orderItem){
        em.remove(orderItem);
    }

    public OrderItem findOne(Long id){
        return em.find(OrderItem.class, id);
    }
}