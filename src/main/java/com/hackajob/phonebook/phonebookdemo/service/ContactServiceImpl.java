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

    private static int contactInfoCount = 0;

    static {
        List<Contacts> contactsFromApi = new RestTemplate().getForObject("http://www.mocky.io/v2/581335f71000004204abaf83", ContactsInfo.class).getContactsList();
        for(int i = 0; i < contactsFromApi.size(); i++) {
            contacts.add(i, new Contacts(i+1, contactsFromApi.get(i).getName(), contactsFromApi.get(i).getPhoneNumber(), contactsFromApi.get(i).getAddress()));
        }
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
    public boolean addContact(String name, String phoneNumber, String address) {
        boolean match = contacts.stream()
                                .anyMatch(x -> x.getName().equalsIgnoreCase(name));
        if(match) return true;
        else {
            contacts.add(new Contacts(++contactInfoCount, name, phoneNumber, address));
            return false;
        }
    }

    @Override
    public List<Contacts> getAllContacts() {
        contacts.sort((c1, c2) -> c1.getId() - c2.getId());
        return contacts;
    }

    @Override
    public void deleteContact(String name) {
            Optional<Contacts> contactToBeRemoved = contacts.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
            contacts.remove(contactToBeRemoved.get());
    }
}
