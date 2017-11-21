package com.hackajob.phonebook.phonebookdemo.service;

import com.hackajob.phonebook.phonebookdemo.model.Contacts;

import java.util.List;
import java.util.Optional;

public interface ContactsService {

    List<Contacts> getSearchContacts(final String name) throws  Exception;

    List<Contacts> getSortedContacts(final String sortOrder) throws Exception;

    boolean addContact(Contacts contact) throws Exception;

    List<Contacts> getAllContacts() throws Exception;

    void deleteContact(int id) throws Exception;

    void updateContact(Contacts contact) throws Exception;

    Optional<Contacts> getContact(int id) throws Exception;
}