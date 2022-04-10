package edu.bu.met.cs665.orderprocessing;

import edu.bu.met.cs665.customerdata.CustomerAdapter;
import edu.bu.met.cs665.notifications.PublisherBase;
import edu.bu.met.cs665.notifications.SubscriberBase;

/**
 * The purpose of this class is both to be the concrete Publisher to CustomerAdapters
 * who've placed orders which need to be updated of the states of these orders.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */
public class Order implements PublisherBase {

  private OrderState packaged;
  private OrderState delivered;
  private OrderState pickedUp;
  private OrderState state;

  CustomerAdapter customer;

  /**
   * Constructor.
   */
  public Order() {
    this.packaged = new PackagedOrder();
    this.delivered = new DeliverOrder();
    this.pickedUp = new PickupOrder();
    this.state = packaged; // default
  }

  // State methods
  /**
   * Try to package the order.
   * @Throws IllegalStateException e
   */
  public void packageOrder() throws IllegalStateException {
    try {
      state.packageOrder();
    } catch (IllegalStateException e) {
      throw e;
    }
  }

  /**
   * Try to deliver the order.
   * @Throws IllegalStateException e
   */
  public void deliverOrder() throws IllegalStateException {
    try {
      state.deliverOrder();
    } catch (IllegalStateException e) {
      throw e;
    }

  }

  /**
   * Try to pick up the order.
   * @Throws IllegalStateException e
   */
  public void pickupOrder() throws IllegalStateException {
    try {
      state.pickupOrder();
    } catch (IllegalStateException e) {
      throw e;
    }

  }

  // Getters
  public OrderState getPackageOrderState() {
    return this.packaged;
  }

  public OrderState getDeliveredOrderState() {
    return this.delivered;
  }

  public OrderState getPickupOrderState() {
    return this.pickedUp;
  }

  // Setters
  public void setState(OrderState state) {
    this.state = state;
    this.notifySubscribers();
  }

  public OrderState getState() {
    return state;
  }

  @Override
  public void subscribe(SubscriberBase customer) {
    this.customer = (CustomerAdapter) customer;
  }

  @Override
  public void unsubscribe(SubscriberBase customer) {
    this.customer = null;
  }

  @Override
  public void notifySubscribers() {
    this.customer.updateSelf(state);
  }
}
