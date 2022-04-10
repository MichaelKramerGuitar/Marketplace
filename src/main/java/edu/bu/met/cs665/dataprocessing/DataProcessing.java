package edu.bu.met.cs665.dataprocessing;

import java.util.LinkedList;
import java.util.function.Function;

/**
 * The purpose of this class is to apply the template pattern to processing
 * customer data.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public abstract class DataProcessing {

  // hook processing function container
  private LinkedList<Function> processingFunctions = new LinkedList<>(); // FIFO

  /**
   * The purpose of this method is to execute the methods in the FIFO
   * processingFunctions Queue. This is the work of the template method pattern.
   */
  public void process() { // template method
    // Options for processing can be easily added or removed from the chain
    while (!processingFunctions.isEmpty()) {
      for (int i = 0; i < processingFunctions.size(); i++) {
        processingFunctions.remove(i);
      }
    }
    System.out.println("processing queue is empty");
  }

  // Verifications to be dynamically added to
  public abstract Function verifyEmail();

  public abstract Function backgroundCheck();

  public abstract Function generateWelcomeEmail();

  public abstract Function generateRejectionEmail();

  // Hooks
  public void performEmailVerification() {
    this.processingFunctions.add(verifyEmail());
  }

  public void performBackgroundCheck() {
    this.processingFunctions.add(backgroundCheck());
  }

  public void sendWelcomeEmail() {
    this.processingFunctions.add(generateWelcomeEmail());
  }

  public void sendRejectionEmail() {
    this.processingFunctions.add(generateRejectionEmail());
  }

  /**
   * Removes a function from the queue.
   * @param function the function to remove from queue.
   */
  public void removeProcess(Function function) {
    this.processingFunctions.remove(function);
  }

  public LinkedList<Function> getProcessingFunctions() {
    return processingFunctions;
  }

}
