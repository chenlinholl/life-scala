package com.eoi.slick.dao.impl

import com.eoi.core.common.{BaseService, StateCode}
import com.eoi.slick.domain.EntityTable
import com.eoi.slick.util.DatabaseService
import org.slf4j.LoggerFactory

import scala.concurrent.Future

class DeptDaoImpl extends EntityTable with BaseService {

  private val log = LoggerFactory.getLogger(getClass)

  override protected val databaseService: DatabaseService = DatabaseService.databaseService

  import databaseService._
  import databaseService.driver.api._

  import scala.concurrent.ExecutionContext.Implicits.global

  /**
    * 表连接查询
    *
    * @return
    */
  def join(): Future[Map[String, Any]] = {

    val query = for {
      e <- employees
      d <- depts

      // 设置连接条件
      if e.id === d.deptId
      // yield 设置显示的字段信息
    } yield (e.name, d.deptId, d.deptName)

    db.run(query.result).flatMap {
      res => {
        Future(success(StateCode.CODE_200, res))
      }
    }
  }
}
