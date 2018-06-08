package com.eoi.akka.routes

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.eoi.akka.AkkaApp.HelloActor
import com.eoi.akka.service.IndexService
import com.eoi.core.util.JsonParse._
import com.eoi.akka.common.ImperativeRequestContext._

class IndexRoute(implicit system: ActorSystem, mat: ActorMaterializer) {

  val indexService: IndexService = new IndexService

  val route: Route = pathPrefix("api") {
    path("index") {
      get {
        complete(indexService.index())
      } ~ post {
        entity(as[String]) {
          req =>
            imperativelyComplete {
              ctx =>
                val handler = system.actorOf(Props(classOf[HelloActor]))
                handler ! (req, ctx)
            }
        }
      }
    }
  }
}
