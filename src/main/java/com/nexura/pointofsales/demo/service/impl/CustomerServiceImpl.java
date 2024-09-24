package com.nexura.pointofsales.demo.service.impl;

import com.nexura.pointofsales.demo.constant.ResponsesMessage;
import com.nexura.pointofsales.demo.dto.request.RequestCustomer;
import com.nexura.pointofsales.demo.dto.request.RequestCustomerDetail;
import com.nexura.pointofsales.demo.dto.response.ResponseCustomer;
import com.nexura.pointofsales.demo.entity.Customer;
import com.nexura.pointofsales.demo.entity.CustomerDetails;
import com.nexura.pointofsales.demo.repository.CustomerRepository;
import com.nexura.pointofsales.demo.service.CustomerDetailService;
import com.nexura.pointofsales.demo.service.CustomerService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDetailService customerDetailService;

    @Override
    public ResponseCustomer createCustomer(RequestCustomer requestCustomer) {
        Customer customer = Customer.builder()
                .username(requestCustomer.getUsername())
                .password(requestCustomer.getPassword())
                .build();

        Customer customerEntity = customerRepository.saveAndFlush(customer);
        CustomerDetails customerDetails = customerDetailService.createCustomer(customerEntity);

        return ResponseCustomer.builder()
                .username(customer.getUsername())
                .name(customerDetails.getName())
                .address(customerDetails.getAddress())
                .contact(customerDetails.getContact())
                .build();
    }

    @Override
    public Customer getCustomer(String username) {
        return customerRepository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponsesMessage.ERROR_NOT_FOUND)
        );
    }

    @Override
    public ResponseCustomer updatePassword(RequestCustomer requestCustomer) {
        Customer customer = getCustomer(requestCustomer.getUsername());
        customer.setPassword(requestCustomer.getPassword());
        customerRepository.save(customer);
        return ResponseCustomer.builder()
                .username(customer.getUsername())
                .build();
    }

    @Override
    public List<ResponseCustomer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDetails> list = customers.stream().map(
                customer -> customerDetailService
                            .getCustomer(customer.getUsername()))
                            .toList();

        return list.stream().map(
                customerDetails -> ResponseCustomer.builder()
                        .username(customerDetails.getCustomer().getUsername())
                        .name(customerDetails.getName())
                        .address(customerDetails.getAddress())
                        .contact(customerDetails.getContact())
                        .build()
        ).toList();
    }
}
