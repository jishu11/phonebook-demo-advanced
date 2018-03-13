package com.hackajob.phonebook.phonebookdemo.controller;

import com.hackajob.phonebook.phonebookdemo.model.Contacts;
import com.hackajob.phonebook.phonebookdemo.service.ContactsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PhonebookControllerTest {

    @Mock
    private ContactsService contactsService;

    @InjectMocks
    private PhonebookController phonebookController;

    private List<Contacts> contacts;

    @Before
    public void setUp() {
        contacts = new ArrayList<>();
        contacts.add(new Contacts(1, "Maida Harju", "+442032960899", "Woodside House, 94 Tockholes Rd, Darwen BB3 1LL, UK"));
        contacts.add(new Contacts(2, "Kaite Robert", "+442034965124", "26 Westmorland Cl, Darwen BB3 2TQ, UK"));
    }

    @Test
    public void testGetPhoneBookContacts() throws Exception {
        when(contactsService.getAllContacts()).thenReturn(contacts);
        assertEquals("phoneBook", phonebookController.getPhoneBookContacts(new ModelMap()));
    }

    @Test
    public void testGetPhoneBookContactsSort() throws Exception {
        contacts.sort((x1, x2) -> x1.getName().compareTo(x2.getName()));
        when(contactsService.getSortedContacts("asc")).thenReturn(contacts);
        assertEquals("phoneBook", phonebookController.getPhoneBookContacts("asc", new ModelMap()));
        verify(contactsService, times(1)).getSortedContacts("asc");
    }

    @Test
    public void testGetSearchedContacts() throws Exception {
        List<Contacts> contact = contacts.stream().filter(x -> x.getName().equals("Kaite Robert")).collect(toList());
        when(contactsService.getSearchContacts("Kaite Robert")).thenReturn(contact);
        assertEquals("phoneBook", phonebookController.getSearchedContacts("Kaite Robert", new ModelMap()));
        verify(contactsService, times(1)).getSearchContacts("Kaite Robert");
    }

    @Test
    public void testAddContact() throws Exception {
        when(contactsService.addContact(any(Contacts.class))).thenReturn(true);
        assertEquals("contactsPage", phonebookController.addContact(new ModelMap(), new Contacts(2, "Kaite Robert", "+442034965124", "26 Westmorland Cl, Darwen BB3 2TQ, UK") ));
        when(contactsService.addContact(any(Contacts.class))).thenReturn(false);
        assertEquals("redirect:/phonebook", phonebookController.addContact(new ModelMap(), new Contacts(3, "Kaicy William", "+442034965125", "29 Westmorland Cl, Darwen BB3 2TQ, UK")));
    }

    @Test
    public void testDeleteContact() throws Exception {
        doNothing().when(contactsService).deleteContact(anyInt());
        assertEquals("redirect:/phonebook", phonebookController.deleteContact(2, new ModelMap()));
        verify(contactsService, times(1)).deleteContact(2);
    }

    @Test
    public void testGetUpdateContact() throws Exception {
        Optional<Contacts> contact = contacts.stream().filter(x-> x.getId() == 1).findFirst();
        when(contactsService.getContact(anyInt())).thenReturn(contact);
        assertEquals("contactsPage", phonebookController.updateContact(1, new ModelMap()));
        verify(contactsService, times(1)).getContact(1);
    }


    @Test
    public void testUpdateContact() throws Exception {
        doNothing().when(contactsService).deleteContact(anyInt());
        doNothing().when(contactsService).updateContact(any(Contacts.class));
        assertEquals("redirect:/phonebook", phonebookController.updateContact(new ModelMap(), new Contacts(10, "Kaicy William", "+442034965125", "29 Westmorland Cl, UK")));
        verify(contactsService, times(1)).updateContact(any());
    }

    @Test
    public void testAddContactInfo() throws Exception {
        assertEquals("contactsPage", phonebookController.addContactInfo(new ModelMap()));
    }

}
