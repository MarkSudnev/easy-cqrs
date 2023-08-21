package pl.flathumor.easycqrs.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/vehicles", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VehicleController {

  private final VehicleService vehicleService;

  @GetMapping("/{vehicleId}")
  public VehicleDetailsDto getDetails(@PathVariable final UUID vehicleId) {
    return vehicleService.getDetails(vehicleId);
  }

  @PostMapping
  public VehicleDetailsDto create(@RequestBody final CreateVehicleDto dto) {
    final var command = CreateVehicleCommand.builder()
        .id(UUID.randomUUID())
        .model(dto.getModel())
        .plate(dto.getPlate())
        .build();
    return vehicleService.handle(command);
  }
}
