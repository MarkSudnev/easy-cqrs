package pl.flathumor.easycqrs.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.flathumor.easycqrs.shared.EventStore;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {

  private final EventStore eventStore;

  public VehicleDetailsDto getDetails(final UUID aggregateId) {
    final var aggregate = loadAggregate(aggregateId);
    return VehicleDetailsDto.builder()
        .id(aggregate.getId())
        .model(aggregate.getModel())
        .plate(aggregate.getPlate())
        .build();
  }

  public VehicleDetailsDto handle(final CreateVehicleCommand command) {
    final var event = VehicleCreatedEvent.builder()
        .id(UUID.randomUUID())
        .timestamp(LocalDateTime.now())
        .type(VehicleCreatedEvent.class.getSimpleName())
        .vehicleId(command.getId())
        .model(command.getModel())
        .plate(command.getPlate())
        .build();
    eventStore.put(command.getId(), event);
    final var aggregate = new VehicleAggregate();
    aggregate.apply(event);
    return VehicleDetailsDto.builder()
        .id(aggregate.getId())
        .model(aggregate.getModel())
        .plate(aggregate.getPlate())
        .build();
  }

  private VehicleAggregate loadAggregate(final UUID aggregateId) {
    final var aggregate = new VehicleAggregate();
    eventStore.get(aggregateId).forEach(aggregate::apply);
    return aggregate;
  }
}
