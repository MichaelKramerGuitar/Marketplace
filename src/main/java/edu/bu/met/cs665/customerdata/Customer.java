package edu.bu.met.cs665.customerdata;


/**
 * The purpose of this class is to represent the Legacy systems' representation of
 * a customer.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class Customer implements CustomerDataOld {

  private String email;
  private String phoneNumber; // Only New System Customers will have

  // Constructors
  public Customer() {}

  /**
   * The purpose of this constructor is for Legacy System Customers.
   * @param email String email address (ex: me@gmail.com)
   */
  public Customer(String email) {
    this.email = email;
  }

  /**
   * The purpose of this constructor is for New System Customers Only.
   * @param email String email address (ex: me@gmail.com)
   * @param phoneNumber String phone number (ex: 123-456-7890)
   */
  public Customer(String email, String phoneNumber) {
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  // Getters

  /**
   * From Old System.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * New System Only.
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  //Setters

  /**
   * From old system.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * New System Only.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Overrides Old System Data interface, email only.
   * @param email look up Customer objects with email address only.
   * @return this Customer object.
   */
  @Override
  public Customer getCustomer(String email) {
    System.out.println("Looking up Customer with Old System...");
    if (this.phoneNumber != null) {
      System.out.println("Dropping phone number from Customer Data...");
      setPhoneNumber(null);
    }
    return this;
  }

  /**
   * The purpose of this method is for printing and it handles both Old and New
   * System customers.
   * @return
   */
  public String toString() {
    if (phoneNumber == null) { // Old System
      return email;
    }
    return email + " " + phoneNumber; // New System
  }

}
