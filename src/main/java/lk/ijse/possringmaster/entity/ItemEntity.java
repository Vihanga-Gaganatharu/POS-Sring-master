package lk.ijse.possringmaster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class ItemEntity implements Serializable {
    @Id
    private String itemCode;
    private String category;
    private double unitPrice;
    private int qtyOnHand;
    private String registerDate;
    private String expireDate;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderDetailsEntity> orderDetails;
}