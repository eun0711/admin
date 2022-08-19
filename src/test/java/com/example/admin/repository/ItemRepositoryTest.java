package com.example.admin.repository;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends AdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void creat(){
        Item item = new Item();

        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("쌈성 노트북");

        Item newItem = itemRepository.save(item);

        //Assert.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        item.ifPresent(i->{
            System.out.println(i);
        });
    }
}
