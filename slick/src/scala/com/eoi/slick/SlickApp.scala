package com.eoi.slick

import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
import com.eoi.core.common.HttpNullException
import com.eoi.slick.routes.{DeptRoute, UserInfoRoute}

object SlickApp {

  import com.eoi.core.util.ExecutorService.{mat, system}

  implicit val log: LoggingAdapter = Logging(system, getClass)


  def main(args: Array[String]): Unit = {
    // 启动服务器，设置启动端口
    Http().bindAndHandle(route(), "0.0.0.0", 10010, log = log)
  }

  /**
    * 路由集合
    *
    * @return
    */
  def route(): Route = {
    new UserInfoRoute().route ~ new DeptRoute().route
  }

  /**
    * 全局异常处理
    *
    * @return
    */
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
