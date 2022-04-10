package edu.bu.met.cs665.customerdata;

import edu.bu.met.cs665.notifications.SubscriberBase;
import edu.bu.met.cs665.orderprocessing.Order;
import edu.bu.met.cs665.orderprocessing.OrderState;

/**
 * The purpose of this class is to vonvert the new system Customer object
 * requests (look up by email and phone number Strings) to the old system
 * lookup. This class is the concrete adapter from the New Customer lookup
 * to the Old.
 *
 * @author Michael Kramer
 *
 * <p>CS665 Spring 2, 2022 Software Design Patterns</p>
 */

public class CustomerAdapter implements CustomerDataManagementSystem, SubscriberBase {

  private Customer customer;
  private Order order; // let's imagine Order is like a "cart" on Amazon
  private OrderState orderState;


  public CustomerAdapter(Customer customer) {
    this.customer = customer;
  }


  @Override
  public Customer getCustomer(String email, String phoneNumber) {
    return customer.getCustomer(email); // drop phone number
  }

  public String getCustomerEmail() {
    return this.customer.getEmail();
  }

  public String getCustomerPhoneNumber() {
    return this.customer.getPhoneNumber();
  }


  public Order getOrder() {
    return this.order;
  }

  public void placeOrder(Order order) {
    this.order = order;
  }

  /**
   * The purpose of this method is to update the self of the state of an order.
   * @param state New Order State.
   */
  public void updateSelf(OrderState state) {
    this.orderState = state;
    System.out.println("The latest order state was received by " + customer.getEmail()
        + ": " + getOrderState());
  }



  public OrderState getOrderState() {
    return this.orderState;
  }

  public String toString() {
    return customer.toString();
  }
}
