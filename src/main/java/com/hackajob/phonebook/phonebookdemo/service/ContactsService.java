package com.hackajob.phonebook.phonebookdemo.service;

import com.hackajob.phonebook.phonebookdemo.model.Contacts;

import java.util.List;

public interface ContactsService {

    List<Contacts> getAllContacts(final String uri) throws Exception;

    List<Contacts> getSearchContacts(final String uri, final String name) throws  Exception;

    List<Contacts> getSortedContacts(final String uri, final String sortOrder) throws Exception;
}