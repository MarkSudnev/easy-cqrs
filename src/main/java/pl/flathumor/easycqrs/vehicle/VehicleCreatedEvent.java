package pl.flathumor.easycqrs.vehicle;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import pl.flathumor.easycqrs.shared.Event;

import java.util.UUID;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class VehicleCreatedEvent extends Event {
  UUID vehicleId;
  String model;
  String plate;
}
