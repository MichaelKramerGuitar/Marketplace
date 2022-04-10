package edu.bu.met.cs665.orderprocessing;

import edu.bu.met.cs665.customerdata.Customer;
import edu.bu.met.cs665.customerdata.CustomerAdapter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class OrderTest {


  private String email = "somecustomer@gmail.com";
  private String phoneNumber = "234-567-8901";
  private CustomerAdapter customerAdapter;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  public OrderTest() {}

  @Before
  public void setUp() {

    this.customerAdapter = new CustomerAdapter(new Customer(email, phoneNumber));
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @After
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  public void TestCustomerSubscribedToOrderState() {
    System.setOut(new PrintStream(outputStreamCaptor));
    Order order = new Order(); // create order
    order.subscribe(customerAdapter); // ensure customer receives order updates
    order.setState(new PackagedOrder()); // order is being packaged
    Assert.assertEquals("The latest order state was received by "
        + email + ": " + order.getState() + ""
        .replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    System.setOut(standardOut);
  }

  @Test(expected = IllegalStateException.class)
  public void TestOrderStateCannotTransitionFromDeliveredToPackaged() throws IllegalStateException {
      Order order = new Order(); // create order
      order.subscribe(customerAdapter); // ensure customer receives order updates
      order.setState(new PackagedOrder()); // order is being packaged
      order.packageOrder();
      order.packageOrder();
      order.setState(new DeliverOrder()); // order is being delivered
      order.packageOrder(); // throws Illegal state -> order has already been packed
  }

  @Test
  public void TestCustomerGetsLatestUpdateOfOrderState() {

    System.setOut(new PrintStream(outputStreamCaptor));
    Order order = new Order(); // create order
    order.subscribe(customerAdapter); // ensure customer receives order updates
    order.setState(new PackagedOrder()); // order is being packaged
    order.setState(new PickupOrder());
    Assert.assertEquals("The latest order state was received by "
        + email + ": {PACKAGING ORDER}"
        + "The latest order state was received by "
        + email + ": " + order.getState() + ""
        .replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    System.setOut(standardOut);
  }

  @Test(expected = IllegalStateException.class)
  public void TestOrderStateCannotTransitionFromDeliveredToPickedUp() throws IllegalStateException {
    Order order = new Order(); // create order
    order.subscribe(customerAdapter); // ensure customer receives order updates
    order.setState(new PackagedOrder()); // order is being packaged
    order.packageOrder();
    order.setState(new DeliverOrder()); // order is being delivered
    order.pickupOrder(); // throws Illegal state
  }

  @Test(expected = IllegalStateException.class)
  public void TestOrderStateCannotTransitionFromPickedUpToDelivered() throws IllegalStateException {
    Order order = new Order(); // create order
    order.subscribe(customerAdapter); // ensure customer receives order updates
    order.setState(new PackagedOrder()); // order is being packaged
    order.packageOrder();
    order.setState(new PackagedOrder()); // order is being delivered
    order.deliverOrder(); // throws Illegal state
  }
}