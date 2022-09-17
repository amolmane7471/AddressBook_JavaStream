import java.util.*;
import java.util.stream.Collectors;
public class AddressBook {

    static ArrayList<Contact> persons = new ArrayList<>();
    public static HashMap<String, ArrayList<Contact>> personByState;
    public static HashMap<String, ArrayList<Contact>> personByCity;
    static Scanner scanner = new Scanner(System.in);
    static int counter;
    private static int indexOfPerson;

    public AddressBook() {
        personByCity = new HashMap<String, ArrayList<Contact>>();
        personByState = new HashMap<String, ArrayList<Contact>>();
    }

    public void addAddress() {

        Contact contact = new Contact();
        System.out.print("First Name : ");
        contact.setFirstName(scanner.next());
        checkDuplicate(contact.getFirstName());
        System.out.print("Last Name  : ");
        contact.setLastName(scanner.next());
        System.out.print("Address  : ");
        contact.setAddress(scanner.next());
        System.out.print("City Name : ");
        contact.setCity(scanner.next());
        System.out.print("State Name : ");
        contact.setState(scanner.next());
        System.out.print("Zip Code : ");
        contact.setZip(scanner.next());
        System.out.print("Phone NO : ");
        contact.setPhoneNo(scanner.next());
        System.out.print("Email Id  : ");
        contact.setEmail(scanner.next());
        persons.add(contact);
    }
    public void editContact() {

        System.out.println("Enter the first name of the contact you want to edit: ");
        String name = scanner.next();

        for (Contact contacts : persons) {
            if (contacts.getFirstName().equals(name)) {
                System.out.print("Enter first name   : ");
                contacts.setFirstName(scanner.next());
                System.out.print("Enter Last name    : ");
                contacts.setLastName(scanner.next());
                System.out.print("Enter Address  : ");
                contacts.setAddress(scanner.next());
                System.out.print("Enter City   : ");
                contacts.setCity(scanner.next());
                System.out.print("Enter State  : ");
                contacts.setState(scanner.next());
                System.out.print("Enter Zip   : ");
                contacts.setZip(scanner.next());
                System.out.print("Enter Phone  : ");
                contacts.setPhoneNo(scanner.next());
                System.out.print("Enter Email  : ");
                contacts.setEmail(scanner.next());
                System.out.print("Contact edited successfully!");
            } else
                System.out.println("The contact with first name : " + name + " is not found!");
        }
    }
    public void deleteContact() {
        System.out.println("Enter the first name of the contact you want to edit: ");
        String name = scanner.next();
        for (int i = 0; i < persons.size(); i++) {

            if (persons.get(i).getFirstName().equals(name)) {
                persons.remove(i);
                System.out.println("List After deletion \n" + persons);

            } else {
                System.out.println("The contact is not found!");
            }
        }
    }
    public void display() {
        for (Contact person2 : persons)
            System.out.println(person2);
    }

    private static boolean checkDuplicate(String fname) {
        int flag = 0;
        for (Contact person1 : persons) {
            if (person1.getFirstName().equals(fname)) {
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    public void getPersonNameByState(String State) {
        List<Contact> list = persons.stream().filter(contactName -> contactName.getState().equals(State))
                .collect(Collectors.toList());
        for (Contact contact : list) {
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
        }

    }

    public void getPersonNameByCity(String cityName) {
        List<Contact> list = persons.stream().filter(contactName -> contactName.getCity().equals(cityName))
                .collect(Collectors.toList());
        for (Contact contact : list) {
            System.out.println("First Name: " + contact.getFirstName());
            System.out.println("Last Name: " + contact.getLastName());
        }
    }
}