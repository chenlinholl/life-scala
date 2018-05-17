package com.eoi.slick

import com.eoi.slick.util.Snowflake
import org.slf4j.LoggerFactory

object SlickApp {


  val logger = LoggerFactory.getLogger(getClass)


  def main(args: Array[String]): Unit = {
    logger.info("日志级别测试:{}", Snowflake.nextId())
    println(Snowflake.nextId())
  }
}
