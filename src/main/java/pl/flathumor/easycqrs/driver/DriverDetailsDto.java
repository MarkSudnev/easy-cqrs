package pl.flathumor.easycqrs.driver;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DriverDetailsDto {
  UUID id;
  String name;
  String phone;
}
