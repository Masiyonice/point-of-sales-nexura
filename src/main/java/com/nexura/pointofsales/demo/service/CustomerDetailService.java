package com.nexura.pointofsales.demo.service;


import com.nexura.pointofsales.demo.dto.request.RequestCustomerDetail;
import com.nexura.pointofsales.demo.dto.response.ResponseCustomer;
import com.nexura.pointofsales.demo.entity.Customer;
import com.nexura.pointofsales.demo.entity.CustomerDetails;

public interface CustomerDetailService {

    CustomerDetails getCustomer(String username);
    ResponseCustomer updateCustomer(RequestCustomerDetail requestCustomer);
    CustomerDetails createCustomer(Customer customer);
}
