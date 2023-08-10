package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name="Adress")
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 8)
    private int id;

    @NotBlank(message = "country  is mandatory")
    @Size(min = 2, max = 100)
    private String country;

    @NotBlank(message = "city  is mandatory")
    @Size(min = 2, max = 100)
    private String city;

    @NotBlank(message = "street  is mandatory")
    @Size(min = 2, max = 100)
    private String street;

    @NotBlank(message = "postalcode  is mandatory")
    @Size(min = 2, max = 30)
    private String postalcode;

    @OneToOne(cascade = CascadeType.ALL)
    private RegisteredUser user;

    public Adress(){}

    public Adress (String country, String city, String street, String postalcode){
        setCountry(country);
        setCity(city);
        setStreet(street);
        setPostalcode(postalcode);
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street==null || street.isBlank()) {
            throw new ModelValidationException("street cant be empty");
        }
        this.street = street;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {
        if (country==null || country.isBlank()) {
            throw new ModelValidationException("country cant be empty");
        }
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city==null || city.isBlank()) {
            throw new ModelValidationException("city cant be empty");
        }
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        if (postalcode==null || postalcode.isBlank()) {
            throw new ModelValidationException("postalcode cant be empty");
        }
        this.postalcode = postalcode;
    }

    @Override
    public String toString() {
        return
                "{"+ "Country: '" + country + '\'' +
                        ",City: '" + city + '\'' +
                        ",Street: '" + street + '\'' +
                        ",Postalcode: '" + postalcode + '\'' +
                        '}';
    }

}