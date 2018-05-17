package com.eoi.test

import com.eoi.slick.dao.impl.UserInfoDaoImpl
import com.eoi.slick.domain.Protocols.UserInfoEntity
import com.eoi.slick.util.{IdHelper, JsonUtil}
import org.junit.Test
import org.slf4j.LoggerFactory

class SlickTest {

  val userDao = new UserInfoDaoImpl

  private val log = LoggerFactory.getLogger(getClass)

  import scala.concurrent.ExecutionContext.Implicits.global

  @Test
  def test(): Unit = {
    val user = UserInfoEntity(IdHelper.id(), Some("张三_" + IdHelper.uuid()), Some(18), Some("浦东新区_" + IdHelper.uuid()));
    val u = userDao.save(user)
    val aaa = JsonUtil.toJson(u)
    log.info("json:{}", aaa)
    log.debug("操作结果:{}", u)
  }

  @Test
  def test1(): Unit = {
    val json = "{\"id\":177355311017984,\"name\":\"张三\",\"age\":18,\"address\":\"浦东新区\"}";
    val u = JsonUtil.fromJson[UserInfoEntity](json)
    log.info("{}", u)
  }


  @Test
  def test2(): Unit = {
    var i = 0
    userDao.list().foreach(user => {
      log.info("下标:{},数据:{}", i, JsonUtil.toJson(user))
      i = i + 1
    })
  }

  @Test
  def test3(): Unit = {
    val res = userDao.findById(177404856336384L)
    log.info("按照ID查询数据结果:{}", JsonUtil.toJson(res))
  }

  @Test
  def test4(): Unit = {
    userDao.deleteById(1L)
  }


  @Test
  def test5(): Unit = {
    val user = UserInfoEntity(177405865074688L, Some("小花啊"), Some(18), Some("广兰路"))
    val res = userDao.updateById(177405865074688L, user)
    log.info("修改结果:{}", JsonUtil.toJson(res))
  }
}
