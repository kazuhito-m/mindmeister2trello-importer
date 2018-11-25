package com.github.kazuhito_m.m2ti

import org.json4s._
import org.json4s.native.JsonMethods

import scala.io.Source

/**
 * Analyzing a exported JSON from Midmeister's mind map
 */
object MindmeisterJsonAnalyzer {

  /**
   * - parse JSON from parameter 'jsonFilePath'
   * - generate a String List like:
   *      - need example
   *
   * @param jsonFilePath path to JSON file
   * @return a List of the buttom element of tree
   */
  def parse(jsonFilePath: String): List[String] = {
    // convert json file to a text data
    val source = Source.fromFile(jsonFilePath)
    val jsonText = source.mkString
    source.close()

    // read Json
    val json = JsonMethods.parse(jsonText)
    val root = json \ "root"

    // convert Json into a List of elements
    val taskTexts = analyzeMindmap(root, "")

    // remove the top element (a box of center of MindMap)
    taskTexts.map(s => s.replaceFirst(".*?-", ""))
  }

  /**
   * convert JValue objects of json4s into String List by seaching recursively.
   * the format of each string is:
   *   - name of parent nodes chained with '-'
   *
   * @param article JValue object of json4s already read.
   * @param prefix a parent nems chained with '-'
   * @return name list
   */
  def analyzeMindmap(article: JValue, prefix: String): List[String] = {
    val childArticles = article \ "children"
    val title = (article \ "title").values.toString
    if (childArticles.children.isEmpty)
      List(prefix + title)
    else
      childArticles.children.flatMap(analyzeMindmap(_, prefix + title + "-"))
  }


}
