package edu.bu.met.cs665.customerdata;

import org.junit.Assert;
import org.junit.Test;

/**
 * The purpose of this class is to Test the concept of the Old Data Management
 * System with the New System. Both concepts are encapsulated in a single
 * Object, however this is obscured by the fact that this object has been
 * "re-factored" to support the additional data storage of a phone number.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class CustomerTest {

  public CustomerTest() {}

  /**
   * Test Old Customer Data System.
   */
  @Test
  public void TestOldCustomer() {

    String email = "me@me.com";
    // Instantiate empty customer
    Customer customer = new Customer();
    // add email
    customer.setEmail(email);
    Assert.assertEquals(customer.getEmail(), email);
  }

  /**
   * Test New Customer Data System.
   */
  @Test
  public void TestNewCustomer() {
    String email = "me@me.com";
    String phoneNumber = "123-456-7890";

    // Instantiate customer but with email as Old System
    Customer customer = new Customer(email);
    // Give the customer a Phone Number
    customer.setPhoneNumber(phoneNumber);
    // Test customer can store phone number
    Assert.assertEquals(customer.getPhoneNumber(), phoneNumber);
  }
}