package com.eoi.test

import com.eoi.core.util.JsonParse
import com.eoi.slick.dao.impl.DeptDaoImpl
import org.junit.Test
import org.slf4j.LoggerFactory

class DeptTest {

  private val log = LoggerFactory.getLogger(getClass)

  val deptDao: DeptDaoImpl = new DeptDaoImpl

  @Test
  def test(): Unit = {
    val res = deptDao.join()
    log.info("join结果:{}", JsonParse.toJson(res))
  }
}
