package com.hackajob.phonebook.phonebookdemo.controller;

import com.hackajob.phonebook.phonebookdemo.model.Contacts;
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

    @RequestMapping(value = "/contacts-info", method = RequestMethod.POST)
    public String addContact(ModelMap modelMap, Contacts contact) throws Exception {
        boolean isPresent = contactsService.addContact(contact);
        if(isPresent) {
            modelMap.put("isPresent", "Already present");
            return "contactsPage";
        } else {
            return "redirect:/phonebook";
        }
    }

    @RequestMapping(value = "/phonebook-delete", method = RequestMethod.GET)
    public String deleteContact(@RequestParam int id, ModelMap modelMap) throws Exception {
        contactsService.deleteContact(id);
        return "redirect:/phonebook";
    }

    @RequestMapping(value = "/phonebook-update", method = RequestMethod.GET)
    public String updateContact(@RequestParam int id, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("contacts", contactsService.getContact(id));
        return "contactsPage";
    }

    @RequestMapping(value = "/phonebook-update", method = RequestMethod.POST)
    public String updateContact(ModelMap modelMap, Contacts contact) throws Exception {
        contactsService.deleteContact(contact.getId());
        contactsService.updateContact(contact);
        return "redirect:/phonebook";
    }

    @RequestMapping(value = "/contacts-info", method = RequestMethod.GET)
    public String addContactInfo(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("contacts", new Contacts(0, "", "", ""));
        return "contactsPage";
    }
}
