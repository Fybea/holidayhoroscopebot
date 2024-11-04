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
    private UserService userService;
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
            LocalDateTime time = LocalDateTime.now().withNano(0);

            Long userId = update.getMessage().getFrom().getId();
            String username = update.getMessage().getFrom().getUserName();
            String firstName = update.getMessage().getFrom().getFirstName();
            String lastName = update.getMessage().getFrom().getLastName();
            String languageCode = update.getMessage().getFrom().getLanguageCode();

            userService.saveUser(userId, username, firstName, lastName, languageCode);

            switch (message) {
                case START -> {
                    sendInlineKeyboardMessageForStartCommand(chatId);
                    actionService.saveAction(userId, START, time);
                }
                case HOLIDAYS -> {
                    sendInlineKeyboardMessage(chatId);
                    actionService.saveAction(userId, HOLIDAYS, time);
                }
                case INTERNATIONAL_HOLIDAYS -> {
                    sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);
                    actionService.saveAction(userId, INTERNATIONAL_HOLIDAYS, time);
                }
                case HOROSCOPE -> {
                    sendInlineKeyboardMessageForHoroscopeCommand(chatId);
                    actionService.saveAction(userId, HOROSCOPE, time);
                }
                case ARIES -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, ARIES);
                    actionService.saveAction(userId, ARIES, time);
                }
                case TAURUS -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, TAURUS);
                    actionService.saveAction(userId, TAURUS, time);
                }
                case GEMINI -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, GEMINI);
                    actionService.saveAction(userId, GEMINI, time);
                }
                case CANCER -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, CANCER);
                    actionService.saveAction(userId, CANCER, time);
                }
                case LEO -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, LEO);
                    actionService.saveAction(userId, LEO, time);
                }
                case VIRGO -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, VIRGO);
                    actionService.saveAction(userId, VIRGO, time);
                }
                case LIBRA -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, LIBRA);
                    actionService.saveAction(userId, LIBRA, time);
                }
                case SCORPIO -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, SCORPIO);
                    actionService.saveAction(userId, SCORPIO, time);
                }
                case SAGITTARIUS -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, SAGITTARIUS);
                    actionService.saveAction(userId, SAGITTARIUS, time);
                }
                case CAPRICORN -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, CAPRICORN);
                    actionService.saveAction(userId, CAPRICORN, time);
                }
                case AQUARIUS -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, AQUARIUS);
                    actionService.saveAction(userId, AQUARIUS, time);
                }
                case PISCES -> {
                    sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, PISCES);
                    actionService.saveAction(userId, PISCES, time);
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

        switch (callBackData) {
            case HOLIDAYS -> {
                sendInlineKeyboardMessage(chatId);
                actionService.saveAction(callbackQuery.getFrom().getId(), HOLIDAYS, time);
            }
            case MENU -> {
                sendInlineKeyboardMessageForStartCommand(chatId);
                actionService.saveAction(callbackQuery.getFrom().getId(), MENU, time);
            }
            case INTERNATIONAL_HOLIDAYS -> {
                sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);
                actionService.saveAction(callbackQuery.getFrom().getId(), INTERNATIONAL_HOLIDAYS, time);
            }
            case HOROSCOPE -> {
                sendInlineKeyboardMessageForHoroscopeCommand(chatId);
                actionService.saveAction(callbackQuery.getFrom().getId(), HOROSCOPE, time);
            }
            case ARIES -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, ARIES);
                actionService.saveAction(callbackQuery.getFrom().getId(), ARIES, time);
            }
            case TAURUS -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, TAURUS);
                actionService.saveAction(callbackQuery.getFrom().getId(), TAURUS, time);
            }
            case GEMINI -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, GEMINI);
                actionService.saveAction(callbackQuery.getFrom().getId(), GEMINI, time);
            }
            case CANCER -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, CANCER);
                actionService.saveAction(callbackQuery.getFrom().getId(), CANCER, time);
            }
            case LEO -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, LEO);
                actionService.saveAction(callbackQuery.getFrom().getId(), LEO, time);
            }
            case VIRGO -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, VIRGO);
                actionService.saveAction(callbackQuery.getFrom().getId(), VIRGO, time);
            }
            case LIBRA -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, LIBRA);
                actionService.saveAction(callbackQuery.getFrom().getId(), LIBRA, time);
            }
            case SCORPIO -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, SCORPIO);
                actionService.saveAction(callbackQuery.getFrom().getId(), SCORPIO, time);
            }
            case SAGITTARIUS -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, SAGITTARIUS);
                actionService.saveAction(callbackQuery.getFrom().getId(), SAGITTARIUS, time);
            }
            case CAPRICORN -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, CAPRICORN);
                actionService.saveAction(callbackQuery.getFrom().getId(), CAPRICORN, time);
            }
            case AQUARIUS -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, AQUARIUS);
                actionService.saveAction(callbackQuery.getFrom().getId(), AQUARIUS, time);
            }
            case PISCES -> {
                sendInlineKeyboardMessageZodiacHoroscopeCommand(chatId, PISCES);
                actionService.saveAction(callbackQuery.getFrom().getId(), PISCES, time);
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
