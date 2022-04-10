package edu.bu.met.cs665.orderprocessing;

/**
 * The purpose of this class is to be the Interface to inherit from in the
 * State Pattern for an Order State.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public interface OrderState {

  public void packageOrder() throws IllegalStateException;

  public void pickupOrder() throws IllegalStateException;

  public void deliverOrder() throws IllegalStateException;

}
