package com.eoi.akka

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.{InternalServerError, NotFound}
import akka.http.scaladsl.server.Directives.{complete, extractUri}
import akka.http.scaladsl.server.ExceptionHandler
import akka.stream.ActorMaterializer
import com.eoi.akka.routes.IndexRoute
import com.eoi.core.common.HttpNullException

object AkkaApp {

  /**
    * actor 模型
    */
  implicit val system: ActorSystem = ActorSystem()
  implicit val mat: ActorMaterializer = ActorMaterializer()

  implicit val log: LoggingAdapter = Logging(system, getClass)


  def main(args: Array[String]): Unit = {
    Http().bindAndHandle(new IndexRoute().route, "0.0.0.0", 8088, log = log)
  }

  implicit def globalExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case ex: ArithmeticException =>
        extractUri { uri =>
          log.error(s"uri:[$uri] ,error msg:${ex.getMessage}", ex)
          complete(HttpResponse(InternalServerError, entity = ex.getMessage))
        }
      case ex: HttpNullException =>
        complete(HttpResponse(NotFound, entity = ex.getMessage))
      case ex: Exception =>
        log.error(s"error msg:${ex.getMessage}", ex)
        complete(HttpResponse(InternalServerError, entity = ex.getMessage))
    }
}
