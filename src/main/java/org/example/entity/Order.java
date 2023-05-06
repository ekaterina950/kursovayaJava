package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message = "Loading point can not be empty!")
    private String fullname;
    @NotEmpty(message = "Destination point can not be empty!")
    private String people;
    @NotEmpty(message = "Contact information can not be empty!")
    private String contact;
    private String info;
    private String data;
    private String time;
    private int btn;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Customer customer;
}
