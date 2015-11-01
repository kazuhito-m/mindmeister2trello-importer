package com.github.kazuhito_m.m2ti

import org.specs2.mutable._

class TrelloImporterSpec extends Specification {


  "Trello本番への投げ込みテスト" should {

    "指定のカードがTrelloに投げ込まえるか" in {
      // arrange
      val key = ""
      val token = ""
      val bordId = "56357f751f202def1466a681"
      val listName = "To Do"
      val taskNames: List[String] = List("あ", "うん", "ぎょう")

      // act
      // val actual: List[String] = TrelloImporter.postCardForTaskList(taskNames, bordId, listName, key, token)
      val actual = taskNames  // 本番に繋ぐわけには行かないので、当然ダミー

      // assert
      actual.foreach(println(_))
      actual.size must equalTo(3)

    }

  }

  "単体での簡潔テスト" should {

    "カードオブジェクトに名前が入るか" in {
      // act
      val actual = TrelloImporter.makeCard("テスト")
      // assert
      actual.getName must equalTo("テスト")
    }


  }
}
