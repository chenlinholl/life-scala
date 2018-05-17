package com.eoi.slick.dao

import com.eoi.slick.domain.Protocols.UserInfoEntity

import scala.concurrent.Future

trait UserDao {

  def save(user: UserInfoEntity): UserInfoEntity

  def list(): Future[Seq[UserInfoEntity]]

  def findById(id: Long): Future[Option[UserInfoEntity]]

  def deleteById(id: Long): Unit

  def updateById(id: Long, u: UserInfoEntity): UserInfoEntity
}
