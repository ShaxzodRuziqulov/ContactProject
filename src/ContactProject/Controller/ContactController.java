package ContactProject.Controller;

import ContactProject.dto.Contact;
import ContactProject.util.DataUtil;
import ContactProject.service.Service;

import java.util.Scanner;

public class ContactController {
    private Service service = new Service();

    private Scanner strscanner = new Scanner(System.in);

    private Scanner numscanner = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("*** Menu ***");
        System.out.println("1.Add Contacts");
        System.out.println("2.Contacts List");
        System.out.println("3.Delete Contacts");
        System.out.println("4.Search Contacts");
        System.out.println("0.Exit");
    }

    public void start() {
        DataUtil.createTable();
        boolean b = true;
        while (b) {
            showMenu();
            int action = getAction();
            switch (action) {
                case 1:
                    System.out.println("1.Add Contacts");
                    addContact();
                    break;
                case 2:
                    System.out.println("2.Contacts List");
                    contactList();
                    break;
                case 3:
                    System.out.println("3.Delete Contacts");
                    delete();
                    break;
                case 4:
                    System.out.println("4.Search Contacts");
                    search();
                    break;
                case 0:
                    System.out.println("0.Exit");
                    b = false;
                    break;
                default:
                    b = false;
                    break;
            }
            System.out.println("The end");
        }

    }

    public int getAction() {
        System.out.println("Enter action");
        return numscanner.nextInt();
    }

    public void addContact() {
        System.out.println("Enter name");
        String name = strscanner.next();
        System.out.println("Enter surname");
        String surname = strscanner.
                next();
        System.out.println("Enter phone");
        String phone = strscanner.next();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setSurname(surname);
        contact.setPhone(phone);

        service.addContact(contact);
    }

    public void contactList() {
        service.contactList();
    }

    public void delete() {
        System.out.println("Enter phone");
        String phone = strscanner.next();
        service.delete(phone);
    }

    public void search() {
        System.out.println("Enter query");
        String query = strscanner.next();

        service.search(query);
    }
}
