package com.github.kazuhito_m.m2ti

/**
  * import the data for Trello
  */
object TrelloImporter {


  def postCardForTaskList(taskNames: List[String], boardId: String, taskListName: String, key: String, token: String): List[String] = {
    // authentication
    val trelloApi: Trello = new TrelloImpl(key, token)
    // get the "board"
    val board: Board = trelloApi.getBoard(boardId)
    // search the task list. add a card if the name matched.
    val targetTaskLists = board.fetchLists()
      .filter(_.getName == taskListName)
    targetTaskLists
      .flatMap(addTaskCards(_, taskNames))
      .toList
  }

  def addTaskCards(taskList: TList, taskNames: List[String]): List[String] = {
    taskNames.map(makeCard(_))
      .map(taskList.createCard(_))
      .map(_.getId)
  }

  def makeCard(taskName: String): Card = {
    val card: Card = new Card()
    card.setName(taskName)
    card
  }

}
