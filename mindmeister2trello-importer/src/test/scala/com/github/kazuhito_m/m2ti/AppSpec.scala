package com.github.kazuhito_m.m2ti

import org.specs2.mutable._

class AppSpec  extends Specification {

  "コンソールからの実行が出来る" should {
    "ともかくゼロを返してくる" in {
      // arrange
      // act
      val actual = App.run(Array("テスト"))
      // assert
      actual must equalTo(0)
    }
  }
}
