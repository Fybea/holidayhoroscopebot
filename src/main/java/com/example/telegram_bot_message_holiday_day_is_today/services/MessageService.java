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

    public SendMessage createHelpMessage(long chatId) {
        var text = """
                Добро пожаловать в личный бот Алексея.
                                
                Здесь Вы сможете узнать какой сегодня праздник..
                                
                                
                Holidays - праздники на сегодня
                Saint Holidays - список Церковных и региональных праздников.
                Horoscope - гороскоп для вашего знака зодиака на сегодня.
                WORK IN PROGRESS...
                """;
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        message.setReplyMarkup(keyboardService.getInlineKeyboardMarkupForHelpCommand());
        return message;
    }

    public SendMessage createStartMessage(long chatId) {
        var text = """
                Справочная информация по боту
                                
                Holidays - список всех праздников в РФ на сегодня.
                Saint Holidays - список Церковных и региональных праздников.
                Horoscope - гороскоп для вашего знака зодиака на сегодня.
                WORK IN PROGRESS...
                """;
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        message.setReplyMarkup(keyboardService.getInlineKeyboardMarkupForHelpCommand());
        return message;
    }

    public SendMessage createSaintHolidaysMessage(long chatId, String saintHolidaysText) {
        SendMessage message = new SendMessage(String.valueOf(chatId), saintHolidaysText);
        message.setReplyMarkup(keyboardService.getInlineKeyboardMarkupForSaintHolidays());
        return message;
    }

}
