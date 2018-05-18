package com.eoi.slick.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.eoi.core.util.JsonParse._
import com.eoi.slick.dao.impl.DeptDaoImpl

class DeptRoute {

  val deptDao = new DeptDaoImpl

  val route: Route = pathPrefix("api") {
    path("dept") {
      get {
        complete(deptDao.leftJoin())
      }
    }
  }
}
