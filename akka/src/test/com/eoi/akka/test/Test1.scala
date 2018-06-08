package com.eoi.akka.test

import akka.actor.FSM.Failure
import akka.actor.Status.Success
import org.junit.Test
import org.slf4j.LoggerFactory

import scala.concurrent.{Future, Promise}

class Test1 {


  private val log = LoggerFactory.getLogger(this.getClass)

  @Test
  def test1(): Unit = {
    type S = String

    val str: S = "hello world！"

    log.debug(s"$str")


    val res = 2.to(20).toList contains 3
    log.info(s"$res")


  }

  @Test
  def test2(): Unit = {

    import scala.concurrent.ExecutionContext.Implicits.global._
    val f: Future[String] = Future {
      "Hello World!"
    }

    val s: Future[Int] = Future {
      10
    }
    log.info(s"$f")
  }
}
