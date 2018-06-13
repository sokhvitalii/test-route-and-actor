package com.test

import com.typesafe.config.{ Config, ConfigFactory }

object Conf {
  private val config = ConfigFactory.load()
  
  object RouteConf{
    private val conf: Config = config.getConfig("route")
    val interface: String = conf.getString("interface")
    val port: Int = conf.getInt("port")
  }
  
  object ActorConf{
    private val conf: Config = config.getConfig("actor")
    val systemName: String = conf.getString("systemName")
    val actorName: String = conf.getString("actorName")
  }
}
