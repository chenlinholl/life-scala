//package com.eoi.test;
//
//import org.junit.Test;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//
//public class DateTest1 {
//
//    @Test
//    public void test() {
//        try {
//            //格式化时间
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//            String time = sdf.format(new Date());
//            System.out.println(time);
//
//            //解析时间 2016-01-05T15:06:58+0800
//            Date date = sdf.parse(time);
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test1() {
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now();
//        String inputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(inputFormat);
//        String input = "2018-06-29T04:00:00.000Z";
//
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(date + "\n" + time + "\n" + now);
//    }
//}
