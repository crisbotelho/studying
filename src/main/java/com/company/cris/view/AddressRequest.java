package com.company.cris.view;

public record AddressRequest(String city,
                             String country,
                             String street,
                             Long number,
                             String postalCode) {
}
