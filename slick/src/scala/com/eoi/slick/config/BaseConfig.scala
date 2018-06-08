package com.eoi.slick.config

import java.io.File
import java.util.Map.Entry
import java.util.concurrent.ConcurrentHashMap

import com.typesafe.config.{Config, ConfigFactory, ConfigValue}

/**
  * Created by tao.zeng on 2018/6/7.
  */
trait BaseConfig[C <: BaseConfig[C]] {

  val configs: ConcurrentHashMap[String, Any]

  def getConfigs(key: String, fileName: String = "config.conf"): ConcurrentHashMap[String, Any] = {
    val hashMap = new ConcurrentHashMap[String, Any]()
    val config = loadFromResource(key, fileName)
    for (entry <- config.entrySet().toArray) {
      val obj = entry.asInstanceOf[Entry[String, ConfigValue]]
      hashMap.put(obj.getKey, obj.getValue.unwrapped())
    }
    hashMap
  }

  def get[V](key: String): Option[V] = {
    configs.get(key) match {
      case null => None
      case None => None
      case x => Some(x.asInstanceOf[V])
    }
  }

  private def loadFromResource(key: String, fileName: String): Config = {
    val file = new File(".", fileName)
    if (file.exists()) {
      loadConfig(file).getConfig(key)
    } else {
      ConfigFactory.load(fileName).getConfig(key)
    }
  }

  private def loadConfig(file: File): Config = {
    ConfigFactory.parseFile(file)
  }
}
