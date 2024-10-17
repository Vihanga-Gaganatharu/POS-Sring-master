package lk.ijse.possringmaster.controller;


import jakarta.validation.Valid;
import lk.ijse.possringmaster.customObj.CustomerResponse;
import lk.ijse.possringmaster.dto.CustomerDto;
import lk.ijse.possringmaster.exception.CustomerNotFoundException;
import lk.ijse.possringmaster.exception.DataPersistFailedException;
import lk.ijse.possringmaster.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@Valid @RequestBody CustomerDto customer) {
        if (customer == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                customerService.saveCustomer(customer);
                logger.info("Customer saved : " + customer);
                return ResponseEntity.created(null).build();
            } catch (DataPersistFailedException e) {
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerResponse getCustomerById(@PathVariable("customerId") String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDto customerDTO, @PathVariable("customerId") String customerId) {
        if (customerDTO == null || customerId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                customerService.updateCustomer(customerId, customerDTO);
                logger.info("Customer updated : " + customerDTO);
                return ResponseEntity.noContent().build();
            } catch (CustomerNotFoundException e) {
                return ResponseEntity.notFound().build();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") String customerId) {
        try {
            customerService.deleteCustomer(customerId);
            logger.info("Customer deleted : " + customerId);
            return ResponseEntity.noContent().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}