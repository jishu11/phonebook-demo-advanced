package com.hackajob.phonebook.phonebookdemo.service;

import com.hackajob.phonebook.phonebookdemo.model.Contacts;
import com.hackajob.phonebook.phonebookdemo.model.ContactsInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ContactServiceImpl implements ContactsService {

    private static List<Contacts> contacts = new ArrayList<>();

    @Override
    public List<Contacts> getAllContacts(final String uri) throws Exception {
        List<Contacts> contactsFromApi = new RestTemplate().getForObject(uri, ContactsInfo.class).getContactsList();
        for(int i = 0; i < contactsFromApi.size(); i++) {
            contacts.add(i, new Contacts(i+1, contactsFromApi.get(i).getName(), contactsFromApi.get(i).getPhoneNumber(), contactsFromApi.get(i).getAddress()));
        }
        return contacts;
    }

    @Override
    public List<Contacts> getSearchContacts(final String uri, final String name) throws Exception {
        return contacts.stream()
                       .filter(contact -> contact.getName().toLowerCase().contains(name.toLowerCase()))
                       .collect(Collectors.toList());
    }

    @Override
    public List<Contacts> getSortedContacts(String uri, String sortOrder) throws Exception {
        if (sortOrder.equalsIgnoreCase("asc")) contacts.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        else contacts.sort((c1, c2) -> c2.getName().compareTo(c1.getName()));
        return contacts;
    }

    @Override
    public boolean addContact(String name, String phoneNumber, String address) {
        boolean match = contacts.stream()
                                .anyMatch(x -> x.getName().equalsIgnoreCase(name));
        if(match) return true;
        else {
            contacts.add(new Contacts(contacts.size(), name, phoneNumber, address));
            return false;
        }
    }

    @Override
    public List<Contacts> getAllContacts() {
        return contacts;
    }

    @Override
    public void deleteContact(String name) {
            Optional<Contacts> contactToBeRemoved = contacts.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
            contacts.remove(contactToBeRemoved.get());
    }
}
