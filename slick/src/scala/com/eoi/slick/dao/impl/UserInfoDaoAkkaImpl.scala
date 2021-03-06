package com.eoi.slick.dao.impl

import com.eoi.core.common.{BaseService, Page, StateCode}
import com.eoi.core.util.IdHelper
import com.eoi.slick.domain.Protocols.UserInfoEntity
import com.eoi.slick.util._
import org.slf4j.LoggerFactory

import scala.concurrent.Future
import scala.util._


class UserInfoDaoAkkaImpl extends com.eoi.slick.domain.EntityTable with BaseService {

  private val log = LoggerFactory.getLogger(getClass)

  override protected val databaseService: DatabaseService = DatabaseService.databaseService

  import databaseService._
  import databaseService.driver.api._

  import scala.concurrent.ExecutionContext.Implicits.global

  /**
    * 新增
    *
    * @param user
    * @return
    */
  def save(user: UserInfoEntity): Future[Map[String, Any]] = {

    val u = UserInfoEntity(IdHelper.id(), user.name, user.age, user.address)

    val query = for {
      _ <- userInfos += u
    } yield u

    db.run(query.asTry).flatMap {
      case Success(s) =>
        log.info(" save user success")
        Future(success("200", "新增成功!", s))
      case Failure(ex) =>
        log.error("保存用户信息失败")
        Future(failure("500", ex.getMessage))
    }
  }

  /**
    * 查询列表
    *
    * @return
    */
  def list(): Future[Map[String, Any]] = {
    db.run(userInfos.result).flatMap {
      res =>
        Future(success("200", "查询成功!", res))
    }
  }


  /**
    * 分页查询列表
    *
    * @param page
    * @return
    */
  def page(page: Page): Future[Map[String, Any]] = {

    val count = for {
      a <- userInfos.drop(page.pageNo).take(page.pageSize).result
    } yield a.length

    db.run(userInfos.sortBy(_.id.desc).drop(page.pageNo).take(page.pageSize).result).flatMap {
      res =>
        val result = {
          Map("total" -> res.length, "current" -> page.pageNo, "pageSize" -> page.pageSize, "data" -> res)
        }
        Future(success(StateCode.CODE_200, result))
    }
  }

  /**
    * 按照ID查询
    *
    * @param id
    */
  def findById(id: Long): Future[Map[String, Any]] = {
    db.run(userInfos.filter(_.id === id).result.headOption).flatMap {
      res =>
        Future(success("200", "按照ID查询数据", res))
    }
  }

  /**
    * 根据ID删除数据
    *
    * @param id
    */
  def deleteById(id: Long): Future[Map[String, Any]] = {
    db.run(userInfos.filter(_.id === id).delete.asTry).flatMap {
      case Success(res) => {
        log.info("删除操作，ID={}", id)
        Future(success("200", "删除成功", res))
      }
      case Failure(ex) => {
        log.error("删除失败!")
        Future(failure("500", ex.getMessage))
      }
    }
  }

  /**
    * 根据ID修改数据
    *
    * @param id
    * @param u
    */
  def updateById(id: Long, u: UserInfoEntity): Future[Map[String, Any]] = {
    val query = for {
      _ <- userInfos.filter(_.id === id)
        .map(s => (s.name, s.age, s.address))
        .update((u.name, u.age, u.address))
      s <- userInfos.filter(_.id === id).result.headOption
    } yield s

    db.run(query.asTry).flatMap {
      case Success(res) => {
        log.info("修改成功")
        Future(success("200", "修改成功", res))
      }
      case Failure(ex) => {
        log.error("修改失败!")
        Future(failure("500", ex.getMessage))
      }
    }
  }
}