package com.example.telegram_bot_message_holiday_day_is_today.services;

import com.example.telegram_bot_message_holiday_day_is_today.model.User;
import com.example.telegram_bot_message_holiday_day_is_today.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(Long userId, String username, String firstName, String lastName, String languageCode) {
        if (userRepository.findUserByUserId(userId).isEmpty()) {
            User user = new User();
            user.setUserId(userId);
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLanguageCode(languageCode);
            userRepository.save(user);
        } else {
            User existingUser = userRepository.findUserByUserId(userId).orElse(null);
            if (existingUser != null) {
                existingUser.setFirstName(firstName);
                existingUser.setLastName(lastName);
                existingUser.setUsername(username);
                existingUser.setLanguageCode(languageCode);
                userRepository.save(existingUser);
            }
        }
    }
}
