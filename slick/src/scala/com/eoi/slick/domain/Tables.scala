package com.eoi.slick.domain

import com.eoi.slick.domain.Protocols._
import com.eoi.slick.util.DatabaseService

object Protocols {

  /**
    * 实体对象
    *
    * @param id
    * @param name
    * @param age
    * @param address
    */
  final case class UserInfoEntity(id: Long, name: Option[String] = None, age: Option[Int] = None, address: Option[String] = None)

  final case class DeptEntity(deptId: Long, deptName: String)

  final case class EmployeeEntity(id: Long, name: String, age: Option[Int] = None, birthday: Option[java.sql.Timestamp] = None, deptId: Option[Long] = None)

}

/**
  * 实体对象表
  */
trait EntityTable {
  protected val databaseService: DatabaseService

  import databaseService.driver.api._


  class Dept(_tableTag: Tag) extends Table[DeptEntity](_tableTag, Some("zt_test"), "dept") {
    def * = (deptId, deptName) <> (DeptEntity.tupled, DeptEntity.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(deptId), Rep.Some(deptName)).shaped.<>({ r => import r._; _1.map(_ => DeptEntity.tupled((_1.get, _2.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column dept_id SqlType(BIGINT), AutoInc, PrimaryKey */
    val deptId: Rep[Long] = column[Long]("dept_id", O.AutoInc, O.PrimaryKey)
    /** Database column dept_name SqlType(VARCHAR), Length(50,true) */
    val deptName: Rep[String] = column[String]("dept_name")
  }

  lazy val depts = new TableQuery(tag => new Dept(tag))


  class Employee(_tableTag: Tag) extends Table[EmployeeEntity](_tableTag, Some("zt_test"), "employee") {
    def * = (id, name, age, birthday, deptId) <> (EmployeeEntity.tupled, EmployeeEntity.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(name), age, birthday, deptId).shaped.<>({ r => import r._; _1.map(_ => EmployeeEntity.tupled((_1.get, _2.get, _3, _4, _5))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), AutoInc, PrimaryKey */
    val id: Rep[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name SqlType(VARCHAR), Length(50,true) */
    val name: Rep[String] = column[String]("name")
    /** Database column age SqlType(INT), Default(None) */
    val age: Rep[Option[Int]] = column[Option[Int]]("age", O.Default(None))
    /** Database column birthday SqlType(DATETIME), Default(None) */
    val birthday: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("birthday", O.Default(None))
    /** Database column dept_id SqlType(BIGINT), Default(None) */
    val deptId: Rep[Option[Long]] = column[Option[Long]]("dept_id", O.Default(None))
  }

  lazy val employees = new TableQuery(tag => new Employee(tag))


  class UserInfo(_tableTag: Tag) extends Table[UserInfoEntity](_tableTag, Some("zt_test"), "user_info") {
    def * = (id, name, age, address) <> (UserInfoEntity.tupled, UserInfoEntity.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), name, age, address).shaped.<>({ r => import r._; _1.map(_ => UserInfoEntity.tupled((_1.get, _2, _3, _4))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(BIGINT), PrimaryKey */
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
