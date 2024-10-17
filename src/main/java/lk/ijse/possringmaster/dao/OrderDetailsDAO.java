package lk.ijse.possringmaster.dao;

import lk.ijse.possringmaster.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsDAO extends JpaRepository<OrderDetailsEntity,String> {
}
