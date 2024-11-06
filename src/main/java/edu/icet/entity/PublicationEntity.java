package edu.icet.entity;

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
    @Column(nullable = false)
    private String name;
    @Column(name = "contact_number")
    private String contactNumber;
    private String address;

    @OneToMany(mappedBy = "publication")
    private List<BookEntity> books;
}
