package lk.ijse.possringmaster.service;



import lk.ijse.possringmaster.customObj.CustomerResponse;
import lk.ijse.possringmaster.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDto customerDTO);
    void updateCustomer(String id, CustomerDto customerDTO);
    void deleteCustomer(String id);
    CustomerResponse getCustomerById(String id);
    List<CustomerDto> getAllCustomers();
}