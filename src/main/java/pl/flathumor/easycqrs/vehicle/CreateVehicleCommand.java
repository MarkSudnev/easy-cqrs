package pl.flathumor.easycqrs.vehicle;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreateVehicleCommand {
  UUID id;
  String model;
  String plate;
}
