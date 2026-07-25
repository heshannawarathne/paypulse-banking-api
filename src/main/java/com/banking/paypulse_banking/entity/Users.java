package com.banking.paypulse_banking.entity;

import com.banking.paypulse_banking.entity.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Persistent;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @Column(name = "email", nullable = false,unique = true, length = 100)
    private String email;

    @Column(name = "password", length = 300)
    private String password;

    @Column(name = "mobile_number", length = 100)
    private String mobileNumber;

    @Column(name = "nic", length = 100)
    private String nic;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


}
