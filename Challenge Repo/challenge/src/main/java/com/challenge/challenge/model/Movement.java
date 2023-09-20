package com.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Movement {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private LocalDateTime date;
    private String movementType;
    private double value;
    private String balance;

    @JsonBackReference(value="account")
    @ManyToOne
    @JoinColumn(name = "account_accountNumber")
    private Account account;


}
