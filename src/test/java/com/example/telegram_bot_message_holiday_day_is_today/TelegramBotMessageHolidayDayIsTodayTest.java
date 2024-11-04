package com.example.telegram_bot_message_holiday_day_is_today;

import com.example.telegram_bot_message_holiday_day_is_today.services.ActionService;
import com.example.telegram_bot_message_holiday_day_is_today.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.mockito.Mockito.*;
@SpringBootTest
class TelegramBotMessageHolidayDayIsTodayTest {

    @Mock
    private ActionService actionService;
    @Mock
    private UserService userService;

    @InjectMocks
    @Autowired
    private TelegramBotMessageHolidayDayIsToday bot;

    @BeforeEach
    public void setup() {
    }


    @Test
    public void testOnUpdateReceived_startCommand() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        User user = mock(User.class);

        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("/start");
        when(message.getChatId()).thenReturn(123L);

        when(message.getFrom()).thenReturn(user);
        when(user.getId()).thenReturn(1L);
        when(user.getUserName()).thenReturn("testUser");
        when(user.getFirstName()).thenReturn("testFirstName");
        when(user.getLastName()).thenReturn("testLastName");
        when(user.getLanguageCode()).thenReturn("en");

        bot.onUpdateReceived(update);

        verify(userService).saveUser(1L, "testUser", "testFirstName", "testLastName", "en");
        verify(bot).sendInlineKeyboardMessage(123L);

    }
}