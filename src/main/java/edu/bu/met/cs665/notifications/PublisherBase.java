package edu.bu.met.cs665.notifications;

public interface PublisherBase {

  void subscribe(SubscriberBase customer);

  void unsubscribe(SubscriberBase customer);

  void notifySubscribers();
}
