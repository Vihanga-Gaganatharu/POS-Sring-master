package lk.ijse.possringmaster.service;

import lk.ijse.possringmaster.customObj.CustomerResponse;
import lk.ijse.possringmaster.customObj.CustomerErrorResponse;
import lk.ijse.possringmaster.dao.CustomerDAO;
import lk.ijse.possringmaster.dto.CustomerDto;
import lk.ijse.possringmaster.entity.CustomerEntity;
import lk.ijse.possringmaster.exception.CustomerNotFoundException;
import lk.ijse.possringmaster.util.AppUtil;
import lk.ijse.possringmaster.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerDAO customerDAO;

    @Autowired
    private final MappingUtil mappingUtil;

    @Override
    public String saveCustomer(CustomerDto customerDTO) {
        customerDTO.setCustomerId(generateCustomerID());
        customerDTO.setName(customerDTO.getFirstName() + " " + customerDTO.getLastName());
        customerDTO.setLastUpdatedAt(AppUtil.getCurrentDateTime());
        CustomerEntity customerEntity = mappingUtil.convertToCustomerEntity(customerDTO);
        customerDAO.save(customerEntity);
        System.out.println("Customer saved : " + customerEntity);
        return "Customer saved successfully";
    }

    @Override
    public void updateCustomer(String id, CustomerDto customerDTO) {
        Optional<CustomerEntity> tmpCustomer = customerDAO.findById(id);
        if (!tmpCustomer.isPresent()) {
            System.out.println("Customer not found");
            throw new CustomerNotFoundException("Customer not found");
        } else {
            tmpCustomer.get().setName(customerDTO.getFirstName() + " " + customerDTO.getLastName());
            tmpCustomer.get().setAddress(customerDTO.getAddress());
            tmpCustomer.get().setMobile(customerDTO.getMobile());
            tmpCustomer.get().setEmail(customerDTO.getEmail());
            tmpCustomer.get().setLastUpdatedAt(AppUtil.getCurrentDateTime());
            System.out.println("Customer updated : " + customerDTO);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        if (customerDAO.existsById(id)) {
            customerDAO.deleteById(id);
            System.out.println("Customer deleted : " + id);
        } else {
            System.out.println("Customer not found");
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public CustomerResponse getCustomerById(String id) {
        if (customerDAO.existsById(id)) {
            CustomerEntity customerEntity = customerDAO.getReferenceById(id);
            System.out.println("Customer found : " + customerEntity);
            CustomerDto customerDTO = mappingUtil.convertToCustomerDTO(customerEntity);
            customerDTO.setFirstName(customerDTO.getName().split(" ")[0]);
            customerDTO.setLastName(customerDTO.getName().split(" ")[1]);
            return customerDTO;
        } else {
            System.out.println("Customer not found");
            return new CustomerErrorResponse(0, "Customer not found");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return mappingUtil.convertToCustomerDTOList(customerDAO.findAll());
    }

    private String generateCustomerID() {
        if (customerDAO.count() == 0) {
            return "C001";
        } else {
            String lastId = customerDAO.findAll().get(customerDAO.findAll().size() - 1).getCustomerId();
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "C00" + newId;
            } else if (newId < 100) {
                return "C0" + newId;
            } else {
                return "C" + newId;
            }
        }
    }
}