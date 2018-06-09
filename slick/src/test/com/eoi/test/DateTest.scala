//package com.eoi.test
//
//import java.text.SimpleDateFormat
//import java.util.Date
//
//import com.eoi.slick.util.BeanMapper
//import org.junit.Test
//import org.slf4j.{Logger, LoggerFactory}
//
//class DateTest {
//
//  val log: Logger = LoggerFactory.getLogger(this.getClass)
//
//
//  @Test
//  def test(): Unit = {
//
//
//    val sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sssZ")
//    val time = sdf.format(new Date)
//    log.info(s"$time")
//
//    val result = sdf.parse(time)
//    log.info(s"$result")
//
//  }
//
//}
