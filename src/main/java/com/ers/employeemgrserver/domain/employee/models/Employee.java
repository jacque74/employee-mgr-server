package com.ers.employeemgrserver.domain.employee.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity // JPA to save to the database gives spring permission to save to datatbase
@NoArgsConstructor // this is our default constructor so Lombok can generate
@RequiredArgsConstructor //this creates the parameterized constructor
@Data //generates the getters and setters
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @Override
    public String toString() {
        return String.format("%d %s %s %s", id, firstName,lastName,email);

    }
}
