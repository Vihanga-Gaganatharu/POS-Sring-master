package lk.ijse.possringmaster.dao;


import lk.ijse.possringmaster.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<CustomerEntity, String> {}