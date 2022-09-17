import java.util.*;
import java.util.stream.Collectors;

public class AddressBookClass {
    public static Scanner sc = new Scanner(System.in);
    private static AddressBook addressBook = new AddressBook();
    public Map<String, AddressBook> addressBookListMap = new HashMap<>();
    int count;

    public void addAddressBook(String bookName) {
        boolean flag = true;
        while (flag) {

            System.out.println("1.Add Contact");
            System.out.println("2.Edit Contact");
            System.out.println("3.Delete");
            System.out.println("4.Display");
            System.out.println("5.Exit");
            System.out.println("Enter Choice: ");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    addressBook.addAddress();
                    count++;
                    break;

                case 2:
                    addressBook.editContact();
                    break;

                case 3:
                    addressBook.deleteContact();
                    break;

                case 4:
                    addressBook.display();
                    break;

                case 5:
                    flag = false;
                    break;
            }
        }
        addressBookListMap.put(bookName, addressBook);
        System.out.println("Address Book Added Successfully");
    }

    private void searchPersonByState(String stateName) {
        if (count > 0) {
            for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
                AddressBook value = entry.getValue();
                System.out.println("The Address Book: " + entry.getKey());
                value.getPersonNameByState(stateName);
            }
        } else {
            System.out.println("Empty address book");
        }
    }

    private void searchPersonByCity(String cityName) {
        if (count > 0) {
            for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
                AddressBook value = entry.getValue();
                System.out.println("The Address Book: " + entry.getKey());
                value.getPersonNameByCity(cityName);
            }
        } else {
            System.out.println("Empty address book");
        }
    }

    private void viewPersonByState(String stateName) {
        if (count > 0) {
            for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
                AddressBook value = entry.getValue();
                ArrayList<Contact> contacts = value.personByState.entrySet().stream()
                        .filter(findState -> findState.getKey().equals(stateName)).map(Map.Entry::getValue).findFirst()
                        .orElse(null);
                for (Contact contact : contacts) {
                    System.out.println("First Name: " + contact.getFirstName() + " Last Name: " + contact.getLastName());
                }
            }
        } else {
            System.out.println("Empty address book");
        }
    }

    private void viewPersonByCity(String cityName) {
        if (count > 0) {
            for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
                AddressBook value = entry.getValue();
                ArrayList<Contact> contacts = value.personByCity.entrySet().stream()
                        .filter(findCity -> findCity.getKey().equals(cityName)).map(Map.Entry::getValue).findFirst()
                        .orElse(null);
                for (Contact contact : contacts) {
                    System.out.println("First Name: " + contact.getFirstName() + " Last Name: " + contact.getLastName());
                }
            }
        } else {
            System.out.println("Empty address book");
        }
    }

    public void CountByState(String state) {
        int countPersonByState = 0;
        if (count > 0) {
            for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
                for (int i = 0; i < (entry.getValue()).persons.size(); i++) {
                    Contact contact = entry.getValue().persons.get(i);

                    if (state.equals(contact.getState())) {
                        countPersonByState++;
                    }
                }
            }
            System.out.println("Total Person Count in state " + state + ": " + count);
        } else {
            System.out.println("Empty address book");
        }
    }

    public void CountByCity(String city) {
        int countPersonInCity = 0;
        if (count > 0) {
            for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
                for (int i = 0; i < (entry.getValue()).persons.size(); i++) {
                    Contact d = entry.getValue().persons.get(i);

                    if (city.equals(d.getCity())) {
                        countPersonInCity++;
                    }
                }
            }
            System.out.println("Total number of people in this city " + city + ": " + countPersonInCity);
        } else {
            System.out.println("Empty address book");
        }
    }

    private void sortByName() {
        if (count > 0) {

            for (Map.Entry<String, AddressBook> entry : addressBookListMap.entrySet()) {
                AddressBook value = entry.getValue();
                List<Contact> sortedList = value.persons.stream().sorted(Comparator.comparing(Contact::getFirstName)).collect(Collectors.toList());

                for (Contact contact : sortedList) {
                    System.out.println( contact.getFirstName());
                }
            }
        } else {
            System.out.println("Empty address book");
        }
    }


    public static void main(String[] args) {
        System.out.println("Welcome to the Address Book Management System using Java Stream");
        AddressBookClass addressBookClass = new AddressBookClass();
        boolean flag = true;
        while (flag) {
            while (flag) {
                System.out.println("1.Add New Address Book");
                System.out.println("2.Add,delete,display existing contact");
                System.out.println("3.Search Contact from a city");
                System.out.println("4.Search Contact from a State");
                System.out.println("5.View contact By State Using State and Person HashMap");
                System.out.println("6.View Contact by city Using City and Person HashMap");
                System.out.println("7.Count Contact By State");
                System.out.println("8.Count Contact By City");
                System.out.println("9.Sort Contact By Name");
                System.out.println("10.Exit");

                System.out.println("Enter choice: ");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter the Name of Address Book: ");
                        String addressBookName = sc.next();
                        if (addressBookClass.addressBookListMap.containsKey(addressBookName)) {
                            System.out.println("The Address book Already Exists");
                            break;
                        } else {
                            addressBookClass.addAddressBook(addressBookName);
                            break;
                        }
                    case 2:
                        System.out.println("Enter Name of book you want to edit: ");
                        String addBook = sc.next();
                        addressBookClass.addAddressBook(addBook);
                        break;

                    case 3:
                        System.out.println("Enter Name of City: ");
                        String CityName = sc.next();
                        addressBookClass.searchPersonByCity(CityName);
                        break;

                    case 4:
                        System.out.println("Enter Name of State: ");
                        String StateName = sc.next();
                        addressBookClass.searchPersonByState(StateName);
                        break;

                    case 5:
                        System.out.println("Enter Name of State: ");
                        String stateName1 = sc.next();
                        addressBookClass.viewPersonByState(stateName1);
                        break;

                    case 6:
                        System.out.println("Enter Name of City: ");
                        String cityName1 = sc.next();
                        addressBookClass.viewPersonByCity(cityName1);
                        break;

                    case 7:
                        System.out.println("Enter Name of State: ");
                        String stateName2 = sc.next();
                        addressBookClass.CountByState(stateName2);
                        break;

                    case 8:
                        System.out.println("Enter Name of City: ");
                        String cityName2 = sc.next();
                        addressBookClass.CountByCity(cityName2);
                        break;

                    case 9:
                        System.out.println("sorted contacts are :");
                        addressBookClass.sortByName();
                        break;

                    case 10:
                        flag = false;
                        break;
                }
            }

        }
    }
}
