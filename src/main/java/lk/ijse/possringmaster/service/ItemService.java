package lk.ijse.possringmaster.service;

import lk.ijse.possringmaster.customObj.ItemResponse;
import lk.ijse.possringmaster.dto.ItemDto;

import java.util.List;

public interface ItemService {
    String saveItem(ItemDto itemDTO);
    void updateItem(String id, ItemDto itemDTO);
    void deleteItem(String id);
    ItemResponse getItemById(String id);
    List<ItemDto> getAllItems();
}
