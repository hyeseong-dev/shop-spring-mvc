package com.shop.shopping.repository;

import com.shop.shopping.constant.ItemSellStatus;
import com.shop.shopping.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations={"classpath:application-test.properties"})
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("새 상품 저장 시 필요한 속성들이 정확히 저장되어야 함")
    public void whenNewItemIsSaved_thenItShouldHaveCorrectProperties(){
        // Given
        LocalDateTime now = LocalDateTime.now();
        String itemNm = "테스트상품";
        int price = 10000;
        String itemDetail = "테스트 상품 상세 설명";
        ItemSellStatus itemSellStatus = ItemSellStatus.SELL;
        int itemStockNumber = 100;
        Item item = createTestItem(itemNm, price, itemDetail, ItemSellStatus.SELL, itemStockNumber, now, now);

        // When
        Item savedItem = itemRepository.save(item);

        // Then
        assertNotNull(savedItem, "저장된 상품은 null이 아니어야 합니다.");
        assertEquals(itemNm, savedItem.getItemNm(), "상품명이 올바르게 저장되어야 합니다.");
        assertEquals(price, savedItem.getPrice(), "상품 가격이 올바르게 저장되어야 합니다.");
        assertEquals(itemDetail, savedItem.getItemDetail(), "상품 상세 설명이 올바르게 저장되어야 합니다.");
        assertEquals(itemSellStatus, savedItem.getItemSellStatus(), "상품 판매 상태가 올바르게 저장되어야 합니다.");
        assertEquals(itemStockNumber, savedItem.getStockNumber(), "상품 재고 수가 올바르게 저장되어야 합니다.");
        assertTrue(savedItem.getRegTime().isEqual(now) && savedItem.getUpdateTime().isEqual(now), "상품 등록 시간과 수정 시간이 올바르게 저장되어야 합니다.");
    }

    private Item createTestItem(String itemNm, int price, String itemDetail, ItemSellStatus itemSellStatus, int stockNumber, LocalDateTime regTime, LocalDateTime updateTime) {
        Item item = new Item();
        item.setItemNm(itemNm);
        item.setPrice(price);
        item.setItemDetail(itemDetail);
        item.setItemSellStatus(itemSellStatus);
        item.setStockNumber(stockNumber);
        item.setRegTime(regTime);
        item.setUpdateTime(updateTime);
        return item;
    }
}