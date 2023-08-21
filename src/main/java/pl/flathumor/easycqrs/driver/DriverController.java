package pl.flathumor.easycqrs.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/drivers", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DriverController {

  private final DriverService driverService;

  @GetMapping("/{driverId}")
  public DriverDetailsDto get(@PathVariable final UUID driverId) {
    return driverService.getDetails(driverId);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public DriverDetailsDto create(@RequestBody final CreateDriverDto dto) {
    final var command = CreateDriverCommand.builder()
        .id(UUID.randomUUID())
        .name(dto.getName())
        .phone(dto.getPhone())
        .build();
    final var driverAggregate = driverService.handle(command);
    return DriverDetailsDto.builder()
        .id(driverAggregate.getId())
        .name(driverAggregate.getName())
        .phone(driverAggregate.getPhone())
        .build();
  }

  @PatchMapping("/{driverId}/name")
  public void updateName(
      @PathVariable final UUID driverId,
      @RequestBody final UpdateDriverNameDto dto
  ) {
    final var command = UpdateDriverNameCommand.builder()
        .id(driverId)
        .name(dto.getName())
        .build();
    driverService.handle(command);
  }

  @PatchMapping("/{driverId}/phone")
  public void updatePhone(
      @PathVariable final UUID driverId,
      @RequestBody final UpdateDriverPhoneDto dto
  ) {
    final var command = UpdateDriverPhoneCommand.builder()
        .id(driverId)
        .phone(dto.getPhone())
        .build();
    driverService.handle(command);
  }
}
