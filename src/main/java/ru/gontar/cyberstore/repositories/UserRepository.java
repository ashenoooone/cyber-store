package ru.gontar.cyberstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gontar.cyberstore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
