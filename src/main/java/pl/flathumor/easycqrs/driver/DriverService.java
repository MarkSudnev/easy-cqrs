package pl.flathumor.easycqrs.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.flathumor.easycqrs.shared.EventStore;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DriverService {

  private final EventStore eventStore;

  public DriverDetailsDto getDetails(final UUID aggregateId) {
    final var aggregate = loadAggregate(aggregateId);
    return DriverDetailsDto.builder()
        .id(aggregate.getId())
        .name(aggregate.getName())
        .phone(aggregate.getPhone())
        .build();
  }

  public DriverAggregate handle(final CreateDriverCommand command) {
    final var event = DriverCreatedEvent.builder()
        .id(UUID.randomUUID())
        .timestamp(LocalDateTime.now())
        .type(DriverNameUpdatedEvent.class.getSimpleName())
        .driverId(command.getId())
        .name(command.getName())
        .phone(command.getPhone())
        .build();
    eventStore.put(command.getId(), event);
    final var aggregate = new DriverAggregate();
    aggregate.apply(event);
    return aggregate;
  }

  public void handle(final UpdateDriverNameCommand command) {
    final var event = DriverNameUpdatedEvent.builder()
        .id(UUID.randomUUID())
        .timestamp(LocalDateTime.now())
        .type(DriverNameUpdatedEvent.class.getSimpleName())
        .driverId(command.getId())
        .name(command.getName())
        .build();
    eventStore.put(command.getId(), event);
  }

  public void handle(final UpdateDriverPhoneCommand command) {
    final var event = DriverPhoneUpdatedEvent.builder()
        .id(UUID.randomUUID())
        .timestamp(LocalDateTime.now())
        .type(DriverNameUpdatedEvent.class.getSimpleName())
        .driverId(command.getId())
        .phone(command.getPhone())
        .build();
    eventStore.put(command.getId(), event);
  }

  private DriverAggregate loadAggregate(final UUID aggregateId) {
    final var aggregate = new DriverAggregate();
    eventStore.get(aggregateId).forEach(aggregate::apply);
    return aggregate;
  }

}
