package com.eoi.akka.routes

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.eoi.akka.service.IndexService
import com.eoi.core.util.JsonParse._

class IndexRoute (implicit system: ActorSystem, mat: ActorMaterializer) {

  val indexService: IndexService = new IndexService

  val route: Route = pathPrefix("api") {
    path("index") {
      get {
        complete(indexService.index())
      }
    }
  }
}
