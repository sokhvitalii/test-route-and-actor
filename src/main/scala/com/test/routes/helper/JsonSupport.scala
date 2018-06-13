package com.test.routes.helper

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.test.model._
import spray.json._

trait JsonSupport extends SprayJsonSupport {
  import DefaultJsonProtocol._
  
  implicit val responseParserJsonFormat = jsonFormat2(ResponseParser)
  implicit val requestParserJsonFormat = jsonFormat2(RequestParser)
  implicit val responseParserErrorJsonFormat = jsonFormat1(ResponseParserError)
  implicit val responseParserNotFoundJsonFormat = jsonFormat2(ResponseParserNotFound)
  
  implicit object AnimalJsonFormat extends RootJsonFormat[Response] {
    def write(a: Response) = a match {
      case p: ResponseParser => p.toJson
      case p: ResponseParserError => p.toJson
      case p: ResponseParserNotFound => p.toJson
    }
    def read(json: JsValue): Response =
      json.asJsObject.getFields("type") match {
        case Seq(JsString("response")) => json.convertTo[ResponseParser]
        
      }
  }
  
}
