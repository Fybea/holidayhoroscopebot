package com.example.telegram_bot_message_holiday_day_is_today;

public enum BotCommand {
    START("/start"),
    MENU("Menu"),
    HOLIDAYS("Holidays"),
    INTERNATIONAL_HOLIDAYS("International Holidays"),
    HOROSCOPE("Horoscope"),
    ARIES("Aries"),
    TAURUS("Taurus"),
    GEMINI("Gemini"),
    CANCER("Cancer"),
    LEO("Leo"),
    VIRGO("Virgo"),
    LIBRA("Libra"),
    SCORPIO("Scorpio"),
    SAGITTARIUS("Sagittarius"),
    CAPRICORN("Capricorn"),
    AQUARIUS("Aquarius"),
    PISCES("Pisces");

    private final String command;


    BotCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static BotCommand fromString(String text) {
        for (BotCommand b : BotCommand.values()) {
            if (b.command.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
