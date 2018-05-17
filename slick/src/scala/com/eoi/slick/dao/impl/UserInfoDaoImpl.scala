package com.eoi.slick.dao.impl

import com.eoi.slick.dao.UserDao
import com.eoi.slick.domain.Protocols.UserInfoEntity
import com.eoi.slick.util.DatabaseService
import org.slf4j.LoggerFactory

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


class UserInfoDaoImpl extends com.eoi.slick.domain.EntityTable with UserDao {

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
  override def save(user: UserInfoEntity): UserInfoEntity = {

    val query = for {
      a <- userInfos += user
    } yield user

    db.run(query).onComplete(u => {
      log.info("保存:{}", u)
    })
    user
  }

  /**
    * 查询列表
    *
    * @return
    */
  override def list(): Future[Seq[UserInfoEntity]] = {
    val res = db.run(userInfos.result)
    // 等待异步任务处理完成
    Await.result(res, Duration.Inf)
    res
  }

  /**
    * 按照ID查询
    *
    * @param id
    */
  override def findById(id: Long): Future[Option[UserInfoEntity]] = {
    val res = db.run(userInfos.filter(_.id === id).result.headOption)
    // 等待异步任务处理完成
    Await.result(res, Duration.Inf)
    res
  }

  /**
    * 根据ID删除数据
    *
    * @param id
    */
  override def deleteById(id: Long): Unit = {
    val res = db.run(userInfos.filter(_.id === id).delete)
    // 等待异步任务处理完成
    Await.result(res, Duration.Inf)
  }

  /**
    * 根据ID修改数据
    *
    * @param id
    * @param u
    */
  override def updateById(id: Long, u: UserInfoEntity): UserInfoEntity = {
    val query = for {
      u <- userInfos.filter(_.id === id)
        .map(s => (s.name, s.age, s.address))
        .update((u.name, u.age, u.address))
      s <- userInfos.filter(_.id === id).result.headOption
    } yield s
    Await.result(db.run(query), Duration.Inf)
    u
  }
}
