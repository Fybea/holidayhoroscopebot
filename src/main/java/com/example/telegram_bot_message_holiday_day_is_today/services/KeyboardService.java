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
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
//
//
//        InlineKeyboardButton button = new InlineKeyboardButton();
//        InlineKeyboardButton menuButton = new InlineKeyboardButton();
//
//        menuButton.setText("Меню");
//        button.setText("Другие праздники");
//        button.setCallbackData("International Holidays");
//        menuButton.setCallbackData("Menu");
//
//        rowInline.add(button);
//        rowInline2.add(menuButton);
//        rowsInline.add(rowInline);
//        rowsInline.add(rowInline2);
//
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//        return inlineKeyboardMarkup;

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
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
//
//        InlineKeyboardButton button = new InlineKeyboardButton();
//        InlineKeyboardButton menuButton = new InlineKeyboardButton();
//        menuButton.setText("Меню");
//        menuButton.setCallbackData("Menu");
//        button.setText("Гороскоп");
//        button.setCallbackData("Horoscope");
//
//        rowInline.add(button);
//        rowInline2.add(menuButton);
//        rowsInline.add(rowInline);
//        rowsInline.add(rowInline2);
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//        return inlineKeyboardMarkup;


        return createInlineKeyboardMarkup(
                createInlineKeyboardRow(
                        createButton("Гороскоп", "Horoscope")
                ),
                createInlineKeyboardRow(
                        createButton("Меню", "Menu"))
        );
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkupForHoroscope() {
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline5 = new ArrayList<>();
//
//        InlineKeyboardButton button1 = new InlineKeyboardButton();
//        InlineKeyboardButton button2 = new InlineKeyboardButton();
//        InlineKeyboardButton button3 = new InlineKeyboardButton();
//        InlineKeyboardButton button4 = new InlineKeyboardButton();
//        InlineKeyboardButton button5 = new InlineKeyboardButton();
//        InlineKeyboardButton button6 = new InlineKeyboardButton();
//        InlineKeyboardButton button7 = new InlineKeyboardButton();
//        InlineKeyboardButton button8 = new InlineKeyboardButton();
//        InlineKeyboardButton button9 = new InlineKeyboardButton();
//        InlineKeyboardButton button10 = new InlineKeyboardButton();
//        InlineKeyboardButton button11 = new InlineKeyboardButton();
//        InlineKeyboardButton button12 = new InlineKeyboardButton();
//        InlineKeyboardButton menuButton = new InlineKeyboardButton();
//
//        button1.setText("Овен");
//        button2.setText("Телец");
//        button3.setText("Близнецы");
//        button4.setText("Рак");
//        button5.setText("Лев");
//        button6.setText("Дева");
//        button7.setText("Весы");
//        button8.setText("Скорпион");
//        button9.setText("Стрелец");
//        button10.setText("Козерог");
//        button11.setText("Водолей");
//        button12.setText("Рыбы");
//        menuButton.setText("Меню");
//
//        button1.setCallbackData("Aries");
//        button2.setCallbackData("Taurus");
//        button3.setCallbackData("Gemini");
//        button4.setCallbackData("Cancer");
//        button5.setCallbackData("Leo");
//        button6.setCallbackData("Virgo");
//        button7.setCallbackData("Libra");
//        button8.setCallbackData("Scorpio");
//        button9.setCallbackData("Sagittarius");
//        button10.setCallbackData("Capricorn");
//        button11.setCallbackData("Aquarius");
//        button12.setCallbackData("Pisces");
//        menuButton.setCallbackData("Menu");
//
//
//        rowInline.add(button1);
//        rowInline.add(button2);
//        rowInline.add(button3);
//        rowInline2.add(button4);
//        rowInline2.add(button5);
//        rowInline2.add(button6);
//        rowInline3.add(button7);
//        rowInline3.add(button8);
//        rowInline3.add(button9);
//        rowInline4.add(button10);
//        rowInline4.add(button11);
//        rowInline4.add(button12);
//        rowInline5.add(menuButton);
//
//        rowsInline.add(rowInline);
//        rowsInline.add(rowInline2);
//        rowsInline.add(rowInline3);
//        rowsInline.add(rowInline4);
//        rowsInline.add(rowInline5);
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//        return inlineKeyboardMarkup;


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
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
//
//        InlineKeyboardButton button = new InlineKeyboardButton();
//        InlineKeyboardButton menuButton = new InlineKeyboardButton();
//        button.setText("Другие знаки зодиака");
//        menuButton.setText("Меню");
//        button.setCallbackData("Horoscope");
//        menuButton.setCallbackData("Menu");
//
//        rowInline.add(button);
//        rowInline2.add(menuButton);
//        rowsInline.add(rowInline);
//        rowsInline.add(rowInline2);
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//        return inlineKeyboardMarkup;

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