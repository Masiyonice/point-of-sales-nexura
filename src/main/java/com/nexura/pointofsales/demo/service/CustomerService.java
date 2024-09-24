package com.nexura.pointofsales.demo.service;

import com.nexura.pointofsales.demo.dto.request.RequestCustomer;
import com.nexura.pointofsales.demo.dto.response.ResponseCustomer;
import com.nexura.pointofsales.demo.entity.Customer;

import java.util.List;

public interface CustomerService {
    ResponseCustomer createCustomer(RequestCustomer requestCustomer);
    Customer getCustomer(String username);
    ResponseCustomer updatePassword(RequestCustomer requestCustomer);
    List<ResponseCustomer> getAllCustomers();
}
