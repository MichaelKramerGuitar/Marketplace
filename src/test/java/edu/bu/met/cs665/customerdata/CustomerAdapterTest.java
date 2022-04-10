package edu.bu.met.cs665.customerdata;

import org.junit.Assert;
import org.junit.Test;

/**
 * The purpose of this class is to test the Adapter Pattern for Customer
 * and Customer Adapter - this pattern was chosen to tackle the Customer
 * Data Access Requirements in the Application Description.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class CustomerAdapterTest {

  public CustomerAdapterTest() {}

  /**
   * Test Customer Adapter Discards Phone Number for Customer Object as per the
   * Old system.
   */
  @Test
  public void TestCustomerAdapterDiscardsPhoneNumber() {
    String email = "me@me.com";
    String phoneNumber = "123-456-7890";

    // Instantiate a CustomerAdapter with a Customer who has an email and phone
    CustomerAdapter adapter = new CustomerAdapter(new Customer(email, phoneNumber));
    // use adapter to return new customer
    Customer customer = adapter.getCustomer(email, phoneNumber);
    // expect the adapter to discard the phone number (New System).
    Assert.assertEquals(null, customer.getPhoneNumber());
  }

  /**
   * Test whether adapter works not just on New Customers (with phone number)
   * but old as well (with no phone number).
   */
  @Test
  public void TestGetCustomerWorksWhenPhoneNumberIsNull() {

    String email = "me@me.com";
    String phoneNumber = "123-456-7890";

    // Instantiate a CustomerAdapter with a Customer who has an email but no phone
    //OLD SYSTEM STYLE
    CustomerAdapter adapter = new CustomerAdapter(new Customer(email));
    // use adapter to return new customer
    Customer customer = adapter.getCustomer(email, phoneNumber);
    // expect the adapter to discard the phone number (New System).
    Assert.assertEquals(email, customer.getEmail());
    Assert.assertEquals(customer, adapter.getCustomer(email, phoneNumber));
  }

  /**
   * Test New Customer system functions.
   */
  @Test
  public void TestNewSystemCustomerInstantiation() {

    String email = "me@me.com";
    String phoneNumber = "123-456-7890";
    Customer customer = new Customer(email, phoneNumber);

    // Test new system works well
    Assert.assertEquals(customer.getPhoneNumber(), phoneNumber);
    Assert.assertEquals(customer.getEmail(), email);

    // Covert the customer to a customer adapter
    CustomerAdapter adapter = new CustomerAdapter(customer);
    // Test adapter throws away phone number
    customer = adapter.getCustomer(email, phoneNumber);
    // expect the adapter to discard the phone number (New System).
    Assert.assertEquals(null, customer.getPhoneNumber());

  }
}