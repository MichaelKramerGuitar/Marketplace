package edu.bu.met.cs665.orderprocessing;

/**
 * The purpose of this class is to be a Concrete Order in the state of Delivery.
 * A delivered order has already been packaged and cannot be picked up.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class DeliverOrder implements OrderState {

  @Override
  public void packageOrder() throws IllegalStateException {
    throw new IllegalStateException("Error: Order has already been packaged.");
  }

  @Override
  public void pickupOrder() throws IllegalStateException {
    throw new IllegalStateException("Error: Order is being delivered, not picked up.");
  }

  @Override
  public void deliverOrder() {
    System.out.println("Order is being delivered, see you soon!");
  }

  public String toString() {
    return "{DELIVERING ORDER}";

  }

}
