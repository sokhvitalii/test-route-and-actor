package com.test.comon

import java.net.URL

import com.test.model.RequestParser
import org.htmlcleaner.HtmlCleaner

import scala.concurrent.{ ExecutionContext, Future }

trait SiteParser {
  private val regexp = List("\n", " ", "\\.", "\\:", "\\;")
  
  def parseHTML(url: String)(implicit ec: ExecutionContext): Future[Array[String]] = Future {
    var stories: String = ""
    val rootNode = (new HtmlCleaner).clean(new URL(url))
    val elements = rootNode.getElementsByName("body", true)
    
    for (elem <- elements) {
      stories += regexp.foldLeft(elem.getText.toString.trim) { (a, i) =>
        a.replaceAll(i, ",")
      }
    }
    stories.split(",").filter(_.nonEmpty)
  }
  
  def getCount(request: RequestParser)(implicit ec: ExecutionContext): Future[Option[Int]] =
    parseHTML(request.url)
      .map(_.map(_.toLowerCase).groupBy(identity).mapValues(_.length))
      .map(_.get(request.word.toLowerCase()))
  
}
