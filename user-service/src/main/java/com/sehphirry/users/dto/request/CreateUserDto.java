package com.sehphirry.users.dto.request;


import com.sehphirry.users.utils.Roles;
import lombok.Data;

@Data
public class CreateUserDto {
    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String email;
    private String password;
    private Roles userRole;
    private String vendorNumber;
}
