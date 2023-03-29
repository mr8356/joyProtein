package com.codekat.joyprotein.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mapping.AccessOptions.GetOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codekat.joyprotein.domain.items.Amino;
import com.codekat.joyprotein.domain.items.Item;
import com.codekat.joyprotein.domain.items.Product;
import com.codekat.joyprotein.domain.items.Protein;
import com.codekat.joyprotein.domain.items.Snack;
import com.codekat.joyprotein.domain.items.Vitamin;
import com.codekat.joyprotein.repository.ItemRepository;
import com.codekat.joyprotein.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long saveItem(Item item){
        this.itemRepository.save(item);
        item.getProduct().addItem(item);
        return item.getId();
    }

    @Transactional
    public void updateItem(Long itemId, int price, int stockQuantity){
        Item item = this.itemRepository.findOne(itemId);
        // item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
        // item.setImgUrl(imgUrl);
    }

    public Item takeProtein(Long productId, int weight, String tasteCode) throws Exception{
        Product product = productRepository.findById(productId);
        Item result;
        try {
            result = product.getItems().stream()
            .filter(item -> item instanceof Protein && ((Protein) item).getWeight() == weight && ((Protein) item).getTasteCode().equals(tasteCode))
            .collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            throw new Exception("no such Protein option");
        }
        return result;
    }
    public Item takeSnack(Long productId, int option, String tasteCode) throws Exception{
        Product product = productRepository.findById(productId);
        Item result;
        try {
            result = product.getItems().stream()
            .filter(item -> item instanceof Snack && ((Snack) item).getOption() == option && ((Snack) item).getTasteCode().equals(tasteCode))
            .collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            throw new Exception("no such Snack option");
        }
        return result;
    }
    public Item takeAmino(Long productId, int weight, String tasteCode) throws Exception{
        Product product = productRepository.findById(productId);
        Item result;
        try {
            result = product.getItems().stream()
            .filter(item -> item instanceof Amino && ((Amino) item).getWeight() == weight && ((Amino) item).getTasteCode().equals(tasteCode))
            .collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            throw new Exception("no such option");
        }
        return result;
    }

    public Item takeVitamin(Long productId, int units) throws Exception{
        Product product = productRepository.findById(productId);
        Item result;
        try {
            result = product.getItems().stream()
            .filter(item -> item instanceof Vitamin && ((Vitamin) item).getUnits() == units)
            .collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            throw new Exception("no such Vitamin option");
        }
        return result;
    }
    public Item findOne(Long id){
        return this.itemRepository.findOne(id);
    }

    public List<Item> findItems(){
        return this.itemRepository.findAll();
    }
}
