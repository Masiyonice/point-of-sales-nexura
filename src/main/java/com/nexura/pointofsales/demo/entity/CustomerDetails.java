package com.nexura.pointofsales.demo.entity;

import com.nexura.pointofsales.demo.constant.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TableName.CUSTOMER_DETAILS)
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "name")
    private String name;

    @Column(name = "contact", unique = true)
    private String contact;

    @Column(name = "address")
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
