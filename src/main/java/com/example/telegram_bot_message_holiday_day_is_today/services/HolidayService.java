package com.example.telegram_bot_message_holiday_day_is_today.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class HolidayService {

    public String getTodayHolidays(StringBuilder stringBuilder) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.forLanguageTag("ru"));
            stringBuilder.append(now.format(formatter) + " " + "\uD83D\uDDD3" + " " + now.format(DateTimeFormatter.ofPattern("EEEE", Locale.forLanguageTag("ru")))).append("\n\n");
            var document = Jsoup.connect("https://kakoysegodnyaprazdnik.ru/").get();
            Elements superElement = document.select("div[id=prin]").prevAll();
            Elements answer = superElement.select("span[itemprop=text]");
            for (Element name : answer) {
                stringBuilder.append("▫\uFE0F").append(name.text()).append(", \n");
            }
            stringBuilder.append("\n").append("");
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getMoreTodayHolidays(StringBuilder stringBuilder) {
        try {
            var document = Jsoup.connect("https://kakoysegodnyaprazdnik.ru/").get();
            Elements superElement = document.select("div[id=prin]").nextAll();
            Elements answer = superElement.select("span[itemprop=text]");
            for (Element name : answer) {
                stringBuilder.append("▪\uFE0F").append(name.text()).append(", \n");
            }
            stringBuilder.append("\n").append("");
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
