package com.example.telegram_bot_message_holiday_day_is_today.repository;

import com.example.telegram_bot_message_holiday_day_is_today.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
}
