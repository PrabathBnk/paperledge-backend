package edu.icet.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String type;
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    private String province;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;

    @OneToMany(mappedBy = "ownerUser")
    @JsonManagedReference("user")
    private List<BookEntity> books;
}
