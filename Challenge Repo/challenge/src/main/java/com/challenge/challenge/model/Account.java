package com.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer accountNumber;

    private String accountType;
    private double initialBalance;
    private Boolean status;

    @JsonBackReference(value="client")
    @ManyToOne
    @JoinColumn(name = "client_identification")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Movement> movements;

}
