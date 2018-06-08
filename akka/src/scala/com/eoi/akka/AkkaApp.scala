package com.eoi.akka

import java.util.Date

import akka.actor.{Actor, ActorSystem, Props, ReceiveTimeout}
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.{InternalServerError, NotFound}
import akka.http.scaladsl.server.Directives.{complete, extractUri}
import akka.http.scaladsl.server.ExceptionHandler
import akka.stream.ActorMaterializer
import com.eoi.akka.common.ImperativeRequestContext
import com.eoi.akka.routes.IndexRoute
import com.eoi.core.common.HttpNullException

import scala.concurrent.duration.Duration

object AkkaApp {

  /**
    * actor 模型
    */
  implicit val system: ActorSystem = ActorSystem()
  implicit val mat: ActorMaterializer = ActorMaterializer()

  implicit val log: LoggingAdapter = Logging(system, getClass)


  def main(args: Array[String]): Unit = {
    Http().bindAndHandle(new IndexRoute().route, "0.0.0.0", 8088, log = log)

    // 缺省的Actor构造函数
    val actor = system.actorOf(Props[HelloActor])
    actor.!("小花脸")
    actor.!("hello")
    actor.!(Message(200, "获取系统时间", new Date))

  }

  class HelloActor extends Actor {

    var ctx: ImperativeRequestContext = _


    override def receive: PartialFunction[Any, Unit] = {
      case "喂" => println("喂，您好！")
      case Message(code: Int, message: String, data: Any) => {
        log.info(s"code:${code},message:${message},data:${data}")
      }
      case Message => {
        log.info("hello message")
      }

      case content: String => {
        log.info(content)
      }

      case (content: String, ctx: ImperativeRequestContext) => {
        log.info(s"content:${content} ")
        ctx.complete(content)
        context.setReceiveTimeout(Duration.Inf)

      }

      case ReceiveTimeout => {
        ctx.complete("time out")
        context.stop(self)
      }

      case _ => println("您是?")
    }
  }

  case class Message(code: Int, message: String, data: Any)

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
