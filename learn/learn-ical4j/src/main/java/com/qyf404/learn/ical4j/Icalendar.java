package com.qyf404.learn.ical4j;


import com.sun.tools.doclets.internal.toolkit.builders.PropertyBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.Calendars;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class ICalendar {
    public static void main(String[] args) throws IOException, ParserException, ValidationException, URISyntaxException, ParseException {


        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        System.out.println(calendar.toString());



        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
        cal.set(java.util.Calendar.DAY_OF_MONTH, 25);
        VEvent christmas = new VEvent(new Date(cal.getTime()), "Christmas Day");
// initialise as an all-day event..
//        christmas.getProperties().getProperty(Property.DTSTART).getParameters().add(Value.DATE);

        christmas.getProperties().add(PropertyFactoryImpl.getInstance().createProperty(Property.UID));


        calendar.getComponents().add(christmas);

//
//
///////
//        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
//        TimeZone timezone = registry.getTimeZone("Australia/Melbourne");
//
//        java.util.Calendar cal = java.util.Calendar.getInstance(timezone);
//        cal.set(java.util.Calendar.YEAR, 2005);
//        cal.set(java.util.Calendar.MONTH, java.util.Calendar.NOVEMBER);
//        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
//        cal.set(java.util.Calendar.HOUR_OF_DAY, 15);
//        cal.clear(java.util.Calendar.MINUTE);
//        cal.clear(java.util.Calendar.SECOND);
//
//        DateTime dt = new DateTime(cal.getTime());
//        dt.setTimeZone(timezone);
//        VEvent melbourneCup = new VEvent(dt, "Melbourne Cup");
//        System.out.println(calendar.toString());
////

        FileOutputStream fout = new FileOutputStream("mycalendar.ics");

        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(calendar, fout);

//        Calendar calendar = Calendars.load("mycalendar.ics");
    }
}
