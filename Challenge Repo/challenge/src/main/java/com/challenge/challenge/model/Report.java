package com.challenge.challenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private LocalDateTime date;
    private String clientName;
    private Integer accountNumber;
    private String accountType;
    private double initialBalance;
    private Boolean status;
    private String movement;
    private double availableBalance;
    private Integer clientId;


}
