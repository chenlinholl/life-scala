//package com.eoi.test
//
//import com.eoi.core.common.Page
//import com.eoi.core.util.{IdHelper, JsonParse}
//import com.eoi.slick.dao.impl.UserInfoDaoAkkaImpl
//import com.eoi.slick.domain.Protocols.UserInfoEntity
//import org.junit.Test
//import org.slf4j.LoggerFactory
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//class SlickTest {
//
//  val userDao = new UserInfoDaoAkkaImpl
//
//  private val log = LoggerFactory.getLogger(getClass)
//
//  import scala.concurrent.ExecutionContext.Implicits.global
//
//  @Test
//  def test(): Unit = {
//    val id = IdHelper.id()
//    val user = UserInfoEntity(id, Some("张三_" + IdHelper.uuid()), Some(18), Some("浦东新区_" + IdHelper.uuid()));
//    val res = userDao.save(user)
//    val aaa = JsonParse.toJson(res)
//    log.info("json:{}", aaa)
//    log.debug("操作结果:{}", res)
//
//    // 断言
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//
//    val delRes = userDao.deleteById(id)
//
//    // 断言
//    assert(Await.result(delRes, 200 seconds).get("code").contains("200"))
//
//  }
//
//  @Test
//  def test1(): Unit = {
//    val json = "{\"id\":177355311017984,\"name\":\"张三\",\"age\":18,\"address\":\"浦东新区\"}";
//    val u = JsonParse.fromJson[UserInfoEntity](json)
//    log.info("{}", u)
//  }
//
//
//  @Test
//  def test2(): Unit = {
//    val res = userDao.list()
//    res.map {
//      ret => {
//        val list = ret.get("data")
//        for (x <- list) {
//          log.info("列表结果:{}", x)
//        }
//      }
//    }
//
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//  }
//
//  @Test
//  def test3(): Unit = {
//    val res = userDao.findById(177404856336384L)
//    log.info("按照ID查询数据结果:{}", JsonParse.toJson(res))
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//  }
//
//  @Test
//  def test4(): Unit = {
//    val res = userDao.deleteById(1L)
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//  }
//
//
//  @Test
//  def test5(): Unit = {
//    val user = UserInfoEntity(177405865074688L, Some("小花啊"), Some(18), Some("广兰路"))
//    val res = userDao.updateById(177405865074688L, user)
//    log.info("修改结果:{}", JsonParse.toJson(res))
//
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//  }
//
//  @Test
//  def test6(): Unit = {
//
//    val page: Page = Page(2, 4)
//    val res = userDao.page(page)
//
//    log.info("分页查询数据:{}", JsonParse.toJson(res))
//
//    assert(Await.result(res, 200 seconds).get("code").contains("200"))
//  }
//}
