package de.htwberlin.skateplanner.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findUserEntityByUsername(String username);

    Optional<UserEntity> findUserEntityByEmail(String email);

    @Override
    UserEntity save(UserEntity entity);

    @Override
    Iterable<UserEntity> findAll();
}