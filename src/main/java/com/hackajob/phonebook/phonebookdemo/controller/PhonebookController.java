package com.hackajob.phonebook.phonebookdemo.controller;

import com.hackajob.phonebook.phonebookdemo.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PhonebookController {

    @Autowired
    private ContactsService contactsService;

    @RequestMapping(value = "/phonebook", method = RequestMethod.GET)
    public String getPhoneBookContacts(ModelMap modelMap) throws Exception {
        modelMap.put("phoneBook", contactsService.getAllContacts());
        return "phoneBook";
    }

    @RequestMapping(value = "/phonebook", method = RequestMethod.GET, params = "sort")
    public String getPhoneBookContacts(@RequestParam String sort, ModelMap modelMap) throws Exception {
        modelMap.put("phoneBook", contactsService.getSortedContacts(sort));
        return "phoneBook";
    }

    @RequestMapping(value = "/phonebook", method = RequestMethod.POST)
    public String getSearchedContacts(@RequestParam String contact, ModelMap modelMap) throws Exception {
        modelMap.put("phoneBook", contactsService.getSearchContacts(contact));
        return "phoneBook";
    }

    @RequestMapping(value = "/contacts-info", method = RequestMethod.POST, params = "name")
    public String addContact(@RequestParam String name, @RequestParam String phoneNo, @RequestParam String contactAddress, ModelMap modelMap) throws Exception {
        boolean isPresent = contactsService.addContact(name, phoneNo, contactAddress);
        if(isPresent) {
            modelMap.put("isPresent", "Already present");
            return "contactsPage";
        } else {
            modelMap.put("phoneBook", contactsService.getAllContacts());
            return "phoneBook";
        }
    }

    @RequestMapping(value = "/phonebook-delete", method = RequestMethod.GET)
    public String deleteContact(@RequestParam String name, ModelMap modelMap) throws Exception {
        contactsService.deleteContact(name);
        modelMap.put("phoneBook", contactsService.getAllContacts());
        return "phoneBook";
    }

    @RequestMapping(value = "/contacts-info", method = RequestMethod.GET)
    public String addContactInfo() throws Exception {
        return "contactsPage";
    }
}
