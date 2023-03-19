package com.codekat.joyprotein.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.Cart;
import com.codekat.joyprotein.domain.OrderItem;
import com.codekat.joyprotein.repository.MemberRepository;
import com.codekat.joyprotein.repository.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;

    public void addItemToCart(Long memberId, OrderItem orderItem){
        Cart cart = memberRepository.findOne(memberId).getCart();
        // OrderItem orderItem = orderItemRepository.findOne(OrderItemId);
        cart.addOrderItem(orderItem);
    }

    public void removeItemFromCart(Long memberId, Long OrderItemId){
        Cart cart = memberRepository.findOne(OrderItemId).getCart();
        OrderItem orderItem = orderItemRepository.findOne(OrderItemId);
        cart.getOrderItems().remove(orderItem);
        orderItemRepository.remove(orderItem);
    }
}
