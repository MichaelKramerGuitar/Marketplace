package edu.bu.met.cs665;

import edu.bu.met.cs665.customerdata.Customer;
import edu.bu.met.cs665.customerdata.CustomerAdapter;
import edu.bu.met.cs665.dataprocessing.CustomerDataProcessing;
import edu.bu.met.cs665.orderprocessing.Order;
import edu.bu.met.cs665.orderprocessing.PackagedOrder;
import org.apache.log4j.Logger;
// import org.apache.log4j.PropertyConfigurator;

public class Main {

  private static Logger logger = Logger.getLogger(Main.class);


  /**
   * A main method to run examples.
   *
   * @param args not used
   */
  public static void main(String[] args) {

    String email = "me@me.com";
    String phoneNumber = "123-456-7890";
    String email1 = "you@gmail.com";
    String phoneNumber1 = "234-567-8901";

    // Make a customer adapter instantiating Customer with only email (old system)
    CustomerAdapter adapter = new CustomerAdapter(new Customer(email));
    Customer customer = adapter.getCustomer(email, phoneNumber); // include phone # in lookup
    System.out.println(customer); // returns old system, only email

    // Make a customer adapter instantiating Customer with email and phone number, new system)
    CustomerAdapter adapter1 = new CustomerAdapter(new Customer(email1, phoneNumber1));
    Customer customer1 = adapter1.getCustomer(email1, phoneNumber1); // include phone # in lookup
    System.out.println(customer1); // returns old system, only email

    CustomerDataProcessing processor = new CustomerDataProcessing(adapter1);
    // load processor with tasks
    processor.performEmailVerification();
    processor.performBackgroundCheck();
    processor.sendWelcomeEmail();
    processor.removeProcess(processor.verifyEmail()); // remove function from hook
    // FIFO
    processor.process();

    System.out.println();
    CustomerDataProcessing processor1 = new CustomerDataProcessing(adapter);
    // load again for fun, different customer
    processor1.sendRejectionEmail();
    processor1.performBackgroundCheck();
    processor1.sendWelcomeEmail();
    processor1.performEmailVerification();
    // Check FIFO again
    processor1.process();
    System.out.println();
    processor1.process();

    System.out.println();
    Order order = new Order(); // create order
    order.subscribe(adapter); // ensure customer receives order updates
    adapter.placeOrder(order); // customer places order
    order.setState(new PackagedOrder()); // order is being packaged
  }

}
