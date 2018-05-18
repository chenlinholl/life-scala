package com.eoi.akka.service

import com.eoi.core.util.IdHelper
import com.eoi.core.common.{BaseService, StateCode}
import org.slf4j.LoggerFactory

import scala.concurrent.Future

class IndexService extends BaseService {

  private val log = LoggerFactory.getLogger(getClass)

  import scala.concurrent.ExecutionContext.Implicits.global

  def index(): Future[Map[String, Any]] = {
    log.debug("测试akka")
    Future(success(StateCode.CODE_200, " 测试数据 ! " + IdHelper.uuid()))
  }
}
