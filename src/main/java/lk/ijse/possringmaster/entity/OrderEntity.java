package lk.ijse.possringmaster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity implements Serializable {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private CustomerEntity customer;
    private String orderDateTime;
    private double total;
    private String paymentMethod;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetails;
}

