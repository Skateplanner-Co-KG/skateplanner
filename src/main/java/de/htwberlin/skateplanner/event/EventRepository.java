package de.htwberlin.skateplanner.event;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventRepository extends CrudRepository<EventEntity, Long> {

    Optional<EventEntity> findEventEntityById(Long id);

    boolean existsByName(String name);

    @Override
    EventEntity save(EventEntity e);

    @Override
    void deleteById(Long aLong);
}