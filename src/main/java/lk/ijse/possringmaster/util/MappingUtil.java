package lk.ijse.possringmaster.util;

import lk.ijse.possringmaster.dto.CustomerDto;
import lk.ijse.possringmaster.dto.ItemDto;
import lk.ijse.possringmaster.dto.OrderDto;
import lk.ijse.possringmaster.dto.OrderDetailDto;
import lk.ijse.possringmaster.entity.CustomerEntity;
import lk.ijse.possringmaster.entity.ItemEntity;
import lk.ijse.possringmaster.entity.OrderDetailsEntity;
import lk.ijse.possringmaster.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MappingUtil {
    @Autowired
    private ModelMapper modelMapper;

    //mapping by customer entity & dto
    public CustomerDto convertToCustomerDTO(CustomerEntity entity){
        return modelMapper.map(entity, CustomerDto.class);
    }

    public CustomerEntity convertToCustomerEntity(CustomerDto dto){
        return modelMapper.map(dto, CustomerEntity.class);
    }

    public List<CustomerDto> convertToCustomerDTOList(List<CustomerEntity> customerEntities) {
        List<CustomerDto> customerDTOs = new ArrayList<>();

        for (CustomerEntity entity : customerEntities) {
            CustomerDto dto = new CustomerDto();
            dto.setCustomerId(entity.getCustomerId());
            dto.setName(entity.getName());
            dto.setAddress(entity.getAddress());
            dto.setEmail(entity.getEmail());
            dto.setMobile(entity.getMobile());
            dto.setLastUpdatedAt(entity.getLastUpdatedAt());
            customerDTOs.add(dto);
        }
        return customerDTOs;
    }


    //mapping by item entity & dto
    public ItemDto convertToItemDTO(ItemEntity entity){
        return modelMapper.map(entity, ItemDto.class);
    }

    public ItemEntity convertToItemEntity(ItemDto dto){
        return modelMapper.map(dto, ItemEntity.class);
    }

    public List<ItemDto> convertToItemDTOList(List<ItemEntity> entities){
        List<ItemDto> itemDTOS = new ArrayList<>();

        for (ItemEntity entity : entities) {
            ItemDto dto = new ItemDto();
            dto.setItemCode(entity.getItemCode());
            dto.setCategory(entity.getCategory());
            dto.setUnitPrice(entity.getUnitPrice());
            dto.setQtyOnHand(entity.getQtyOnHand());
            dto.setRegisterDate(entity.getRegisterDate());
            dto.setExpireDate(entity.getExpireDate());
            itemDTOS.add(dto);
        }
        return itemDTOS;
    }

    //mapping by order entity & dto
    public OrderEntity convertToOrderEntity(OrderDto dto){
        return modelMapper.map(dto, OrderEntity.class);
    }

    //mapping by order details entity & dto
    public OrderDetailsEntity convertToOrderDetailEntity(OrderDetailDto dto){
        return modelMapper.map(dto, OrderDetailsEntity.class);
    }
}