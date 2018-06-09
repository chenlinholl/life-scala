//package com.eoi.test
//
//import java.util.Date
//
//import com.eoi.core.util.{IdHelper, JsonParse}
//import com.eoi.slick.dao.impl.DeptDaoImpl
//import com.eoi.slick.entity.User
//import org.junit.Test
//import org.slf4j.LoggerFactory
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//class DeptTest {
//
//  private val log = LoggerFactory.getLogger(getClass)
//
//  private val deptDao: DeptDaoImpl = new DeptDaoImpl
//
//  @Test
//  def test(): Unit = {
//    val res = deptDao.join()
//
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//
//    print(res)
//  }
//
//  @Test
//  def test1(): Unit = {
//    val res = deptDao.innerJoin()
//
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//
//    print(res)
//  }
//
//  @Test
//  def test2(): Unit = {
//    val res = deptDao.leftJoin()
//
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//
//    print(res)
//  }
//
//  @Test
//  def test3(): Unit = {
//    val res = deptDao.zipQueryJoin()
//
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//
//    print(res)
//  }
//
//  @Test
//  def test4(): Unit = {
//    val res = deptDao.zipWithIndexJoin()
//
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//
//    print(res)
//  }
//
//  @Test
//  def test5(): Unit = {
//    val user = User(id = IdHelper.id(), birthday = new Date(), name = "张三", address = "上海市张江高科")
//
//    val u = new User("123", "aaa")
//    log.info("构造函数测试:{}", JsonParse.toJson(user))
//    log.info("{}", JsonParse.toJson(u))
//  }
//
//  def print(res: Any): Unit = {
//    log.info("查询结果:{}", JsonParse.toJson(res))
//  }
//}
