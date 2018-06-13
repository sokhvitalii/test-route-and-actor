package com.test

import akka.actor.ActorRef
import akka.pattern.ask
import scala.concurrent.duration._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.test.actors.MainActor
import com.test.model._
import com.test.routes.MainRoutes
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpec }

class ActorSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest
  with MainRoutes with MockitoSugar with BeforeAndAfterAll{
  
  override val mainActor: ActorRef = system.actorOf(MainActor.props, Conf.ActorConf.actorName)
  
  var serverForTesting: ServerForTesting = _
  
  val requestData = RequestParser("Vetal", "http://localhost:7000/hello")

  override def beforeAll() {
    println("Before!")
    serverForTesting = new ServerForTesting
  }

  override def afterAll() {
    println("After!")
    serverForTesting.stopServer()
  }

  "Parser Actor" should {
    "be able to parse sites and success result" in {
      val futureResponse = (mainActor ? requestData).mapTo[Response]
      
      whenReady(futureResponse, timeout(2 seconds), interval(500 millis)){ result =>
        result shouldBe ResponseParser("Vetal", 3)
      }
    }
    
    "be able to return erroe" in {
      val futureResponse = (mainActor ? requestData.copy(url = "someUrl")).mapTo[Response]
  
      whenReady(futureResponse, timeout(2 seconds), interval(500 millis)){ result =>
        result shouldBe ResponseParserError("no protocol: someUrl")
      }
    }

    "be able to parse sites and not found" in {
  
      val futureResponse = (mainActor ? requestData.copy(word = "Vetalaaaaaa")).mapTo[Response]
  
      whenReady(futureResponse, timeout(2 seconds), interval(500 millis)){ result =>
        result shouldBe ResponseParserNotFound("Vetalaaaaaa", "Not Found")
      }
    }
  }

}
