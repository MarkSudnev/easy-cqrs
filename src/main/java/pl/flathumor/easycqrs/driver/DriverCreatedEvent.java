package pl.flathumor.easycqrs.driver;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import pl.flathumor.easycqrs.shared.Event;

import java.util.UUID;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DriverCreatedEvent extends Event {

  UUID driverId;
  String name;
  String phone;

}
