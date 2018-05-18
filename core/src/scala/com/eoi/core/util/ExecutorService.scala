package com.eoi.core.util

import java.util.concurrent._

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService}

object ExecutorService {

  implicit val system: ActorSystem = ActorSystem()
  implicit val mat: ActorMaterializer = ActorMaterializer()

  /**
    * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    */
  val appServiceThreadPool: ExecutorService = Executors.newFixedThreadPool(10)


  val appServiceExecutionContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(appServiceThreadPool)
}
