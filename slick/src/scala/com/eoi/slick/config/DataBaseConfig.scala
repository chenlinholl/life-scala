package com.eoi.slick.config

/**
  * Created by tao.zeng on 2018/6/7.
  */
class DataBaseConfig extends BaseConfig[DataBaseConfig] {
  override val configs = getConfigs("dbConnection")

  def drives() = get[String]("drives")

  def user() = get[String]("user")

  def password() = get[String]("password")

  def maxConnections() = get[Int]("maxConnections")
}

object DataBaseConfig {
  def apply() = new DataBaseConfig()
}
