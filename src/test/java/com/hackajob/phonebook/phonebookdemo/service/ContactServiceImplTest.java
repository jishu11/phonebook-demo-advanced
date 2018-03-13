package com.hackajob.phonebook.phonebookdemo.service;

import com.hackajob.phonebook.phonebookdemo.model.Contacts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    public void testGetSearchContacts() throws Exception {
        List<Contacts> contacts = contactService.getSearchContacts("Maida Harju");
        assertEquals("Maida Harju", contacts.get(0).getName());
    }

    @Test
    public void testGetSortedContacts() throws Exception {
        List<Contacts> contacts = contactService.getSortedContacts("asc");
        assertEquals("Adan Milian", contacts.get(0).getName());
        assertEquals("Stanley Vanderhoof", contacts.get(contacts.size() - 1).getName());
    }

    @Test
    public void testAddContact() throws Exception {
        assertTrue(contactService.addContact(new Contacts(2, "Maida Harju", "+442032960899", "Woodside House, 94 Tockholes Rd, Darwen BB3 1LL, UK")));
        assertFalse(contactService.addContact(new Contacts(10, "Kaite Robert", "+442034965124", "26 Westmorland Cl, Darwen BB3 2TQ, UK")));
    }

    @Test
    public void testGetAllContacts() throws Exception {
        List<Contacts> contacts = contactService.getAllContacts();
        assertEquals(10, contacts.size());
    }

    @Test
    public void testDeleteContact() throws Exception {
        contactService.deleteContact(1);
        List<Contacts> contacts = contactService.getAllContacts();
        assertFalse(contacts.stream().anyMatch(x -> x.getId() == 1));
    }

    @Test
    public void testGetContact() {
        Optional<Contacts> contacts = contactService.getContact(2);
        assertTrue(contacts.isPresent());
    }

    @Test
    public void testUpdateContact() throws Exception {
        contactService.updateContact(new Contacts(11, "Kaisy William", "+442034965126", "29 Westmorland Cl, Darwen BB3 2TQ, UK"));
        List<Contacts> contacts = contactService.getAllContacts();
        assertTrue(contacts.stream().anyMatch(x -> x.getId() == 11 && x.getName().equals("Kaisy William")));
    }
}
