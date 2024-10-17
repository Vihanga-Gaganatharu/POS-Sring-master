package lk.ijse.possringmaster.util;

import lk.ijse.possringmaster.dto.CustomerDto;
import lk.ijse.possringmaster.dto.ItemDto;
import lk.ijse.possringmaster.entity.CustomerEntity;
import lk.ijse.possringmaster.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //Customer matters
    public CustomerEntity convertToCustomerEntity(CustomerDto dto){
        return modelMapper.map(dto, CustomerEntity.class);
    }
    public CustomerDto convertToCustomerDto(CustomerEntity entity){
        return modelMapper.map(entity, CustomerDto.class);
    }
    public List<CustomerDto> convertToCustomerDtoList(List<CustomerEntity> entities){
     return modelMapper.map(entities, new TypeToken<List<CustomerDto>>(){}.getType());
    }
    //matters of Item
    public ItemDto convertToItemDTO(ItemEntity item) {
        return modelMapper.map(item, ItemDto.class);
    }
    public ItemEntity convertToItemEntity(ItemDto dto) {
        return modelMapper.map(dto, ItemEntity.class);
    }
    public List<ItemDto> convertToItemDTOList(List<ItemEntity> items) {
        return modelMapper.map(items, new TypeToken<List<ItemDto>>() {}.getType());
    }
    //matters of Order
    public OrderDto convertToOrderDTO(OrderEntity order) {
        return modelMapper.map(order, OrderDto.class);
    }
    public OrderEntity convertToOrderEntity(OrderDto dto) {
        return modelMapper.map(dto, OrderEntity.class);
    }
    public List<OrderDto> convertToOrderDTOList(List<OrderEntity> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderDto>>() {}.getType());
    }

    public OrderDetailsEntity convertToOrderDetailEntity(OrderDetailDto dto) {
        return modelMapper.map(dto, OrderDetailsEntity.class);
    }

    public OrderDetailDto convertToOrderDetailDto(OrderEntity entity) {
        return modelMapper.map(entity, OrderDetailDto.class);
    }


    public List<OrderDetailDto> convertToOrderDetailDTOList(List<OrderDetailsEntity> orders) {
        return modelMapper.map(orders, new TypeToken<List<OrderDetailDto>>() {}.getType());
    }
}
