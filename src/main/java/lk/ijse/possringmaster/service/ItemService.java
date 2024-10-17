package lk.ijse.possringmaster.service;

import lk.ijse.possringmaster.customObj.ItemResponse;
import lk.ijse.possringmaster.dto.ItemDto;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDto itemDto);
    void updateItem(ItemDto itemDto);
    void deleteItem(String itemDto);
    ItemResponse getSelectedItem(String itemId);
    List<ItemDto> getAllItem();
}
