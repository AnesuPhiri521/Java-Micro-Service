package com.sehphirry.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sehphirry.users.utils.Roles;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String companyName;
    private String country;
    private String email;
    private boolean isActive;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @CreationTimestamp
    private Date dateCreated;
    @OneToMany(mappedBy="id")
    private List<Verification> userVerification;
}
