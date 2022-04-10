package edu.bu.met.cs665.orderprocessing;

/**
 * The purpose of this class is to be a concrete Order to be picked up. It
 * has already been packaged and cannot be delivered.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class PickupOrder implements OrderState {

  @Override
  public void packageOrder() throws IllegalStateException {
    throw new IllegalStateException("Error: Order has already been packaged.");
  }

  @Override
  public void pickupOrder() {
    System.out.println("Order is ready for pickup, see you soon!");
  }

  @Override
  public void deliverOrder() throws IllegalStateException {
    throw new IllegalStateException("Error: Order is being picked up, not delivered.");
  }

  public String toString() {
    return "{READY FOR PICKUP}";
  }
}
