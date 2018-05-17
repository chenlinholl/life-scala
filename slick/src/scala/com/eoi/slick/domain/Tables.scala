package com.eoi.slick.domain

import com.eoi.slick.domain.Protocols.UserInfoEntity
import com.eoi.slick.util.DatabaseService

object Protocols {

  final case class UserInfoEntity(id: Long, name: Option[String] = None, age: Option[Int] = None, address: Option[String] = None)

}

trait EntityTable {
  protected val databaseService: DatabaseService

  import databaseService.driver.api._


  class UserInfo(_tableTag: Tag) extends Table[UserInfoEntity](_tableTag, Some("zt_test"), "user_info") {
    def * = (id, name, age, address) <> (UserInfoEntity.tupled, UserInfoEntity.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, age, address).shaped.<>({ r => import r._; _1.map(_ => UserInfoEntity.tupled((_1.get, _2, _3, _4))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(50,true), Default(None) */
    val name: Rep[Option[String]] = column[Option[String]]("name", O.Default(None))
    /** Database column age SqlType(INT), Default(None) */
    val age: Rep[Option[Int]] = column[Option[Int]]("age", O.Default(None))
    /** Database column address SqlType(VARCHAR), Length(255,true), Default(None) */
    val address: Rep[Option[String]] = column[Option[String]]("address", O.Default(None))
  }

  lazy val userInfos = new TableQuery(tag => new UserInfo(tag))
}
