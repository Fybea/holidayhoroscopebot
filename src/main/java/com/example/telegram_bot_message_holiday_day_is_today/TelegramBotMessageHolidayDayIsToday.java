package com.example.telegram_bot_message_holiday_day_is_today;

import com.example.telegram_bot_message_holiday_day_is_today.services.HolidayService;
import com.example.telegram_bot_message_holiday_day_is_today.services.HoroscopeService;
import com.example.telegram_bot_message_holiday_day_is_today.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class TelegramBotMessageHolidayDayIsToday extends TelegramLongPollingBot {

    @Autowired
    private MessageService messageService;
    private static final Logger LOG = LoggerFactory.getLogger(TelegramBotMessageHolidayDayIsToday.class);
    private static final String START = "/start";
    private static final String MENU = "Menu";
    private static final String HOLIDAYS = "Holidays";
    private static final String INTERNATIONAL_HOLIDAYS = "International Holidays";
    private static final String HOROSCOPE = "Horoscope";
    private static final String ARIES = "Aries";
    private static final String TAURUS = "Taurus";
    private static final String GEMINI = "Gemini";
    private static final String CANCER = "Cancer";
    private static final String LEO = "Leo";
    private static final String VIRGO = "Virgo";
    private static final String LIBRA = "Libra";
    private static final String SCORPIO = "Scorpio";
    private static final String SAGITTARIUS = "Sagittarius";
    private static final String CAPRICORN = "Capricorn";
    private static final String AQUARIUS = "Aquarius";
    private static final String PISCES = "Pisces";

    private static final HolidayService holidayParser = new HolidayService();
    private static final HoroscopeService horoscopeParser = new HoroscopeService();

    public TelegramBotMessageHolidayDayIsToday(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            switch (message) {
                case START -> sendInlineKeyboardMessageForStartCommand(chatId);
                case HOLIDAYS -> sendInlineKeyboardMessage(chatId);
                case INTERNATIONAL_HOLIDAYS -> sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);
                case HOROSCOPE -> sendInlineKeyboardMessageForHoroscopeCommand(chatId);
                case ARIES -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, ARIES);
                case TAURUS -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, TAURUS);
                case GEMINI -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, GEMINI);
                case CANCER -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, CANCER);
                case LEO -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, LEO);
                case VIRGO -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, VIRGO);
                case LIBRA -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, LIBRA);
                case SCORPIO -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, SCORPIO);
                case SAGITTARIUS -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, SAGITTARIUS);
                case CAPRICORN -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, CAPRICORN);
                case AQUARIUS -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, AQUARIUS);
                case PISCES -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, PISCES);
            }
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update.getCallbackQuery());
        }
    }

    private void handleCallbackQuery(CallbackQuery callbackQuery) {
        String callBackData = callbackQuery.getData();
        String userName = callbackQuery.getFrom().getUserName();
        String callbackQueryId = callbackQuery.getId();
        Long chatId = callbackQuery.getMessage().getChatId();

        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQueryId);
        answerCallbackQuery.setText("Ваш запрос обработан!");
        try {
            execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        LOG.info("Нажата кнопка: {} пользователем {}", callBackData, userName);

        switch (callBackData) {
            case HOLIDAYS -> sendInlineKeyboardMessage(chatId);
            case MENU -> sendInlineKeyboardMessageForStartCommand(chatId);
            case INTERNATIONAL_HOLIDAYS -> sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);
            case HOROSCOPE -> sendInlineKeyboardMessageForHoroscopeCommand(chatId);
            case ARIES -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, ARIES);
            case TAURUS -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, TAURUS);
            case GEMINI -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, GEMINI);
            case CANCER -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, CANCER);
            case LEO -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, LEO);
            case VIRGO -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, VIRGO);
            case LIBRA -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, LIBRA);
            case SCORPIO -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, SCORPIO);
            case SAGITTARIUS -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, SAGITTARIUS);
            case CAPRICORN -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, CAPRICORN);
            case AQUARIUS -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, AQUARIUS);
            case PISCES -> sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, PISCES);
        }
    }


    private void sendInlineKeyboardMessage(long chatId) {
        String holidaysText = holidayParser.getTodayHolidays(new StringBuilder());
        SendMessage message = messageService.createHolidaysMessage(chatId, holidaysText);
        executeMessage(message);
    }

    private void sendInlineKeyboardMessageForHoroscopeCommand(long chatId) {
        SendMessage message = messageService.createHoroscopeMessage(chatId);
        executeMessage(message);
    }

    private void sendInlineKeyboardMessageZodiacHoroscopeCommand(long chatId, String zodiac) {
        String horoscopeText = horoscopeParser.getTodayHoroscopes(new StringBuilder(), zodiac);
        SendMessage message = messageService.createHoroscopeMessageForZodiac(chatId, horoscopeText);
        executeMessage(message);
    }

    private void sendInlineKeyboardMessageForSaintHolidaysCommand(long chatId) {
        String saintHolidayText = holidayParser.getMoreTodayHolidays(new StringBuilder());
        SendMessage message = messageService.createSaintHolidaysMessage(chatId, saintHolidayText);
        executeMessage(message);
    }

    private void sendInlineKeyboardMessageForStartCommand(long chatId) {
        SendMessage message = messageService.createStartMessage(chatId);
        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "WhichPrazdnikTodayBot";
    }

    public String getBotName() {
        return "Which holiday is today";
    }


}
