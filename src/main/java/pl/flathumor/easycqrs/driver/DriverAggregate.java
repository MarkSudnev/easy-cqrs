package pl.flathumor.easycqrs.driver;

import lombok.Getter;
import pl.flathumor.easycqrs.shared.Event;

import java.util.UUID;

@Getter
public class DriverAggregate {

  private UUID id;
  private String name;
  private String phone;


  public void apply(final Event event) {
    if (event instanceof DriverCreatedEvent e) {
      apply(e);
    }
    else if (event instanceof DriverNameUpdatedEvent e) {
      apply(e);
    }
    else if (event instanceof DriverPhoneUpdatedEvent e) {
      apply(e);
    }
  }

  private void apply(final DriverCreatedEvent event) {
    this.id = event.getDriverId();
    this.name = event.getName();
    this.phone = event.getPhone();
  }

  private void apply(final DriverNameUpdatedEvent event) {
    this.name = event.getName();
  }

  private void apply(final DriverPhoneUpdatedEvent event) {
    this.phone = event.getPhone();
  }
}
