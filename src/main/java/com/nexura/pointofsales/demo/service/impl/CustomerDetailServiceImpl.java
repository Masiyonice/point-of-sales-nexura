package com.nexura.pointofsales.demo.service.impl;
import com.nexura.pointofsales.demo.constant.ResponsesMessage;
import com.nexura.pointofsales.demo.dto.request.RequestCustomerDetail;
import com.nexura.pointofsales.demo.dto.response.ResponseCustomer;
import com.nexura.pointofsales.demo.entity.Customer;
import com.nexura.pointofsales.demo.entity.CustomerDetails;
import com.nexura.pointofsales.demo.repository.CustomerDetailRepository;
import com.nexura.pointofsales.demo.service.CustomerDetailService;
import com.nexura.pointofsales.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerDetailRepository customerDetailRepository;
    private final CustomerService customerService;

    @Override
    public CustomerDetails getCustomer(String username) {
        Customer customer = customerService.getCustomer(username);
        return customerDetailRepository.findByCustomer(customer).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponsesMessage.ERROR_NOT_FOUND)
        );
    }

    @Override
    public CustomerDetails createCustomer(Customer customer) {
        CustomerDetails customerDetails = CustomerDetails.builder().customer(customer).build();
        return customerDetailRepository.saveAndFlush(customerDetails);
    }

    @Override
    public ResponseCustomer updateCustomer(RequestCustomerDetail requestCustomer) {
        CustomerDetails customerDetails = getCustomer(requestCustomer.getUsername());
        customerDetails.setName(requestCustomer.getName());
        customerDetails.setAddress(requestCustomer.getAddress());
        customerDetails.setContact(requestCustomer.getContact());
        CustomerDetails value = customerDetailRepository.saveAndFlush(customerDetails);

        return ResponseCustomer.builder()
                .username(value.getCustomer().getUsername())
                .name(value.getName())
                .address(value.getAddress())
                .contact(value.getContact())
                .build();
    }
}
