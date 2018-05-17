package com.eoi.slick.util

import java.util.concurrent.{ExecutorService, Executors}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService}

object ExecutorService {

  /**
    * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
    */
  val appServiceThreadPool: ExecutorService = Executors.newFixedThreadPool(10)


  val appServiceExecutionContext: ExecutionContextExecutorService = ExecutionContext.fromExecutorService(appServiceThreadPool)
}
