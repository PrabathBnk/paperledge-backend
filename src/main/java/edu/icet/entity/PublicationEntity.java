package edu.icet.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "publication")
public class PublicationEntity {
    @Id
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(name = "contact_number")
    private String contactNumber;
    private String address;

    @OneToMany(mappedBy = "publication")
    @JsonManagedReference("publication")
    private List<BookEntity> books;
}
