package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name="Moderator")
public class Moderator extends RegisteredUser{

    @NotBlank(message = "contactInformation is mandatory")
    @Column(name = "contact_information", nullable = false)
    @Size(min = 2, max = 300)
    private String contactInformation;

    public Moderator(){

    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        if(contactInformation==null){
            throw new ModelValidationException("contact info cant be null");
        }
        this.contactInformation = contactInformation;
    }
}
