package com.test.model

case class RequestParser(word: String, url: String)

sealed trait Response
case class ResponseParser(word: String, count: Int) extends Response
case class ResponseParserError(msg: String) extends Response
case class ResponseParserNotFound(word: String, msg: String) extends Response
