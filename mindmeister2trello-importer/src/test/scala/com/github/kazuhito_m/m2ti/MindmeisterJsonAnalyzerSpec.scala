package com.github.kazuhito_m.m2ti

import org.specs2.mutable._

class MindmeisterJsonAnalyzerSpec extends Specification {

  "Jsonのパーズ" should {

    "中身の要素が期待通りの様相数である" in {
      // arrange
      // act
      val actual = MindmeisterJsonAnalyzer.parse(getClass.getResource("map.json").getPath())

      // assert
      actual.foreach { i => println(i) }
      actual.size must equalTo(9)
    }

    "要素の文字列中「マインドマップのトップの名前」が削られている" in {
      // arrange
      // act
      val actual = MindmeisterJsonAnalyzer.parse(getClass.getResource("map.json").getPath())

      // assert
      actual(0) must equalTo("洗濯")
    }

    "要素の文字列はツリー形式の親をハイフンつなぎで表現している" in {
      // arrange
      // act
      val actual = MindmeisterJsonAnalyzer.parse(getClass.getResource("map.json").getPath())

      // assert
      actual(2) must equalTo("買い物-買い込み-牛乳")
    }


  }
}
