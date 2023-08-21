package pl.flathumor.easycqrs.shared;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public abstract class Event {

  private final UUID id;
  private final LocalDateTime timestamp;
  private final String type;
}
