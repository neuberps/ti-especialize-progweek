package br.tie.progweek.repository;

import br.tie.progweek.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String value);

    Optional<User> findByEmail(String value);
}
