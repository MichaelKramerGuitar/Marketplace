package edu.bu.met.cs665.dataprocessing;

import edu.bu.met.cs665.customerdata.CustomerAdapter;
import java.util.function.Function;
import java.util.regex.Pattern;

public class CustomerDataProcessing extends DataProcessing {

  private CustomerAdapter customer;

  public CustomerDataProcessing(CustomerAdapter customer) {
    this.customer = customer;
  }

  @Override
  public Function verifyEmail() {
    boolean validEmail = validEmailAddress(customer.getCustomerEmail());
    if (!validEmail) {
      System.out.println("Customer email is invalid.");
    } else {
      System.out.println("Customer email is valid: "
          + getCustomer().getCustomerEmail());
    }
    return null;
  }

  @Override
  public Function backgroundCheck() {
    // Fancy background checking algorithm
    System.out.println("Conducting background check on customer with email: "
        + getCustomer().getCustomerEmail());
    return null;
  }

  @Override
  public Function generateWelcomeEmail() {
    System.out.println("Sending customer welcome email to: "
        + getCustomer().getCustomerEmail());
    return null;
  }

  @Override
  public Function generateRejectionEmail() {
    System.out.println("Sending customer rejection email to: "
        + getCustomer().getCustomerEmail());
    return null;
  }

  public CustomerAdapter getCustomer() {
    return this.customer;
  }

  /**
   * The purpose of this method is to validate email addresses. Inspired by
   * https://www.geeksforgeeks.org/check-email-address-valid-not-java/
   *
   * @param emailAddress String email address
   * @return
   */
  public static boolean validEmailAddress(String emailAddress) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
        + "[a-zA-Z0-9_+&*-]+)*@"
        + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
        + "A-Z]{2,7}$";

    Pattern pat = Pattern.compile(emailRegex);
    if (emailAddress == null) {
      return false;
    }
    return pat.matcher(emailAddress).matches();
  }

}
