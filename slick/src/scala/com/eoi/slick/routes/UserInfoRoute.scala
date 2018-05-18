package com.eoi.slick.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.eoi.core.common.Page
import com.eoi.slick.dao.impl._
import com.eoi.slick.domain.Protocols.UserInfoEntity
import com.eoi.core.util.JsonParse._

/**
  * 路由控制器 类似于spring mvc 中的controller
  */
class UserInfoRoute {

  val userDao: UserInfoDaoAkkaImpl = new UserInfoDaoAkkaImpl

  // 创建路由对象，指定前缀
  val route: Route = pathPrefix("api") {
    // 设置请求路径
    path("user") {
      post {
        entity(as[UserInfoEntity]) {
          req =>
            complete(userDao.save(req))
        }
      } ~ get {
        complete(userDao.list())
      }
    } ~
      path("user" / LongNumber) {
        id => {
          get {
            complete(userDao.findById(id))
          } ~ delete {
            complete(userDao.deleteById(id))
          } ~ put {
            entity(as[UserInfoEntity]) {
              req => complete(userDao.updateById(id, req))
            }
          }
        }
      } ~
      path("user-page") {
        post {
          entity(as[Page]) {
            req =>
              complete(userDao.page(req))
          }
        }
      }
  }
}
