package edu.bu.met.cs665.notifications;

import edu.bu.met.cs665.orderprocessing.OrderState;

public interface SubscriberBase {

  void updateSelf(OrderState state);

}
