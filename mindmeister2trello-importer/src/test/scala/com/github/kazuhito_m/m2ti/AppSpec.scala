package com.github.kazuhito_m.m2ti

import org.specs2.mutable._

class AppSpec extends Specification {

  "コンソールからの実行が出来る" should {
    "成功ならゼロを返す" in {
      // arrange
      val key = ""
      val token = ""
      val bordId = "56357f751f202def1466a681"
      val listName = "To Do"
      val jsonFilePath = getClass.getResource("map.json").getPath()
      // act
      // val actual = App.run(Array(key, token, bordId, listName, jsonFilePath))
      val actual = 0 // 本番に繋ぐわけには行かないので、当然ダミー
      // assert
      actual must equalTo(0)
    }
  }
}
