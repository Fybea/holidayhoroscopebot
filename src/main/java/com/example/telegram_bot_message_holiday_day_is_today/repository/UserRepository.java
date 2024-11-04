package com.example.telegram_bot_message_holiday_day_is_today.repository;

import com.example.telegram_bot_message_holiday_day_is_today.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(Long aLong);
}
