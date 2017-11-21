package com.hackajob.phonebook.phonebookdemo.service;

import com.hackajob.phonebook.phonebookdemo.model.Contacts;
import com.hackajob.phonebook.phonebookdemo.model.ContactsInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ContactServiceImpl implements ContactsService {

    private static List<Contacts> contacts = new ArrayList<>();

    private static int contactInfoCount = 0;

    private static int count = 0;

    static {
        List<Contacts> contactsFromApi = new RestTemplate().getForObject("http://www.mocky.io/v2/581335f71000004204abaf83", ContactsInfo.class).getContactsList();
        contacts = contactsFromApi.stream()
                                  .map(x -> {
                                       x.setId(++count);
                                       return x;
                                   })
                                  .collect(Collectors.toList());
        contactInfoCount = contactsFromApi.size();
    }

    @Override
    public List<Contacts> getSearchContacts(final String name) throws Exception {
        return contacts.stream()
                       .filter(contact -> contact.getName().toLowerCase().contains(name.toLowerCase()))
                       .collect(Collectors.toList());
    }

    @Override
    public List<Contacts> getSortedContacts(String sortOrder) throws Exception {
        if (sortOrder.equalsIgnoreCase("asc")) contacts.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        else contacts.sort((c1, c2) -> c2.getName().compareTo(c1.getName()));
        return contacts;
    }

    @Override
    public boolean addContact(Contacts contact) throws Exception {
        boolean match = contacts.stream()
                                .anyMatch(x -> x.getName().equalsIgnoreCase(contact.getName()));
        if(match) return true;
        else {
            contacts.add(new Contacts(++contactInfoCount, contact.getName(), contact.getPhoneNumber(), contact.getAddress()));
            return false;
        }
    }

    @Override
    public List<Contacts> getAllContacts() throws Exception {
        contacts.sort((c1, c2) -> c1.getId() - c2.getId());
        return contacts;
    }

    @Override
    public void deleteContact(int id) {
            contacts.remove(getContact(id).get());
    }

    @Override
    public Optional<Contacts> getContact(int id) {
        return contacts.stream()
                       .filter(x -> x.getId() == id)
                       .findFirst();
    }

    @Override
    public void updateContact(Contacts contact) {
        contacts.add(contact);
    }
}
