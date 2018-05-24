package com.eoi.akka

import akka.actor.{Actor, ActorSystem, Props}
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
    // Http().bindAndHandle(new IndexRoute().route, "0.0.0.0", 8088, log = log)

    // 缺省的Actor构造函数
    val helloActor = system.actorOf(Props[HelloActor])
    helloActor.!("hello")
    helloActor ! "喂"
    helloActor.!(123)
  }

  class HelloActor extends Actor {
    override def receive: PartialFunction[Any, Unit] = {
      case "hello" => println("您好！")
      case "喂" => println("喂，您好！")
      case _ => println("您是?")
    }
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
