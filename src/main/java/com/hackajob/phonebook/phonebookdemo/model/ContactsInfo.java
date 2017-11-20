package com.hackajob.phonebook.phonebookdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactsInfo {

    @JsonProperty("contacts")
    private List<Contacts> contactsList;
}
