package com.sehphirry.users.model;

import com.sehphirry.users.utils.VerificationType;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private Users user;
    @Enumerated(EnumType.STRING)
    private VerificationType verificationType;
    private String verification;
    private boolean status;
    @CreationTimestamp
    private Date createdDate;

    public Verification(Users user, VerificationType verificationType, String verification, boolean status) {
        this.user = user;
        this.verificationType = verificationType;
        this.verification = verification;
        this.status = status;
    }

    public Verification() {}
}
