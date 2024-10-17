package lk.ijse.possringmaster.service;

import jakarta.transaction.Transactional;
import lk.ijse.possringmaster.dao.CustomerDAO;
import lk.ijse.possringmaster.dao.ItemDAO;
import lk.ijse.possringmaster.dao.OrderDAO;
import lk.ijse.possringmaster.dao.OrderDetailsDAO;
import lk.ijse.possringmaster.dto.OrderDto;
import lk.ijse.possringmaster.entity.CustomerEntity;
import lk.ijse.possringmaster.entity.ItemEntity;
import lk.ijse.possringmaster.entity.OrderDetailsEntity;
import lk.ijse.possringmaster.util.AppUtil;
import lk.ijse.possringmaster.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderDAO orderDAO;
    @Autowired
    private final CustomerDAO customerDAO;
    @Autowired
    private final ItemDAO itemDAO;
    @Autowired
    private final OrderDetailsDAO orderDetailsDAO;

    @Autowired
    private final Mapping mapping;

    @Override
    @Transactional
    public void saveOrders(OrderDto orderDto) {
        CustomerEntity customer = customerDAO.getCustomerEntitiesById(orderDto.getCustomerId());

        orderDto.setId(AppUtil.createOrderId());
        System.out.println(orderDto);
        var orderEntity = mapping.convertToOrderEntity(orderDto);

        List<OrderDetailsEntity> orderDetails = orderDto.getOrderDetailDtos().stream().map(orderDetailDto -> {
            OrderDetailsEntity orderDetailsEntity = mapping.convertToOrderDetailEntity(orderDetailDto);
            orderDetailsEntity.setOrder(orderEntity);
            orderDetailsDAO.save(orderDetailsEntity);

            ItemEntity item = itemDAO.findById(orderDetailDto.getItemid()).orElseThrow(() -> new RuntimeException("Item not found"));

            item.setQty(item.getQty() - orderDetailDto.getQty());
            orderDetailsEntity.setItem(item);
            itemDAO.save(item);
            return orderDetailsEntity;
        }).collect(Collectors.toList());

        orderEntity.setOrderDetails(orderDetails);

        orderDAO.save(orderEntity);
    }

    @Override
    public List<OrderDto> getAllOrders()  {
        return mapping.convertToOrderDTOList(orderDAO.findAll());
    }
}