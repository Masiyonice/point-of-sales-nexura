package com.nexura.pointofsales.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCustomer {
    private String username;
    private String name;
    private String contact;
    private String address;
}
