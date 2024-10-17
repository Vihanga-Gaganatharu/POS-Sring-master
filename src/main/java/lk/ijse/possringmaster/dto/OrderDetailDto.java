package lk.ijse.possringmaster.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lk.ijse.possringmaster.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailDto implements Serializable, OrderResponse {
    private String orderId;
    @NotBlank(message = "Item code cannot be blank")
    private String itemCode;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int qty;
    @Positive(message = "Unit price must be greater than zero")
    private double unitPrice;
    private double total;
}
