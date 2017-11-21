package com.hackajob.phonebook.phonebookdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contacts {

    private int id;

    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

}
