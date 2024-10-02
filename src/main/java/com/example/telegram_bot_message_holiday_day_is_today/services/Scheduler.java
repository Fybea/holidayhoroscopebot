package com.example.telegram_bot_message_holiday_day_is_today.services;

import com.example.telegram_bot_message_holiday_day_is_today.TelegramBotMessageHolidayDayIsToday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduler {
    final TelegramBotMessageHolidayDayIsToday telegramBotMessageHolidayDayIsToday;

    @Autowired
    public Scheduler(TelegramBotMessageHolidayDayIsToday telegramBotMessageHolidayDayIsToday) {

        this.telegramBotMessageHolidayDayIsToday = telegramBotMessageHolidayDayIsToday;
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateHolidayDaily() {
    }

    /* TODO: сделать так, чтобы бот автоматически присылал сообщения каждый день с новыми праздниками примерно в 5 утра*/

}
