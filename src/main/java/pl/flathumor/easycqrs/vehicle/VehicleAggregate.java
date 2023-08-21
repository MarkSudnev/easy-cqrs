package pl.flathumor.easycqrs.vehicle;

import lombok.Getter;
import pl.flathumor.easycqrs.shared.Event;

import java.util.UUID;

@Getter
public class VehicleAggregate {

  private UUID id;
  private String model;
  private String plate;

  public void apply(final Event event) {
    if (event instanceof VehicleCreatedEvent e) {
      this.id = e.getId();
      this.model = e.getModel();
      this.plate = e.getPlate();
    }
  }
}
