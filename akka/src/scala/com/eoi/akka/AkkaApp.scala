package com.eoi.akka

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import com.eoi.akka.routes.IndexRoute

object AkkaApp {

  implicit val system: ActorSystem = ActorSystem()

  implicit val log: LoggingAdapter = Logging(system, getClass)


  def main(args: Array[String]): Unit = {
    val route = new IndexRoute().route
  }
}
