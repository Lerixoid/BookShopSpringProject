package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;

import javax.persistence.*;


@MappedSuperclass
abstract public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private int id;


    private int sessionTime;

    public Person(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(int sessionTime) {
        if(sessionTime==0){
            throw new ModelValidationException("session time cant ben null");
        }
        this.sessionTime = sessionTime;
    }
}
