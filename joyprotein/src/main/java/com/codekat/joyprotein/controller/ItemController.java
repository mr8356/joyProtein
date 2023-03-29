package com.codekat.joyprotein.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.domain.items.Product;
import com.codekat.joyprotein.service.ItemService;
import com.codekat.joyprotein.service.OrderService;
import com.codekat.joyprotein.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@SessionAttributes("memberId")
public class ItemController {
    @Autowired private ItemService itemService;
    @Autowired private ProductService productService;
    @Autowired private OrderService orderService;

    @GetMapping("/protein")
    public String protein(Model model) {
        List <Product> products = productService.getByCategory("protein");
        model.addAttribute("items", products);
        return "items/protein";
    }

    @GetMapping(value="/protein/buy")
    public String getMethodName(@RequestParam Long id, Model model) {
        Product product = productService.findOne(id);
        model.addAttribute("product", product);
        return "items/proteinBuy";
    }

    /**
     * un do !!!!!!!
     * @param proteinDTO
     * @return
     * @throws Exception
     */
    @PostMapping(value="/protein/order")
    public String orderProtein(@ModelAttribute("memberId") Long memberId, ProteinOrderDTO proteinDTO) throws Exception {
        Item item = itemService.takeProtein(proteinDTO.getId(), proteinDTO.getWeight(), proteinDTO.getTasteCode());
        orderService.orderItem(memberId, item.getId(), proteinDTO.getQuantity());
        return "redirect:/orders";
    }

    @PostMapping(value="/snack/order")
    public String orderSnack(@ModelAttribute("memberId") Long memberId, SnackOrderDTO snackOrderDTO) throws Exception {
        Item item = itemService.takeProtein(snackOrderDTO.getId(), snackOrderDTO.getQuantity(), snackOrderDTO.getTasteCode());
        orderService.orderItem(memberId, item.getId(), snackOrderDTO.getQuantity());
        return "redirect:/orders";
    }

    @PostMapping(value="/amino/order")
    public String orderAmino(@ModelAttribute("memberId") Long memberId, AminoOrderDTO aminoOrderDTO) throws Exception {
        Item item = itemService.takeAmino(aminoOrderDTO.getId(), aminoOrderDTO.getWeight(), aminoOrderDTO.getTasteCode());
        orderService.orderItem(memberId, item.getId(), aminoOrderDTO.getQuantity());
        return "redirect:/orders";
    }
    @PostMapping(value="/vitamin/order")
    public String orderVitamin(@ModelAttribute("memberId") Long memberId, VitaminOrderDTO VitaminOrderDTO) throws Exception {
        Item item = itemService.takeVitamin(VitaminOrderDTO.getId(), VitaminOrderDTO.getUnits());
        orderService.orderItem(memberId, item.getId(), VitaminOrderDTO.getQuantity());
        return "redirect:/orders";
    }

}
    

