package com.nexura.pointofsales.demo.repository;

import com.nexura.pointofsales.demo.entity.Customer;
import com.nexura.pointofsales.demo.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CustomerDetailRepository extends JpaRepository<CustomerDetails, String> {
    Optional<CustomerDetails> findByCustomer(Customer customer);
}
