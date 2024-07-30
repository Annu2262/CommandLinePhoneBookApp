package Phone;
import java.util.Scanner;
class Contact {
   String name;
   String phoneNumber;
   public Contact(String name, String phoneNumber) {
       this.name = name;
       this.phoneNumber = phoneNumber;
   }
}
class Node {
   Contact data;
   Node next;
   public Node(Contact data) {
       this.data = data;
       this.next = null;
   }
}
class MyLinkedList {
   Node head;
  
   public void insert(Contact contact) {
       Node newNode = new Node(contact);
       if (head == null || contact.name.compareTo(head.data.name) < 0) {
           newNode.next = head;
           head = newNode;
       } else {
           Node current = head;
           while (current.next != null && contact.name.compareTo(current.next.data.name) > 0) {
               current = current.next;
           }
           newNode.next = current.next;
           current.next = newNode;
       }
   }
   public void display() {
       Node current = head;
       while (current != null) {
           System.out.println(current.data.name + ": " + current.data.phoneNumber);
           current = current.next;
       }
   }
   public void delete(String name) {
       if (head == null) {
           System.out.println("Phone book is empty. No contacts to delete.");
           return;
       }
       if (head.data.name.equals(name)) {
           head = head.next;
           System.out.println("Contact deleted successfully!\n");
           return;
       }
       Node current = head;
       while (current.next != null && !current.next.data.name.equals(name)) {
           current = current.next;
       }
       if (current.next == null) {
           System.out.println("Contact not found for: " + name + "\n");
       } else {
           current.next = current.next.next;
           System.out.println("Contact deleted successfully!\n");
       }
   }
}
class PhoneBook extends MyLinkedList {
   public Contact binarySearch(String name) {
       Node current = head;
       while (current != null) {
           int compareResult = name.compareTo(current.data.name);
           if (compareResult == 0) {
               return current.data;
           } else if (compareResult < 0) {
               return null;
           }
           current = current.next;
       }
       return null;
   }
}
class askInput {
   Scanner sc = new Scanner(System.in);
   public String enterPhone() {
       String phoneNumber = sc.next().trim();
       if (!checkPhoneNumber(phoneNumber)) {
           System.out.println("Invalid phone number! "+"\nPlease Type Valid Phone Number :");
           return enterPhone();
       }
       return phoneNumber;
   }
   public String enterName() {
       String name = sc.nextLine().toLowerCase().trim();
       if (!checkName(name)) {
           System.out.println("Name Can Contain Only Alphabets! ");
           return enterName();
       }
       return name;
   }
   public boolean checkName(String name) {
       for (int i = 0; i < name.length(); i++) {
           if (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
               return true;
           } else {
               return false;
           }
       }
       return true;
   }
   public static boolean checkPhoneNumber(String s) {
       if (s.length() != 10) {
           return false;
       } else if (s.startsWith("9") || s.startsWith("8") || s.startsWith("7")) {
           for (int i = 0; i < s.length(); i++) {
               if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                   continue;
               } else {
                   return false;
               }
           }
           return true;
       }
       return false;
   }
}
public class PhoneBookApp {
   public static void main(String[] args) {
       PhoneBook phoneBook = new PhoneBook();
       Scanner sc = new Scanner(System.in);
       while (true) {
           System.out.println("Phone Book Menu:");
           System.out.println("\t1. Add Contact");
           System.out.println("\t2. View All Contacts");
           System.out.println("\t3. Search Contacts");
           System.out.println("\t4. Delete Contacts");
           System.out.println("\t5. Exit");
           System.out.print("\nChoose an option: ");
           try {
               int choice = sc.nextInt();
               sc.nextLine();
               switch (choice) {
                   case 1:
                       System.out.print("Enter contact name: ");
                       String name = new askInput().enterName();
                       System.out.print("Enter contact phone number: ");
                       String phoneNumber = new askInput().enterPhone();
                       Contact newContact = new Contact(name, phoneNumber);
                       phoneBook.insert(newContact);
                       System.out.println("Contact added successfully!\n");
                       break;
                   case 2:
                       System.out.println("Phone Book Directory:");
                       phoneBook.display();
                       System.out.println();
                       break;
                   case 3:
                       System.out.print("Enter contact name to search: ");
                       String searchName = sc.nextLine().toLowerCase().trim();
                       Contact result = phoneBook.binarySearch(searchName);
                       try {
                           System.out.println("Searching.");
                           for (int i = 0; i < 5; i++) {
                               System.out.print(".");
                               Thread.sleep(500);
                           }
                           System.out.println();
                       } catch (Exception e) {
                           System.out.println("Exception Occured");
                       }
                       if (result != null) {
                           System.out.println("Contact found: " + result.phoneNumber + "\n");
                       } else {
                           System.out.println("Contact not found for: " + searchName + "\n");
                       }
                       break;
                   case 4:
                   	System.out.print("Enter contact name to delete: ");
                       String deleteName = sc.nextLine().toLowerCase().trim();
                       phoneBook.delete(deleteName);
                       break;
                   case 5:
                       System.out.println("Exiting Phone Book. Goodbye!");
                       return;
                   default:
                       System.out.println("OOps !! Invalid  option. Please choose a valid option.\n");
                       break;
               }
           } catch (Exception e) {
               System.out.println("Enter Valid Input");
               sc.nextLine();
           }
       }
   }
}
