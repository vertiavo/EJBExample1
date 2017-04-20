package wipb.ee.std.demo.clientapp;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import wipb.jee.clientdemo.ejb.MessageBeanRemote;

import java.util.Scanner;

/**
 *
 * @author dr
 */
public class StandAloneClient {

    public static void main(String[] args) throws NamingException {
        System.out.println("Initializing remote EJB bean...");
        InitialContext ic = new InitialContext();
        // pobranie referencji do ejb (proxy) do komponentu EJB z usługi katalogowej JNDI serwera aplikacyjnego
        MessageBeanRemote ejb = (MessageBeanRemote) ic.lookup("java:global/ee-std-client-demo-ear-1.0/ee-std-client-demo-ejb-1.0/MessageBean!wipb.jee.clientdemo.ejb.MessageBeanRemote");
        // wywołanie metody ejb z serwera aplikacyjnego                            
        String result = ejb.getMessage();
        System.out.println("Remote message result: "+result);

        Scanner scanner = new Scanner(System.in);
        String key;
        Integer value, data;

        while(true) {
            System.out.println("1 - Adding new values.\n" +
                    "2 - Deleting values.\n" +
                    "3 - Displaying value stored in cache.\n" +
                    "Other - Exit.");
            switch(Integer.parseInt(scanner.nextLine())) {
                // Dodawanie wartości
                case 1:
                    System.out.print("Enter new key: ");
                    key = scanner.nextLine();
                    System.out.print("\nEnter new data: ");
                    value = Integer.parseInt(scanner.nextLine());
                    System.out.println("Adding new data");
                    ejb.put(key, value);
                    break;

                // Usuwanie wartości
                case 2:
                    System.out.print("Enter key: ");
                    key = scanner.nextLine();
                    data = ejb.get(key);

                    if (data == null) {
                        System.out.println("\nNo data for key: " + key);
                        break;
                    } else {
                        System.out.println("\nDeleting key: " + key + " data: " + data);
                        ejb.remove(key);
                        break;
                    }

                    // Wyświetlenie wartości
                case 3:
                    System.out.print("Enter key: ");
                    key = scanner.nextLine();
                    data = ejb.get(key);

                    if (data == null) {
                        System.out.println("\nNo data for key: " + key);
                        break;
                    } else {
                        System.out.println("\nInfo - key: " + key + " data: " + data);
                        break;
                    }

                default:
                    return;
            }
            System.out.println("********************************************");
        }

    }
}
