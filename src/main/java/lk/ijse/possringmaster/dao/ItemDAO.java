package lk.ijse.possringmaster.dao;

import lk.ijse.possringmaster.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<ItemEntity,String> {
}
