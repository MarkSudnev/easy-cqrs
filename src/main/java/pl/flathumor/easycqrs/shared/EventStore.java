package pl.flathumor.easycqrs.shared;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Comparator.comparing;

@Slf4j
@Component
public class EventStore {

  private final Map<UUID, List<Event>> store = new HashMap<>();

  public void put(final UUID aggregateId, final Event event) {
    final var events = store.getOrDefault(aggregateId, new ArrayList<>());
    events.add(event);
    store.put(aggregateId, events);
    log.info(store.toString());
  }

  public List<Event> get(final UUID aggregateId) {
    return store
        .getOrDefault(aggregateId, List.of())
        .stream()
        .sorted(comparing(Event::getTimestamp))
        .toList();
  }
}
