package com.test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.Directives.{ complete, get, path }
import akka.stream.ActorMaterializer

class ServerForTesting {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  
  val route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>hello vetal to akka-http</h1>" +
          "<h1>Say vetal hello to akka-http</h1><h1>to akka-http vetal</h1>"))
      }
    }
  
  val bindingFuture = Http().bindAndHandle(route, "localhost", 7000)
 
  def stopServer(): Unit ={
    system.terminate()
  }
}
