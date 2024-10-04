package com.example.telegram_bot_message_holiday_day_is_today.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KeyboardService {

    public ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(createKeyboardRow("Holidays", "International Holidays", "Horoscope"));
        keyboardRowList.add(new KeyboardRow());

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(keyboardRowList);
        return keyboardMarkup;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkupForHolidays() {
        return createInlineKeyboardMarkup(
                createInlineKeyboardRow(
                        createButton("Другие праздники", "International Holidays")
                ),
                createInlineKeyboardRow(
                        createButton("Меню", "Menu")
                )
        );
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkupForSaintHolidays() {
        return createInlineKeyboardMarkup(
                createInlineKeyboardRow(
                        createButton("Гороскоп", "Horoscope")
                ),
                createInlineKeyboardRow(
                        createButton("Меню", "Menu"))
        );
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkupForHoroscope() {
        return createInlineKeyboardMarkup(
                createInlineKeyboardRow(
                        createButton("Овен", "Aries"),
                        createButton("Телец", "Taurus"),
                        createButton("Близнецы", "Gemini")
                ),
                createInlineKeyboardRow(
                        createButton("Рак", "Cancer"),
                        createButton("Лев", "Leo"),
                        createButton("Дева", "Virgo")
                ),
                createInlineKeyboardRow(
                        createButton("Весы", "Libra"),
                        createButton("Скорпион", "Scorpio"),
                        createButton("Стрелец", "Sagittarius")
                ),
                createInlineKeyboardRow(
                        createButton("Козерог", "Capricorn"),
                        createButton("Водолей", "Aquarius"),
                        createButton("Рыбы", "Pisces")
                ),
                createInlineKeyboardRow(
                        createButton("Меню", "Menu"))
        );
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkupForHoroscopeForAnotherZodiacs() {
        return createInlineKeyboardMarkup(
                createInlineKeyboardRow(createButton("Другие знаки зодиака", "Horoscope")),
                createInlineKeyboardRow(createButton("Меню", "Menu"))
        );
    }

    private InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }

    private List<InlineKeyboardButton> createInlineKeyboardRow(InlineKeyboardButton... buttons) {
        return new ArrayList<>(Arrays.asList(buttons));
    }

    @SafeVarargs
    private InlineKeyboardMarkup createInlineKeyboardMarkup(List<InlineKeyboardButton>... rows) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>(Arrays.asList(rows));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    private KeyboardRow createKeyboardRow(String... buttons) {
        KeyboardRow row = new KeyboardRow();
        for (String button : buttons) {
            row.add(button);
        }
        return row;
    }
}