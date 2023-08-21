package pl.flathumor.easycqrs.vehicle;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class VehicleDetailsDto {
  UUID id;
  String model;
  String plate;
}
