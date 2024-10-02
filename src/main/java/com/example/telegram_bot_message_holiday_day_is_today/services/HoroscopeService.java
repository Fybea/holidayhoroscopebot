package com.example.telegram_bot_message_holiday_day_is_today.services;

public class HoroscopeService {

    public String parse(StringBuilder stringBuilder) {
        // TODO: СОХРАНЯТЬ В БД ЗНАК ЗАДИАКА ПОЛЬЗОВАТЕЛЯ/ ЕСЛИ ЕСТЬ ДАТА РОЖДЕНИЯ В ПРОФИЛЕ/ ЕСЛИ НЕТ ТО СПРАШИВАТЬ КАКОЙ У ПОЛЬЗОВАТЕЛЯ ЗНАК ЗАДИАКА В ВИДЕЕ КНОПОК/
//
//        try {
//            var document = Jsoup.connect("https://horo.mail.ru/prediction/").get();
//            Element element = document.select("div[class=listing_wr]").first();
//           // Elements names = element.select("span[itemprop=text]");
//            Elements elements = new Elements();
//            while (!element.is("div[class=hr-hr_osen abv]")) {
//                elements.add(element);
//            }
//            Elements names = elements.select("span[itemprop=text]");
//            for (Element name : names) {
//                stringBuilder.append("▫\uFE0F").append(name.text()).append(", \n").append("\n");
//            }
//            stringBuilder.insert(0, "▫\uFE0F");
//            System.out.println(stringBuilder);
//            return stringBuilder.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
