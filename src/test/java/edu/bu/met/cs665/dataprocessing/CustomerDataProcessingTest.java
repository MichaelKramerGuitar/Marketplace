package edu.bu.met.cs665.dataprocessing;

import edu.bu.met.cs665.customerdata.Customer;
import edu.bu.met.cs665.customerdata.CustomerAdapter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * The purpose of this class is to test the Customer Data Processing System,
 * Item 2 of the Application Description.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class CustomerDataProcessingTest {

  private String email = "somecustomer@gmail.com";
  private String phoneNumber = "234-567-8901";
  private CustomerAdapter customerAdapter;
  private CustomerDataProcessing processor;
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  public CustomerDataProcessingTest() {}

  @Before
  public void setUp() {

    this.customerAdapter = new CustomerAdapter(new Customer(email, phoneNumber));
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @After
  public void tearDown() {
    System.setOut(standardOut);
  }

  /**
   * Test Email Verification Performed in Data Processor.
   */
  @Test
  public void TestCustomerDataProcessingPerformsEmailVerification() {

    processor = new CustomerDataProcessing(customerAdapter);
    processor.performEmailVerification(); // add verifyEmail to Queue
    processor.process(); // start popping processes off the queue FIFO
    // Will validate email and notify that the processing queue is empty
    Assert.assertEquals("Customer email is valid: " + email +
      "\nprocessing queue is empty".replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
  }

  /**
   * Test Email Verification notices bad email.
   */
  @Test
  public void TestCustomerDataProcessingKicksBackInvalidEmail() {
    System.setOut(new PrintStream(outputStreamCaptor));
    String badEmail = "invalid.email.com";
    CustomerAdapter custAdapt = new CustomerAdapter(new Customer(badEmail));
    CustomerDataProcessing newProcessor = new CustomerDataProcessing(custAdapt);
    newProcessor.performEmailVerification(); // add verifyEmail to Queue
    Assert.assertEquals("Customer email is invalid."
        .replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    System.setOut(standardOut);
  }

  /**
   * Test background check customer data processing.
   */
  @Test
  public void TestBackGroundCheckCustomerDataProcessing() {
    System.setOut(new PrintStream(outputStreamCaptor));
    CustomerAdapter custAdapt = new CustomerAdapter(new Customer(email));
    CustomerDataProcessing newProcessor = new CustomerDataProcessing(custAdapt);
    newProcessor.performBackgroundCheck(); // add background check to Queue
    newProcessor.process(); // start popping processes off the queue FIFO
    Assert.assertEquals("Conducting background check on customer with email: "
        + email +
        "\nprocessing queue is empty"
          .replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    System.setOut(standardOut);

  }

  /**
   * Test welcome email is sent with Customer Data Processor.
   */
  @Test
  public void TestCustomerDataProcessingSendsWelcomeEmail() {
    System.setOut(new PrintStream(outputStreamCaptor));
    // add phone for fun
    CustomerAdapter custAdapt = new CustomerAdapter(new Customer(email, phoneNumber));
    CustomerDataProcessing newProcessor = new CustomerDataProcessing(custAdapt);
    newProcessor.generateWelcomeEmail(); // add welcome email to Queue
    newProcessor.process(); // start popping processes off the queue FIFO
    Assert.assertEquals("Sending customer welcome email to: "
        + email +
        "\nprocessing queue is empty"
          .replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    System.setOut(standardOut);
  }

  /**
   * Test rejection email sent by customer data processor.
   */
  @Test
  public void TestCustomerDataProcessingSendsRejectionEmail() {
    System.setOut(new PrintStream(outputStreamCaptor));
    // add phone for fun
    CustomerAdapter custAdapt = new CustomerAdapter(new Customer(email, phoneNumber));
    CustomerDataProcessing newProcessor = new CustomerDataProcessing(custAdapt);
    newProcessor.generateRejectionEmail(); // add rejection email to Queue
    newProcessor.process(); // start popping processes off the queue FIFO
    Assert.assertEquals("Sending customer rejection email to: "
        + email +
        "\nprocessing queue is empty"
          .replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    System.setOut(standardOut);
  }

  @Test
  public void TestMultipleProcessesCanBeAddedAtWillToQueue() {
    System.setOut(new PrintStream(outputStreamCaptor));
    // add phone for fun
    CustomerAdapter custAdapt = new CustomerAdapter(new Customer(email, phoneNumber));
    CustomerDataProcessing newProcessor = new CustomerDataProcessing(custAdapt);
    newProcessor.performEmailVerification(); // add rejection email to Queue
    newProcessor.performBackgroundCheck(); // add background check
    newProcessor.generateWelcomeEmail();
    newProcessor.process(); // start popping processes off the queue FIFO
    // Will validate email and notify that the processing queue is empty
    Assert.assertEquals("Customer email is valid: " + email
        + "Conducting background check on customer with email: " + email
        + "Sending customer welcome email to: " + email
        + "\nprocessing queue is empty"
          .replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    System.setOut(standardOut);

  }

  @Test
  public void TestProcessesCanBeRemovedFromQueueAtWill() {

    System.setOut(new PrintStream(outputStreamCaptor));
    CustomerAdapter custAdapt = new CustomerAdapter(new Customer(email));
    CustomerDataProcessing newProcessor = new CustomerDataProcessing(custAdapt);
    newProcessor.performBackgroundCheck(); // add background check to Queue

    // Queue should not be empty
    Assert.assertFalse(newProcessor.getProcessingFunctions().isEmpty());

    // We decided not to background check this customer
    newProcessor.removeProcess(newProcessor.backgroundCheck());

    // Queue should be empty
    Assert.assertTrue(newProcessor.getProcessingFunctions().isEmpty());
    // start popping processes off the queue FIFO, nothing happens empty Queue
    newProcessor.process();
    System.setOut(standardOut);

  }

  @Test
  public void TestRandomOrderAddingProcesses() {
    System.setOut(new PrintStream(outputStreamCaptor));
    // add phone for fun
    CustomerAdapter custAdapt = new CustomerAdapter(new Customer(email, phoneNumber));
    CustomerDataProcessing newProcessor = new CustomerDataProcessing(custAdapt);
    newProcessor.sendWelcomeEmail(); // Generate Welcome email

    newProcessor.performBackgroundCheck(); // add background check

    newProcessor.performEmailVerification(); // Verify Email

    newProcessor.sendRejectionEmail();
    newProcessor.process(); // start popping processes off the queue FIFO
    // Will validate email and notify that the processing queue is empty
    Assert.assertEquals("Sending customer welcome email to: " + email
        + "Conducting background check on customer with email: " + email
        + "Customer email is valid: " + email
        + "Sending customer rejection email to: " + email
        + "\nprocessing queue is empty"
        .replaceAll("\n", "").replaceAll("\r", ""),
      outputStreamCaptor.toString().trim().replaceAll("\n", "").replaceAll("\r", ""));
    System.setOut(standardOut);
  }

  @Test
  public void TestAddingAndRemovingFromQueueMultiple() {
    CustomerAdapter custAdapt = new CustomerAdapter(new Customer(email, phoneNumber));
    CustomerDataProcessing newProcessor = new CustomerDataProcessing(custAdapt);
    newProcessor.sendWelcomeEmail(); // Generate Welcome email

    newProcessor.performBackgroundCheck(); // add background check

    newProcessor.performEmailVerification(); // Verify Email

    newProcessor.sendRejectionEmail();

    // Queue should not be empty
    Assert.assertFalse(newProcessor.getProcessingFunctions().isEmpty());

    newProcessor.removeProcess(newProcessor.backgroundCheck());
    newProcessor.removeProcess(newProcessor.generateWelcomeEmail());
    newProcessor.removeProcess(newProcessor.generateRejectionEmail());
    newProcessor.removeProcess(newProcessor.verifyEmail());

    // Now should be empty
    Assert.assertTrue(newProcessor.getProcessingFunctions().isEmpty());
  }

}