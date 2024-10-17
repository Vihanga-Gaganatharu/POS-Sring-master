package lk.ijse.possringmaster.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lk.ijse.possringmaster.customObj.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements Serializable, OrderResponse {
    private String orderId;
    @NotBlank(message = "Customer ID cannot be blank")
    private String customerId;
    @NotEmpty(message = "Order must contain at least one order detail")
    @Valid
    private List<OrderDetailDto> orderDetails;
    private String orderDateTime;
    private double total;
    @NotBlank(message = "Payment method cannot be blank")
    @Pattern(regexp = "cash|card|mobile", message = "Payment method must be either 'cash' or 'card'")
    private String paymentMethod;
}