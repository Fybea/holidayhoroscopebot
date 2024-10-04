package com.example.telegram_bot_message_holiday_day_is_today.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class MessageService {
    @Autowired
    private KeyboardService keyboardService;

    public SendMessage createHolidaysMessage(long chatId, String holidaysText) {
        SendMessage message = new SendMessage(String.valueOf(chatId), holidaysText);
        message.setReplyMarkup(keyboardService.getInlineKeyboardMarkupForHolidays());
        return message;
    }

    public SendMessage createStartMessage(long chatId) {
        var text = """
                                          
                По всем вопросам пишите в ТГ - @EternalFairTrade                
                                
                Holidays - список всех праздников в РФ на сегодня.
                Saint Holidays - список международных и церковных праздников.
                Horoscope - гороскоп на сегодня.
                """;
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        message.setReplyMarkup(keyboardService.getReplyKeyboardMarkup());
        return message;
    }

    public SendMessage createSaintHolidaysMessage(long chatId, String saintHolidaysText) {
        SendMessage message = new SendMessage(String.valueOf(chatId), saintHolidaysText);
        message.setReplyMarkup(keyboardService.getInlineKeyboardMarkupForSaintHolidays());
        return message;
    }

    public SendMessage createHoroscopeMessage(long chatId) {
        String text = "Укажите знак зодиака, на который ты хочешь посмотреть гороскоп на сегодня";

        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        message.setReplyMarkup(keyboardService.getInlineKeyboardMarkupForHoroscope());
        return message;
    }

    public SendMessage createHoroscopeMessageForZodiac(long chatId, String horoscopeText) {
        SendMessage message = new SendMessage(String.valueOf(chatId), horoscopeText);
        message.setReplyMarkup(keyboardService.getInlineKeyboardMarkupForHoroscopeForAnotherZodiacs());
        return message;
    }
}
