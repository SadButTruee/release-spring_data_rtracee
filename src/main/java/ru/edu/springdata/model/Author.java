package ru.edu.springdata.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Сущность описывающая авторов книг
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(mappedBy="authors", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Book> books;

    @OneToOne
    @JoinColumn(name="adress_id")
    private Address address;
}
