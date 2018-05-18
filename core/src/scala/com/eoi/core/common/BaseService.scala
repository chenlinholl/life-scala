package com.eoi.core.common

trait BaseService {

  def success(code: String, message: String, data: Any) =
    Map("code" -> code, "message" -> message, "data" -> data)

  def success(state: StateCode, data: Any): Map[String, Any] =
    success(state.code, state.message, data)

  def failure(code: String, message: String) =
    Map("code" -> code, "message" -> message)
}

class BaseHttpException(message: String = "", cause: Throwable = null)
  extends RuntimeException(message, cause)

case class HttpNullException(message: String = "", cause: Throwable = null)
  extends BaseHttpException(message, cause)