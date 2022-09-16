import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    static Scanner scanner = new Scanner(System.in);
    ArrayList<Contact> ContactsArrayList = new ArrayList<>();
    static HashMap<String, ArrayList<Contact>> hashmap = new HashMap<>();
    public static Map<String, Contact> cityHashMap = new HashMap<>();
    public static Map<String, Contact> stateHashMap = new HashMap<>();

    static AddressBook details = new AddressBook();
    static boolean flag = true;

    public void addAddress() {

        Contact contact = new Contact();
        System.out.print("First Name : ");
        contact.setFirstName(scanner.next());
        duplicate(contact.getFirstName());
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
        ContactsArrayList.add(contact);
    }
    public void editContact() {

        System.out.println("Enter the first name of the contact you want to edit: ");
        String name = scanner.next();

        for (Contact contacts : ContactsArrayList) {
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
        for (int i = 0; i < ContactsArrayList.size(); i++) {

            if (ContactsArrayList.get(i).getFirstName().equals(name)) {
                ContactsArrayList.remove(i);
                System.out.println("List After deletion \n" + ContactsArrayList);

            } else {
                System.out.println("The contact is not found!");
            }
        }
    }

    public void duplicate(String firstName) {
        for (Contact contacts : ContactsArrayList) {
            String contactName = contacts.getFirstName();
            if (firstName.equals(contactName)) {
                System.out.println("This Person is Already Present");
                addAddress();
            }
        }
    }

    public List<Contact> searchByCity(String city) {
        return ContactsArrayList.stream().filter(person -> person.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
    public List<Contact> searchByState(String state) {
        return ContactsArrayList.stream().filter(person -> person.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
    public static void viewByCity(Map<String, Contact> cityHashMap) {
        cityHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public static void viewByState(Map<String, Contact> stateHashMap) {
        stateHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public void searchByOptions() {
        System.out.println("1.By city");
        System.out.println("2.By state");
        System.out.println("3.exit");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter city: ");
                String city = scanner.nextLine();
                ContactsArrayList.forEach(book -> searchByCity(city).forEach(System.out::println));
                break;
            case 2:
                System.out.print("Enter state: ");
                String state = scanner.nextLine();
                ContactsArrayList.forEach(book -> searchByState(state).forEach(System.out::println));
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
    public static void viewBy() {
        System.out.print("Enter Your choice: \n");
        System.out.println("1. View By city");
        System.out.println("2. View By state");
        System.out.println("3. exit");
        int choice1 = scanner.nextInt();
        scanner.nextLine();
        switch (choice1) {
            case 1:
                 viewByCity(cityHashMap);
                 break;
            case 2:
                viewByState(stateHashMap);
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
    public void countPeopleByRegion(HashMap<String, ArrayList<Contact>> listToDisplay) {

        System.out.println("Enter the name of the region :");
        String regionName = scanner.next();

        long countPeople = listToDisplay.values().stream()
                .map(region -> region.stream()
                        .filter(person -> person.getState().equals(regionName) || person.getCity().equals(regionName))).count();

        System.out.println("Number of People residing in " + regionName+" are: "+countPeople+"\n");

    }

    public void display() {
        System.out.println(ContactsArrayList);
    }
    public void createAddressBook() {

        while (flag) {
            System.out.println("What do you want to do: ");
            System.out.print("1.Create new address book\n2.Edit existing address book\n3.Display all address book\n4.Exit : ");
            int choose = scanner.nextInt();
            boolean flag1 = true;
            switch (choose) {
                case 1 -> {
                    System.out.print("Enter the name of address book: ");
                    String address_name = scanner.next();
                    if (hashmap.containsKey(address_name)) {
                        System.out.println("Address book name exist, Enter different name");
                        break;
                    }
                    ContactsArrayList = new ArrayList<>();

                    while (flag1) {
                        System.out.println("What do you want to do: ");
                        System.out.print("1.Add details\n2.Edit details\n3.Delete contact\n4.Display Contact\n5.Search By\n6.View By" +
                                "\n7.Count by region \n8.Exit : ");
                        int choose1 = scanner.nextInt();

                        switch (choose1) {
                            case 1 -> details.addAddress();
                            case 2 -> details.editContact();
                            case 3 -> details.deleteContact();
                            case 4 -> details.display();
                            case 5 -> details.searchByOptions();
                            case 6 -> details.viewBy();
                            case 7 -> details.countPeopleByRegion(hashmap);
                            case 8 -> {
                                System.out.println("Thank You!");
                                flag1 = false;
                            }
                            default -> System.out.println("Choose valid option");
                        }
                        hashmap.put(address_name, ContactsArrayList);
                    }
                }
                case 2 -> {
                    System.out.println("Enter the name of address book: ");
                    String address_name_old = scanner.next();
                    if (hashmap.containsKey(address_name_old)) {

                        ContactsArrayList = new ArrayList<>();
                        ContactsArrayList = hashmap.get(address_name_old);
                        boolean j = true;
                        while (j) {
                            System.out.println("What do you want to do: ");
                            System.out
                                    .print("1.Add details\n2.Edit details\n3.Delete contact\n4.Display contact\n5.Exit : ");
                            int choose1 = scanner.nextInt();
                            switch (choose1) {
                                case 1 -> details.addAddress();
                                case 2 -> details.editContact();
                                case 3 -> details.deleteContact();
                                case 4 -> details.display();
                                case 5 -> {
                                    System.out.println("Thank you!");
                                    j = false;
                                }
                                default -> System.out.println("Choose valid option");
                            }
                            hashmap.put(address_name_old, ContactsArrayList);
                            System.out.println(hashmap);
                        }
                        flag = false;
                    } else {
                        System.out.println("Enter valid address book name");
                    }
                }
                case 3 -> {
                    System.out.println(hashmap);
                    System.out.println();
                }
                case 4 -> {
                    flag = false;
                }
                default -> System.out.println("Enter valid option");
            }
        }
    }
}
