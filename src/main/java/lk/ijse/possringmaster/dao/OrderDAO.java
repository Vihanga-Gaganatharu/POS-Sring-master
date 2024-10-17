package lk.ijse.possringmaster.dao;

import lk.ijse.possringmaster.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity,String> {
}
