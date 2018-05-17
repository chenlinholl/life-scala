package com.eoi.akka.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.eoi.akka.service.IndexService

class IndexRoute {

  val indexService: IndexService = new IndexService

  val route: Route = pathPrefix("api") {
    path("index") {
      get {
        complete("success")
      }
    }
  }
}