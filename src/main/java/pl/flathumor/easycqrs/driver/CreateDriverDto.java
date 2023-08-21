package pl.flathumor.easycqrs.driver;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateDriverDto {
  String name;
  String phone;
}
