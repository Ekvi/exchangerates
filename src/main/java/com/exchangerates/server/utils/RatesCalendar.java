package com.exchangerates.server.utils;


import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RatesCalendar {
    private Logger logger = Logger.getLogger(RatesCalendar.class);

    public List<String> getDaysAndMonth(Date finishDate) {
        List<String> dates = new ArrayList<String>();
        Calendar oldCalendar = Calendar.getInstance();
        oldCalendar.setTime(finishDate);

        Date startDate = getStartDate(finishDate);
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(startDate);

        if(newCalendar.get(Calendar.MONTH) != oldCalendar.get(Calendar.MONTH)) {
            dates.addAll(setDates(newCalendar.get(Calendar.DAY_OF_MONTH),
                    newCalendar.getActualMaximum(Calendar.DAY_OF_MONTH), newCalendar.get(Calendar.MONTH) + 1));
            dates.addAll(setDates(1, oldCalendar.get(Calendar.DAY_OF_MONTH), oldCalendar.get(Calendar.MONTH) + 1));

        } else {
            dates.addAll(setDates(newCalendar.get(Calendar.DAY_OF_MONTH),
                    oldCalendar.get(Calendar.DAY_OF_MONTH), oldCalendar.get(Calendar.MONTH) + 1));
        }
        return dates;
    }

    private List<String> setDates(int start, int finish, int month) {
        List<String> dates = new ArrayList<String>();

        for(int i = start; i <= finish; i++) {
            String str = i + ".";
            str += (month == 10 || month == 11 || month == 12) ? month : "0" + month;
            dates.add(str);
        }
        return dates;
    }

    public Date getStartDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date startDate;

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        int daysOfWeek = 8;
        int startDay;
        int prevMonth;
        int divided = daysOfWeek - day;

        if(day > daysOfWeek -1) {
            startDay = day - (daysOfWeek - 1);
            startDate = createDate(startDay, month, year);
        } else {
            if(month > 1) {
                prevMonth = month - 1;
                calendar.set(Calendar.MONTH, prevMonth);

                int start = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - (divided - 1);
                startDate = createDate(start, prevMonth, year);
            } else {
                prevMonth = 11;
                calendar.set(Calendar.YEAR, year - 1);
                calendar.set(Calendar.MONTH, prevMonth);

                int start = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - (divided - 1);
                startDate = createDate(start, prevMonth, (year - 1));
            }
        }
       return startDate;
    }

    private Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);

        return calendar.getTime();
    }

    public Date getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public Date convertStringToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd M yyyy");
        Date date = null;

        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            logger.info("can't parse date " + strDate);
            e.printStackTrace();
        }

        return date;
    }
}
