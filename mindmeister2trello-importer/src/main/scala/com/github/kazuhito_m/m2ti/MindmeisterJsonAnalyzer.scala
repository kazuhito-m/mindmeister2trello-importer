package com.github.kazuhito_m.m2ti

import org.json4s._
import org.json4s.native.JsonMethods

import scala.io.Source

/**
 * MidmeisterのマインドマップのエクスポートJsonの解析器。
 */
object MindmeisterJsonAnalyzer {

  /**
   * 引数に渡されたJsonファイルを解析し、
   * ツリーの末端を「トップからのノード名をハイフンでつなげたもの」へと
   * 文字列リスト変換する。

   * @param jsonFilePath 読み込み対象となるJsonファイルのパス。
   * @return ツリーの末端要素の文字列リスト。
   */
  def parse(jsonFilePath: String): List[String] = {

    // Jsonファイルをテキスト化
    val source = Source.fromFile(jsonFilePath)
    val jsonText = source.mkString
    source.close()

    // Jsonを読み込み
    val json = JsonMethods.parse(jsonText)
    val root = json \ "root"

    // Jsonを解析して「待った要素名の文字列リスト」へ変換
    val taskTexts = analyzeMindmap(root, "")

    // トップ要素(マインドマップの中心のボックス)部分は削る
    taskTexts.map(s => s.replaceFirst(".*?-", ""))

  }

  /**
   * json4s のJValueオブジェクトを一定法則で再帰的に探索し、
   * 末端要素の名前文字列のリストを返す。
   *
   * 名前文字列の書式は「経由した親ノードの名前をハイフン区切りでつなげたもの」。
   *
   * @param article 読み込み済みのjson4sのJValueオブジェクト。
   * @param prefix プレフィックス。親の名前をハイフンでつなげた文字列。
   * @return 名前文字列のリスト。
   */
  def analyzeMindmap(article: JValue, prefix: String): List[String] = {
    val childArticles = (article \ "children")
    val title = (article \ "title").values.toString
    if (childArticles.children.isEmpty) {
      List(prefix + title)
    } else {
      childArticles.children.flatMap((c) => analyzeMindmap(c, prefix + title + "-"))
    }
  }


}
