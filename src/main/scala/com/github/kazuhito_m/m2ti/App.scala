package com.github.kazuhito_m.m2ti

/** The launched conscript entry point */
class App extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) = {
    Exit(App.run(config.arguments))
  }
}

object App {
  def run(args: Array[String]): Int = {

    val key = args(0)
    val token = args(1)
    val bordId = args(2)
    val listName = args(3)
    val jsonFilePath = args(4)

    // get the list of Tasks fron JSON
    val taskNames:List[String] = MindmeisterJsonAnalyzer.parse(jsonFilePath)

    if (taskNames.isEmpty) {
      return 0
    }

    // post data into a specific list of a specific Trello Board.
    val cardIds = TrelloImporter.postCardForTaskList(taskNames , bordId , listName , key , token)

    // a tiny validation...
    if (taskNames.size == cardIds.size) 0 else 1
  }

  /** Standard runnable class entrypoint */
  def main(args: Array[String]) {
    System.exit(run(args))
  }
}

case class Exit(val code: Int) extends xsbti.Exit
