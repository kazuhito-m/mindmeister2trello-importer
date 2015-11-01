package com.github.kazuhito_m.m2ti


import com.julienvey.trello.Trello
import com.julienvey.trello.domain.{Board, Card, TList}
import com.julienvey.trello.impl.TrelloImpl

import scala.collection.JavaConversions._

/**
 * webサービス「Trello」にむけてデータをインポートするヤーツ。
 */
object TrelloImporter {


  def postCardForTaskList(taskNames:List[String],boardId:String , taskListName:String , key : String , token : String): List[String] = {
    // まず認証
    val trelloApi: Trello = new TrelloImpl(key , token)
    // 次に「ボード」取得
    val board: Board = trelloApi.getBoard(boardId)
    // タスクリスト探して、名前が合致するものあればカード作って足す。
    board.fetchLists().filter(_.getName == taskListName).flatMap(addTaskCards(_ , taskNames)).toList
  }

  def addTaskCards(taskList: TList, taskNames: List[String]) : List[String] = {
    taskNames.map(tn => taskList.createCard(makeCard(tn)).getId)
  }

  def makeCard(taskName : String) : Card = {
    val card:Card = new Card()
    card.setName(taskName)
    card
  }

}
