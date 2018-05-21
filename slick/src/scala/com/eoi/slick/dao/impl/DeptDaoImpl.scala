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
      if e.deptId === d.deptId
      // yield 设置显示的字段信息
    } yield (e.name, d.deptId, d.deptName)

    db.run(query.result).flatMap {
      res => {
        Future(success(StateCode.CODE_200, res))
      }
    }
  }

  /**
    * innerJoin 查询
    *
    * @return
    */
  def innerJoin(): Future[Map[String, Any]] = {
    // val query = employees.join(depts).on((emp, dep) => emp.deptId === dep.deptId)
    val innerJoin = for {
      (emp, dept) <- employees join depts
      if emp.deptId === dept.deptId
    } yield (emp.deptId, emp.name, dept.deptName)
    db.run(innerJoin.result).flatMap {
      res => {
        Future(success(StateCode.CODE_200, res))
      }
    }
  }

  /**
    * left join 查询
    *
    * @return
    */
  def leftJoin(): Future[Map[String, Any]] = {
    val leftJoin = for {
      (emp, dept) <- employees.joinLeft(depts).on(_.deptId === _.deptId)
    } yield (emp.deptId, emp.name, dept.map(_.deptName))

    db.run(leftJoin.result).flatMap {
      res => {
        val result = res.map { e =>
          Map("deptId" -> e._1, "empName" -> e._2, "deptName" -> e._3)
        }
        Future(success(StateCode.CODE_200, result))
      }
    }
  }

  /**
    * 基于交叉连接或外部连接
    *
    * @return
    */
  def zipQueryJoin(): Future[Map[String, Any]] = {
    val zipQuery = for {
      (emp, dept) <- employees.zip(depts)
    } yield (emp.deptId, emp.name, dept.deptName)

    db.run(zipQuery.result).flatMap {
      res => {
        Future(success(StateCode.CODE_200, res))
      }
    }
  }

  /**
    *
    * @return
    */
  def zipWithIndexJoin(): Future[Map[String, Any]] = {
    val zipWithIndexJoin = for {
      (emp, dept) <- employees.zip(depts)
    } yield (emp.deptId, emp.name, dept.deptName)

    db.run(zipWithIndexJoin.result).flatMap {
      res => {
        Future(success(StateCode.CODE_200, res))
      }
    }
  }

  def delete(id: Long): Option[String] = {

    val q = employees.filter(_.id === id)
    val action = q.delete
    db.run(action)

    action.statements.headOption
  }

  def save(): Unit = {
  }
}
