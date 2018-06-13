package com.test

import akka.actor.ActorRef
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.test.actors.MainActor
import com.test.model._
import com.test.routes.MainRoutes
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpec }

class RoutesSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest
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

  "Parser Route" should {
    "be able to parse sites and success result" in {
      
      val userEntity = Marshal(requestData).to[MessageEntity].futureValue
      
      val request = Post("/parser").withEntity(userEntity)
      
      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
        
        contentType should ===(ContentTypes.`application/json`)
        
        assert(entityAs[ResponseParser] === ResponseParser("Vetal", 3))
      }
    }
    
    "be able to return erroe" in {
    
      val userEntity = Marshal(requestData.copy(url = "someUrl")).to[MessageEntity].futureValue
    
      val request = Post("/parser").withEntity(userEntity)
    
      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
      
        contentType should ===(ContentTypes.`application/json`)
      
        assert(entityAs[ResponseParserError] === ResponseParserError("no protocol: someUrl"))
      }
    }
    
    "be able to parse sites and not found" in {
    
      val userEntity = Marshal(requestData.copy(word = "Vetalaaaaaa")).to[MessageEntity].futureValue
    
      val request = Post("/parser").withEntity(userEntity)
    
      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
      
        contentType should ===(ContentTypes.`application/json`)
      
        assert(entityAs[ResponseParserNotFound] === ResponseParserNotFound("Vetalaaaaaa", "Not Found"))
      }
    }
  }

}
