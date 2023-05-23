package com.mutsa.hw5bookloan.Domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String city;
    private String street;

    public void edit(String city, String street) {
        if (city != null)
            this.city = city;
        if (street != null)
            this.street = street;
    }
}
