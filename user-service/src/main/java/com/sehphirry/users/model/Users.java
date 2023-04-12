package com.sehphirry.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Users {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String email;
    private boolean isActive;
    @JsonIgnore
    private String password;
//    @Enumerated(EnumType.STRING)
//    private Roles role;
//    @CreationTimestamp
//    private Date dateCreated;
//    @OneToMany(mappedBy="id")
//    private List<Device> userDevices;
//    @OneToMany(mappedBy="id")
//    private List<Transaction> userTransaction;
//    @OneToMany(mappedBy="id")
//    private List<Verification> userVerification;
}
