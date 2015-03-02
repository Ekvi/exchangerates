package com.exchangerates.server.utils;

import com.exchangerates.shared.Constants;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestRatesCalendar {
    private RatesCalendar ratesCalendar = new RatesCalendar();

    @Test
    public void testGetDaysAndMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.YEAR, 2015);

        Date date = calendar.getTime();
        List<String> dates = ratesCalendar.getDaysAndMonth(date);

        assertEquals(Constants.DAYS_FOR_GRAPHIC, dates.size());
        assertEquals("13.12", dates.get(0));
        assertEquals("16.12", dates.get(3));
        assertEquals("20.12", dates.get(7));
    }

    @Test
    public void testGetDaysAndMonth2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.YEAR, 2015);

        Date date = calendar.getTime();
        List<String> dates = ratesCalendar.getDaysAndMonth(date);

        assertEquals(Constants.DAYS_FOR_GRAPHIC, dates.size());
        assertEquals("27.08", dates.get(0));
        assertEquals("30.08", dates.get(3));
        assertEquals("3.09", dates.get(7));
    }

    @Test
    public void testGetDaysAndMonth3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, 2015);

        Date date = calendar.getTime();
        List<String> dates = ratesCalendar.getDaysAndMonth(date);

        for(String d : dates) {
            System.out.print(d + "  ");
        }
        assertEquals(Constants.DAYS_FOR_GRAPHIC, dates.size());
        assertEquals("29.12", dates.get(0));
        assertEquals("1.01", dates.get(3));
        assertEquals("5.01", dates.get(7));
    }

    @Test
    public void testGetDaysAndMonth4() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 6);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.YEAR, 2015);

        Date date = calendar.getTime();
        List<String> dates = ratesCalendar.getDaysAndMonth(date);

        for(String d : dates) {
            System.out.print(d + "  ");
        }
        assertEquals(Constants.DAYS_FOR_GRAPHIC, dates.size());
        assertEquals("30.03", dates.get(0));
        assertEquals("2.04", dates.get(3));
        assertEquals("6.04", dates.get(7));
    }

    @Test
    public void testGetDaysAndMonth5() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.YEAR, 2015);

        Date date = calendar.getTime();
        List<String> dates = ratesCalendar.getDaysAndMonth(date);

        for(String d : dates) {
            System.out.print(d + "  ");
        }
        assertEquals(Constants.DAYS_FOR_GRAPHIC, dates.size());
        assertEquals("24.02", dates.get(0));
        assertEquals("27.02", dates.get(3));
        assertEquals("3.03", dates.get(7));
    }


    @Test
    public void testGetStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 6);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.YEAR, 2015);

        Date date = calendar.getTime();
        Date actual = ratesCalendar.getStartDate(date);

        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(actual);

        assertEquals(30, newCalendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(2, newCalendar.get(Calendar.MONTH));
        assertEquals(2015, newCalendar.get(Calendar.YEAR));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 2);
        calendar2.set(Calendar.MONTH, 1);
        calendar2.set(Calendar.YEAR, 2015);

        Date date2 = calendar2.getTime();
        Date actual2 = ratesCalendar.getStartDate(date2);
        Calendar newCalendar2 = Calendar.getInstance();
        newCalendar2.setTime(actual2);

        assertEquals(26, newCalendar2.get(Calendar.DAY_OF_MONTH));
        assertEquals(11, newCalendar2.get(Calendar.MONTH));
        assertEquals(2014, newCalendar2.get(Calendar.YEAR));

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.DAY_OF_MONTH, 20);
        calendar3.set(Calendar.MONTH, 11);
        calendar3.set(Calendar.YEAR, 2015);

        Date date3 = calendar3.getTime();
        Date actual3 = ratesCalendar.getStartDate(date3);
        Calendar newCalendar3 = Calendar.getInstance();
        newCalendar3.setTime(actual3);

        assertEquals(13, newCalendar3.get(Calendar.DAY_OF_MONTH));
        assertEquals(11, newCalendar3.get(Calendar.MONTH));
        assertEquals(2015, newCalendar3.get(Calendar.YEAR));
    }
}
