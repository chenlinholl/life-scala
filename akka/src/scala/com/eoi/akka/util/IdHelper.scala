package com.eoi.slick.util

import java.util.UUID

object IdHelper {

  def id(): Long = {
    Snowflake.nextId()
  }

  def uuid(): String = {
    UUID.randomUUID().toString.replaceAll("-", "").toLowerCase
  }
}
