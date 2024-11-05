package com.example.telegram_bot_message_holiday_day_is_today;

import com.example.telegram_bot_message_holiday_day_is_today.services.*;
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

import java.time.LocalDateTime;

@Component
public class TelegramBotMessageHolidayDayIsToday extends TelegramLongPollingBot {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private HolidayService holidayParser;

    @Autowired
    private HoroscopeService horoscopeParser;

    @Autowired
    private UserService userService;
    private static final Logger LOG = LoggerFactory.getLogger(TelegramBotMessageHolidayDayIsToday.class);

    public TelegramBotMessageHolidayDayIsToday(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            LocalDateTime time = LocalDateTime.now().withNano(0);
            Long userId = update.getMessage().getFrom().getId();
            String username = update.getMessage().getFrom().getUserName();
            String firstName = update.getMessage().getFrom().getFirstName();
            String lastName = update.getMessage().getFrom().getLastName();
            String languageCode = update.getMessage().getFrom().getLanguageCode();

            BotCommand command = BotCommand.fromString(message);

            userService.saveUser(userId, username, firstName, lastName, languageCode);

            if (command != null) {
                switch (command) {
                    case START -> {
                        sendInlineKeyboardMessageForStartCommand(chatId);
                        actionService.saveAction(userId, command.getCommand(), time);
                    }
                    case HOLIDAYS -> {
                        sendInlineKeyboardMessage(chatId);
                        actionService.saveAction(userId, command.getCommand(), time);
                    }
                    case INTERNATIONAL_HOLIDAYS -> {
                        sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);
                        actionService.saveAction(userId, command.getCommand(), time);
                    }
                    case HOROSCOPE -> {
                        sendInlineKeyboardMessageForHoroscopeCommand(chatId);
                        actionService.saveAction(userId, command.getCommand(), time);
                    }
                    case ARIES, TAURUS, GEMINI, CANCER, LEO, VIRGO, LIBRA, SCORPIO, SAGITTARIUS, CAPRICORN, AQUARIUS, PISCES -> {
                        sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, command.getCommand());
                        actionService.saveAction(userId, command.getCommand(), time);
                    }
                }
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
        LocalDateTime time = LocalDateTime.now().withNano(0);

        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackQueryId);
        answerCallbackQuery.setText("Ваш запрос обработан!");
        try {
            execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        LOG.info("Нажата кнопка: {} пользователем {}", callBackData, userName);

        BotCommand command = BotCommand.fromString(callBackData);
        if (command != null) {
            Long userId = callbackQuery.getFrom().getId();

            switch (command) {
                case HOLIDAYS -> {
                    sendInlineKeyboardMessage(chatId);
                    actionService.saveAction(userId, command.getCommand(), time);
                }
                case MENU -> {
                    sendInlineKeyboardMessageForStartCommand(chatId);
                    actionService.saveAction(userId, command.getCommand(), time);
                }
                case INTERNATIONAL_HOLIDAYS -> {
                    sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);
                    actionService.saveAction(userId, command.getCommand(), time);
                }
                case HOROSCOPE -> {
                    sendInlineKeyboardMessageForHoroscopeCommand(chatId);
                    actionService.saveAction(userId, command.getCommand(), time);
                }
                case ARIES, TAURUS, GEMINI, CANCER, LEO, VIRGO, LIBRA, SCORPIO, SAGITTARIUS, CAPRICORN, AQUARIUS, PISCES -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, command.getCommand());
                    actionService.saveAction(userId, command.getCommand(), time);
                }
            }
        }
    }

    public void sendInlineKeyboardMessage(long chatId) {
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