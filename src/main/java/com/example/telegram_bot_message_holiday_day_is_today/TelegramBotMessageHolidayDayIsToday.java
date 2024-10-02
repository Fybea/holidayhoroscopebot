package com.example.telegram_bot_message_holiday_day_is_today;

import com.example.telegram_bot_message_holiday_day_is_today.services.HolidayService;
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
    private static final String HELP = "Help";
    private static final String HOLIDAYS = "Holidays";
    private static final String SAINT_HOLIDAYS = "Saint Holidays";

    private static final HolidayService parser = new HolidayService();

    public TelegramBotMessageHolidayDayIsToday(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            switch (message) {
                case START ->
                        sendInlineKeyboardMessageForStartCommand(chatId); // sendInlineKeyboardMessageForHelpCommand(chatId);
                case HELP -> sendInlineKeyboardMessageForHelpCommand(chatId); // helpCommand(chatId);
                case HOLIDAYS -> sendInlineKeyboardMessage(chatId); // sendInlineKeyboardMessage(chatId);
                case SAINT_HOLIDAYS ->
                        sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);  // sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);
            }
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update.getCallbackQuery());
        }

        /* TODO: добавить кнопку к второму сообщению "ГОРОСКОП", где если нет данных человека, спрашивать у него эти данные и вносить их в бд, чтобы потом доставить оттуда информацию, без дополнительного обращения к юзеру*/

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
            case HOLIDAYS -> sendInlineKeyboardMessage(chatId);  // sendInlineKeyboardMessage(chatId);
            case SAINT_HOLIDAYS ->
                    sendInlineKeyboardMessageForSaintHolidaysCommand(chatId); // sendInlineKeyboardMessageForSaintHolidaysCommand(chatId);
            case HELP -> sendInlineKeyboardMessageForHelpCommand(chatId); // helpCommand(chatId);
        }
    }


    private void sendInlineKeyboardMessage(long chatId) {
        String holidaysText = parser.getTodayHolidays(new StringBuilder());
        SendMessage message = messageService.createHolidaysMessage(chatId, holidaysText);
        executeMessage(message);
    }

    private void sendInlineKeyboardMessageForHelpCommand(long chatId) {
        SendMessage message = messageService.createHelpMessage(chatId);
        executeMessage(message);
    }

    private void sendInlineKeyboardMessageForSaintHolidaysCommand(long chatId) {
        String saintHolidayText = parser.getMoreTodayHolidays(new StringBuilder());
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

//    private void holidaysCommand(Long chatId) {
//        sendMessage(chatId, parser.getMoreTodayHolidays(new StringBuilder()));
//
//    }

    @Override
    public String getBotUsername() {
        return "WhichPrazdnikTodayBot";
    }

    public String getBotName() {
        return "Which holiday is today";
    }


//    private void startCommand(Long chatId, String userName) {
//        var text = """
//                Добро пожаловать в личный бот Алексея.
//
//                Здесь Вы сможете узнать какой сегодня праздник..
//
//                Для этого воспользуйтесь командами:
//                /holidays - праздники на сегодня
//
//                Дополнительные команды:
//                /help - получение справки
//                """;
//
//        var formattedText = String.format(text, userName, getBotName());
//        sendMessage(chatId, formattedText);
//    }


//    private void helpCommand(Long chatId) {
//        var text = """
//                Справочная информация по боту
//
//                Holidays - список всех праздников в РФ на сегодня.
//                Saint Holidays - список Церковных и региональных праздников.
//                Horoscope - гороскоп для вашего знака зодиака на сегодня.
//                WORK IN PROGRESS...
//                """;
//        sendMessage(chatId, text);
//    }

//    private void se\\dMessage(Long chatId, String text) {
//        var chatIdStr = String.valueOf(chatId);
//        var sendMessage = new SendMessage(chatIdStr, text);
//        ReplyKeyboardMarkup keyboardMarkup = getReplyKeyboardMarkup();
//        sendMessage.setReplyMarkup(keyboardMarkup);
//        try {
//            execute(sendMessage);
//        } catch (TelegramApiException e) {
//            LOG.error("Ошибка отправки сообщения", e);
//        }
//
//    }

//    private static ReplyKeyboardMarkup getReplyKeyboardMarkup() {
//        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
//        keyboardMarkup.setResizeKeyboard(true);
//        List<KeyboardRow> keyboardRowList = new ArrayList<>();
//        KeyboardRow row = new KeyboardRow();
//        row.add(HOLIDAYS);
//        row.add(HELP);
//        keyboardRowList.add(row);
//        row = new KeyboardRow();
//        row.add("Goroskop");
//        row.add("Random");
//        keyboardRowList.add(row);
//        keyboardMarkup.setKeyboard(keyboardRowList);
//        return keyboardMarkup;
//    }
//
//    private void sendInlineKeyboardMessage(long chaId) {
//        String strChatId = String.valueOf(chaId);
//        SendMessage message = new SendMessage(strChatId, parser.getTodayHolidays(new StringBuilder()));
//
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//        InlineKeyboardButton button = new InlineKeyboardButton();
//        button.setText("Другие праздники");
//        button.setCallbackData(SAINT_HOLIDAYS);
//
//        rowInline.add(button);
//        rowsInline.add(rowInline);
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//
//        message.setReplyMarkup(inlineKeyboardMarkup);
//
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void sendInlineKeyboardMessageForHelpCommand(long chaId) {
//
//        var text = """
//                Добро пожаловать в личный бот Алексея.
//
//                Здесь Вы сможете узнать какой сегодня праздник..
//
//
//                Holidays - праздники на сегодня
//                Saint Holidays - список Церковных и региональных праздников.
//                Horoscope - гороскоп для вашего знака зодиака на сегодня.
//                WORK IN PROGRESS...
//                """;
//
//        String strChatId = String.valueOf(chaId);
//        SendMessage message = new SendMessage(strChatId, text);
//
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//        InlineKeyboardButton button1 = new InlineKeyboardButton();
//        button1.setText("Праздники");
//        button1.setCallbackData(HOLIDAYS);
//
//        InlineKeyboardButton button2 = new InlineKeyboardButton();
//        button2.setText("Помощь");
//        button2.setCallbackData(HELP);
//
//        rowInline.add(button1);
//        rowInline.add(button2);
//        rowsInline.add(rowInline);
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//
//        message.setReplyMarkup(inlineKeyboardMarkup);
//
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void sendInlineKeyboardMessageForStartCommand(long chaId) {
//
//        var text = """
//                Справочная информация по боту
//
//                Holidays - список всех праздников в РФ на сегодня.
//                Saint Holidays - список Церковных и региональных праздников.
//                Horoscope - гороскоп для вашего знака зодиака на сегодня.
//                WORK IN PROGRESS...
//                """;
//
//        String strChatId = String.valueOf(chaId);
//        SendMessage message = new SendMessage(strChatId, text);
//
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//        InlineKeyboardButton button1 = new InlineKeyboardButton();
//        button1.setText("Праздники");
//        button1.setCallbackData(HOLIDAYS);
//
//        InlineKeyboardButton button2 = new InlineKeyboardButton();
//        button2.setText("Помощь");
//        button2.setCallbackData(HELP);
//
//        rowInline.add(button1);
//        rowInline.add(button2);
//        rowsInline.add(rowInline);
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//
//        message.setReplyMarkup(inlineKeyboardMarkup);
//
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void sendInlineKeyboardMessageForSaintHolidaysCommand(long chaId) {
//        String strChatId = String.valueOf(chaId);
//        SendMessage message = new SendMessage(strChatId, parser.getMoreTodayHolidays(new StringBuilder()));
//
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
//        List<InlineKeyboardButton> rowInline = new ArrayList<>();
//
//        InlineKeyboardButton button = new InlineKeyboardButton();
//        button.setText("ГОРОСКОП");
//        button.setCallbackData("гороскоп");
//
//        rowInline.add(button);
//        rowsInline.add(rowInline);
//
//        inlineKeyboardMarkup.setKeyboard(rowsInline);
//
//        message.setReplyMarkup(inlineKeyboardMarkup);
//
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

}
