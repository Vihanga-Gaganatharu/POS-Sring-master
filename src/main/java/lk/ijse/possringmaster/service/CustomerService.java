package lk.ijse.possringmaster.service;



import lk.ijse.possringmaster.customObj.CustomerResponse;
import lk.ijse.possringmaster.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);
    void updateCustomer(CustomerDto customerDto);
    void deleteCustomer(String customerId) throws ClassNotFoundException;
    CustomerResponse getSelectedCustomer(String customerId);
    List<CustomerDto> getAllCustomer();
}
