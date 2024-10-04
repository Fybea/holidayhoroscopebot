package com.example.telegram_bot_message_holiday_day_is_today.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class HoroscopeService {
    public String getTodayHoroscopes(StringBuilder stringBuilder, String zodiac) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.forLanguageTag("ru"));

            var document = Jsoup.connect("https://horo.mail.ru/prediction/" + zodiac + "/today/").get();
            Element element = document.select("main[itemprop=articleBody]").first();
            Elements text = element.select("div[article-item-type=html]");
            stringBuilder.append("Гороскоп от " + (now.format(formatter) + " " + "\uD83D\uDDD3" + " "));
            stringBuilder.append("\n").append("Знак зодиака: ").append(getZodiacSign(zodiac)).append("\n\n");
            for (Element horoscope : text) {
                stringBuilder.append(horoscope.text()).append("\n\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getZodiacSign(String zodiac) {

        HashMap<String, String> zodiacs = new HashMap<>();

        zodiacs.put("Aries", "\uD83D\uDC0F Овен");
        zodiacs.put("Taurus", "\uD83D\uDC02 Телец");
        zodiacs.put("Gemini", "\uD83D\uDC6F\u200D♂\uFE0F Близнецы");
        zodiacs.put("Cancer", "\uD83E\uDD80 Рак");
        zodiacs.put("Leo", "\uD83E\uDD81 Лев");
        zodiacs.put("Virgo", "♍\uFE0F Дева");
        zodiacs.put("Libra", "⚖\uFE0F Весы");
        zodiacs.put("Scorpio", "\uD83E\uDD82 Скорпион");
        zodiacs.put("Sagittarius", "\uD83C\uDFF9 Стрелец");
        zodiacs.put("Capricorn", "\uD83D\uDC10 Козерог");
        zodiacs.put("Aquarius", "♒ Водолей");
        zodiacs.put("Pisces", "\uD83D\uDC1F Рыбы");

        for (Map.Entry<String, String> map : zodiacs.entrySet()) {
            if (map.getKey().equals(zodiac)) {
                return map.getValue();
            }
        }
        return null;
    }
}
