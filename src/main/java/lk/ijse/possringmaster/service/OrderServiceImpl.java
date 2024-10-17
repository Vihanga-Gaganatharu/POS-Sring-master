package lk.ijse.possringmaster.service;

import lk.ijse.possringmaster.dao.ItemDAO;
import lk.ijse.possringmaster.dao.OrderDAO;
import lk.ijse.possringmaster.dto.OrderDto;
import lk.ijse.possringmaster.dto.OrderDetailDto;
import lk.ijse.possringmaster.entity.ItemEntity;
import lk.ijse.possringmaster.entity.OrderDetailsEntity;
import lk.ijse.possringmaster.entity.OrderEntity;
import lk.ijse.possringmaster.exception.DataPersistFailedException;
import lk.ijse.possringmaster.exception.ItemNotFoundException;
import lk.ijse.possringmaster.service.OrderService;
import lk.ijse.possringmaster.util.AppUtil;
import lk.ijse.possringmaster.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderDAO orderDAO;

    @Autowired
    private final ItemDAO itemDAO;

    @Autowired
    private final MappingUtil mappingUtil;

    @Override
    public String saveOrder(OrderDto orderDTO) {
        orderDTO.setOrderId(generateOrderID());
        orderDTO.setOrderDateTime(AppUtil.getCurrentDateTime());
        orderDTO.setTotal(orderDTO.getOrderDetails().stream().mapToDouble(detail -> detail.getQty() * detail.getUnitPrice()).sum());
        OrderEntity orderEntity = mappingUtil.convertToOrderEntity(orderDTO);

        List<OrderDetailsEntity> orderDetailEntities = orderDTO.getOrderDetails().stream().map(detail -> {
                    OrderDetailsEntity orderDetailEntity = mappingUtil.convertToOrderDetailEntity(detail);
                    orderDetailEntity.setDescription("Payment Verified");
                    orderDetailEntity.setOrder(orderEntity);
                    return orderDetailEntity;
                })
                .collect(Collectors.toList());

        orderEntity.setOrderDetails(orderDetailEntities);
        boolean allItemsUpdated = orderDTO.getOrderDetails().stream().allMatch(this::updateItemQty);

        if (allItemsUpdated) {
            orderDAO.save(orderEntity);
            return "Order placed successfully";
        } else {
            throw new DataPersistFailedException("place order failed");
        }
    }

    private boolean updateItemQty(OrderDetailDto orderDetailDTO) {
        ItemEntity item = itemDAO.findById(orderDetailDTO.getItemCode()).orElse(null);
        if (item == null) {
            throw new ItemNotFoundException("Item not found");
        }

        if (item.getQtyOnHand() < orderDetailDTO.getQty()) {
            throw new ItemNotFoundException("Item qty not enough");
        }

        item.setQtyOnHand(item.getQtyOnHand() - orderDetailDTO.getQty());
        itemDAO.save(item);
        return true;
    }

    private String generateOrderID() {
        if (orderDAO.count() == 0) {
            return "O001";
        } else {
            String lastId = orderDAO.findAll().get(orderDAO.findAll().size() - 1).getOrderId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "O00" + newId;
            } else if (newId < 100) {
                return "O0" + newId;
            } else {
                return "O" + newId;
            }
        }
    }
}