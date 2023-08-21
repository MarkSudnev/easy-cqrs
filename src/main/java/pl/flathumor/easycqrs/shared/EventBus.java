package pl.flathumor.easycqrs.shared;

import org.springframework.stereotype.Component;
import pl.flathumor.easycqrs.driver.DriverCreatedEvent;
import pl.flathumor.easycqrs.driver.DriverNameUpdatedEvent;
import pl.flathumor.easycqrs.driver.DriverPhoneUpdatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class EventBus {

  private final List<Consumer<DriverCreatedEvent>> driverCreatedEventConsumers = new ArrayList<>();
  private final List<Consumer<DriverNameUpdatedEvent>> driverNameUpdatedEventConsumers = new ArrayList<>();
  private final List<Consumer<DriverPhoneUpdatedEvent>> driverPhoneUpdatedEventConsumers = new ArrayList<>();

  public void subscribeDriverCreatedEvent(final Consumer<DriverCreatedEvent> consumer) {
    driverCreatedEventConsumers.add(consumer);
  }

  public void subscribeDriverNameUpdatedEvent(final Consumer<DriverNameUpdatedEvent> consumer) {
    driverNameUpdatedEventConsumers.add(consumer);
  }

  public void subscribeDriverPhoneUpdatedEvent(final Consumer<DriverPhoneUpdatedEvent> consumer) {
    driverPhoneUpdatedEventConsumers.add(consumer);
  }

  public void pushEvent(final DriverCreatedEvent event) {
    driverCreatedEventConsumers.forEach(consumer -> consumer.accept(event));
  }

  public void pushEvent(final DriverNameUpdatedEvent event) {
    driverNameUpdatedEventConsumers.forEach(consumer -> consumer.accept(event));
  }

  public void pushEvent(final DriverPhoneUpdatedEvent event) {
    driverPhoneUpdatedEventConsumers.forEach(consumer -> consumer.accept(event));
  }


}
