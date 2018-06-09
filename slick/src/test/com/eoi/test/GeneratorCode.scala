//package com.eoi.test
//
//import generator.SourceCodeGenerator
//import org.junit.Test
//
//
//class GeneratorCode {
//
//
////  @Test
//  def generator(): Unit = {
//
//    val as = Array[String](
//      //slick.driver.MySQLDriver,JdbcProfile
//      "slick.jdbc.MySQLProfile",
//      // 数据库驱动 com.mysql.jdbc.Driver,MysqlDataSource,com.mysql.cj.jdbc.Driver
//      "com.mysql.jdbc.Driver",
//      // 数据库连接字符串
//      "jdbc:mysql://192.168.31.46:3306/zt_test?characterEncoding=utf8&useSSL=false",
//      // 生产代码位置
//      "generator_code/",
//      // 生成的包名
//      "com.eoi.slick.domain",
//      // 用户名密码
//      "root", "User@123")
//
//    SourceCodeGenerator.main(as)
//  }
//}
