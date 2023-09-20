package com.challenge.challenge.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
public abstract class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer identification;

    private String name;
    private String gender;
    private int age;
    private String address;
    private String phone;
}