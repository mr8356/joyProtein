package com.codekat.joyprotein.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.codekat.joyprotein.domain.*;
import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.service.ItemService;
import com.codekat.joyprotein.service.MemberService;
import com.codekat.joyprotein.service.OrderItemService;
import com.codekat.joyprotein.service.OrderService;

@Controller
@SessionAttributes("memberId")
public class CartController {
    @Autowired private MemberService memberService;
    @Autowired private ItemService itemService;
    @Autowired private OrderService orderService;
    @Autowired private OrderItemService orderItemService;
    
    @GetMapping("/cart")
    public String showCart(Model model, @ModelAttribute("memberId") Long memberId) throws Exception {
        Member member = memberService.findOne(memberId);
        List<CartItemDTO> cartItems = member.getCart().getOrderItems().stream()
        .map(orderItem -> new CartItemDTO(orderItem.getId(),orderItem.getName(),orderItem.getItem().getProduct().getImgUrl(), orderItem.getItem().getPrice(), orderItem.getQuantity()))
        .collect(Collectors.toList());
        model.addAttribute("items", cartItems);
        return "cart";
    }

    @GetMapping("/cart/order")
    public String orderFromCart(@ModelAttribute("memberId") Long memberId,Model model) throws Exception{
        orderService.orderFromCart(memberId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/protein/add")
    public String addProteinToCart(@ModelAttribute("memberId") Long memberId, ProteinOrderDTO proteinDTO) throws Exception{
        Item item = itemService.takeProtein(proteinDTO.getId(), proteinDTO.getWeight(), proteinDTO.getTasteCode());
        OrderItem orderItem = orderItemService.addItemToCart(memberId, item, proteinDTO.getQuantity());
        return "redirect:/cart";
    }

    @PostMapping(value="/cart/snack/add")
    public String addSnackToCart(@ModelAttribute("memberId") Long memberId, SnackOrderDTO snackOrderDTO) throws Exception {
        Item item = itemService.takeProtein(snackOrderDTO.getId(), snackOrderDTO.getQuantity(), snackOrderDTO.getTasteCode());
        OrderItem orderItem = orderItemService.addItemToCart(memberId, item, snackOrderDTO.getQuantity());
        return "redirect:/cart";
    }

    @PostMapping(value="/cart/amino/add")
    public String addAminoToCart(@ModelAttribute("memberId") Long memberId, AminoOrderDTO aminoOrderDTO) throws Exception {
        Item item = itemService.takeAmino(aminoOrderDTO.getId(), aminoOrderDTO.getWeight(), aminoOrderDTO.getTasteCode());
        OrderItem orderItem = orderItemService.addItemToCart(memberId, item, aminoOrderDTO.getQuantity());
        return "redirect:/cart";
    }
    @PostMapping(value="/cart/vitamin/add")
    public String addVitaminToCart(@ModelAttribute("memberId") Long memberId, VitaminOrderDTO vitaminOrderDTO) throws Exception {
        Item item = itemService.takeVitamin(vitaminOrderDTO.getId(), vitaminOrderDTO.getUnits());
        OrderItem orderItem = orderItemService.addItemToCart(memberId, item, vitaminOrderDTO.getQuantity());
        return "redirect:/cart";
    }
}
