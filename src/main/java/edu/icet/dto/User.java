package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String type;
    private LocalDate createdDate;
    private String contactNumber;
    private String addressLine1;
    private String addressLine2;
    private String province;
    private String postalCode;
    private String country;
    private List<Book> books;
}
