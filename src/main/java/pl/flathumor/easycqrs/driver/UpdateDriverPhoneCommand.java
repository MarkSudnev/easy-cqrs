package pl.flathumor.easycqrs.driver;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UpdateDriverPhoneCommand {
  UUID id;
  String phone;
}
