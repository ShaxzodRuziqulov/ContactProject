package ContactProject.service;

import ContactProject.dto.Contact;
import ContactProject.respository.ContactRespository;

import java.util.List;

public class Service {
    private ContactRespository contactRespository = new ContactRespository();

    public void addContact(Contact contact) {
        boolean result = contactRespository.saveContact(contact);
        if (result) {
            System.out.println("Contacts added");
        } else {
            System.out.println("Something wend rong");
        }
    }

    public void contactList() {
        List<Contact> contactList = contactRespository.getList();
        for (Contact contact :
                contactList) {
            System.out.println(contact.getName() + " " + contact.getSurname() + " " + contact.getPhone());
        }
    }

    public void delete(String phone) {

        Contact contact = contactRespository.byPhone(phone);

        if (contact == null) {
            System.out.println("Contacts not exists. Mazgi");
            return;
        }
        int effectedRows = contactRespository.delate(phone);
        if (effectedRows == 1) {
            System.out.println("phone succsessfully delated. ");
        }
    }

    public void search(String query) {
        List<Contact> contactList = contactRespository.search(query);

        for (Contact contact :
                contactList) {
            System.out.println(contact.getName() + " " + contact.getSurname() + " " + contact.getPhone());
        }
    }
}
