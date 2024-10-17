package lk.ijse.possringmaster.service;


import lk.ijse.possringmaster.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void saveOrders(OrderDto orderDto);
    List<OrderDto> getAllOrders();

}
