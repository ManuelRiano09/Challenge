package com.challenge.challenge.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Client extends Person {

    private String password;
    private String status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Account> accounts;
}
