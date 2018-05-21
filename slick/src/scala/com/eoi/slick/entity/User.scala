package com.eoi.slick.entity

import java.util.Date

import com.eoi.core.util.IdHelper

/**
  * 辅助构造函数名必须是this，而且必须调用主构造函数实现参数的传递
  * 通过参数名称调用，可以不考虑顺序
  *
  * val user = User(id = IdHelper.id(), birthday = new Date(), name = "1123", address = "123")
  *
  * @param id
  * @param name
  * @param address
  * @param birthday
  */
case class User(id: Long, name: String, address: String, birthday: Date) {

  def this(name: String, address: String) = {
    this(IdHelper.id(), name, address, new Date)
  }

  def this(name: String, address: String, birthday: Date) = {
    this(IdHelper.id(), name, address, birthday)
  }

  def this(id: Long) = {
    this(id, null, null, new Date)
  }
}
