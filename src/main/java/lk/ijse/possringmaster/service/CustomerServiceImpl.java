package lk.ijse.possringmaster.service;

import jakarta.transaction.Transactional;
import lk.ijse.possringmaster.customObj.CustomerErrorResponse;
import lk.ijse.possringmaster.customObj.CustomerResponse;
import lk.ijse.possringmaster.dao.CustomerDAO;
import lk.ijse.possringmaster.dto.CustomerDto;
import lk.ijse.possringmaster.entity.CustomerEntity;
import lk.ijse.possringmaster.exception.CustomerNotFoundException;
import lk.ijse.possringmaster.exception.DataPersistFailedException;
import lk.ijse.possringmaster.util.AppUtil;
import lk.ijse.possringmaster.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.spi.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerDAO customerDAO;
    @Autowired
    private final MappingUtil mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        customerDto.setId(AppUtil.createCustomerId());
        CustomerEntity savedCustomer =
                customerDAO.save(mapping.convertToCustomerEntity(customerDto));
        if(savedCustomer == null ) {
            throw new DataPersistFailedException("Cannot data saved");
        }
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Optional<CustomerEntity> tmpCustomer = customerDAO.findById(customerDto.getId());
        if(!tmpCustomer.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            tmpCustomer.get().setName(customerDto.getName());
            tmpCustomer.get().setAddress(customerDto.getAddress());
            tmpCustomer.get().setSalary(customerDto.getSalary());
        }
    }

    @Override
    public void deleteCustomer(String customerId) {
        Optional<CustomerEntity> selectedCustomerId = customerDAO.findById(customerId);
        if(!selectedCustomerId.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            customerDAO.deleteById(customerId);
        }
    }

    @Override
    public CustomerResponse getSelectedCustomer(String customerId) {
        if(customerDAO.existsById(customerId)){
            CustomerEntity customerEntityByCustomerId = customerDAO.getCustomerEntitiesById(customerId);
            return mapping.convertToCustomerDto(customerEntityByCustomerId);
        }else {
            return new CustomerErrorResponse(0, "Customer not found");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomer() {

        List<CustomerEntity> getAllCustomers = customerDAO.findAll();


        return mapping.convertToCustomerDtoList(getAllCustomers);
    }
    }
