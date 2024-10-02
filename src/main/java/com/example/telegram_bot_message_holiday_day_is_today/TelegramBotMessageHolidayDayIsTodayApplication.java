package com.example.telegram_bot_message_holiday_day_is_today;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramBotMessageHolidayDayIsTodayApplication  {

	private static final Logger logger = LoggerFactory.getLogger(TelegramBotMessageHolidayDayIsTodayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotMessageHolidayDayIsTodayApplication.class, args);
		logger.debug("Debug: ");
	}

}
