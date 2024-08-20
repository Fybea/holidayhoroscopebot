package com.example.telegram_bot_message_holiday_day_is_today.config;

import com.example.telegram_bot_message_holiday_day_is_today.TelegramBotMessageHolidayDayIsToday;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Configuration
public class TelegramBotMessageHolidayDayIsTodayConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi
            (TelegramBotMessageHolidayDayIsToday telegramBotMessageHolidayDayIsToday) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(telegramBotMessageHolidayDayIsToday);
        return api;
    }
}
