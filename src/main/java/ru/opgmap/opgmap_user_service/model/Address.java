package ru.opgmap.opgmap_user_service.model;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String country;

    private String city;

    private String street;

    private String house;

}
