package edu.bu.met.cs665.orderprocessing;

/**
 * The purpose of this class is to be a concrete Order in the state of
 * being packaged. It is not yet ready to be picked up or delivered.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class PackagedOrder implements OrderState {


  @Override
  public void packageOrder() {
    System.out.println("Packaging Order...");
  }

  @Override
  public void pickupOrder() throws IllegalStateException {
    throw new IllegalStateException(
        "Error: Order is being packaged will be ready for pickup soon...");
  }

  @Override
  public void deliverOrder() throws IllegalStateException {
    throw new IllegalStateException(
        "Error: Order is being packaged will be ready for delivery soon...");
  }

  public String toString() {
    return "{PACKAGING ORDER}";
  }
}
