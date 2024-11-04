package com.example.telegram_bot_message_holiday_day_is_today.services;

import com.example.telegram_bot_message_holiday_day_is_today.model.Action;
import com.example.telegram_bot_message_holiday_day_is_today.model.User;
import com.example.telegram_bot_message_holiday_day_is_today.repository.ActionRepository;
import com.example.telegram_bot_message_holiday_day_is_today.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ActionService {

    private final ActionRepository actionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ActionService(ActionRepository actionRepository, UserRepository userRepository){
        this.actionRepository = actionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveAction(Long userid, String botCommand, LocalDateTime localDateTime) {
        User user = userRepository.findUserByUserId(userid).orElseThrow(NullPointerException::new);
        System.out.println(user.getUserId());
        Action action = new Action();
        action.setUser(user);
        action.setBotCommand(botCommand);
        action.setLocalDateTime(localDateTime);
        actionRepository.save(action);
    }

}
