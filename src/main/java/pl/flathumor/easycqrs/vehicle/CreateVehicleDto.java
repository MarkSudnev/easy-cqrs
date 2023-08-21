package pl.flathumor.easycqrs.vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVehicleDto {
  private String model;
  private String plate;
}
