package com.eoi.test

import com.eoi.core.util.JsonParse
import com.eoi.slick.dao.impl.DeptDaoImpl
import org.junit.Test
import org.slf4j.LoggerFactory

class DeptTest {

  private val log = LoggerFactory.getLogger(getClass)

  private val deptDao: DeptDaoImpl = new DeptDaoImpl

  @Test
  def test(): Unit = {
    val res = deptDao.join()
    print(res)
  }

  @Test
  def test1(): Unit = {
    val res = deptDao.innerJoin()
    print(res)
  }

  @Test
  def test2(): Unit = {
    val res = deptDao.leftJoin()
    print(res)
  }

  def print(res: Any): Unit = {
    log.info("查询结果:{}", JsonParse.toJson(res))
  }
}
