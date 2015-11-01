seq(conscriptSettings :_*)

organization := "com.github.kazuhito_m"


name := "mindmeister2trello-importer"

version := "0.1.0"

scalaVersion := "2.11.7"

publishTo := Some(Resolver.file("mindmeister2trello-importer",file("./target/repo"))(Patterns(true, Resolver.mavenStyleBasePattern)))

libraryDependencies ++= Seq(
  "com.julienvey.trello" % "trello-java-wrapper" % "0.3.2",
  "org.springframework" % "spring-web" % "4.2.2.RELEASE",
  "org.json4s" %% "json4s-native" % "3.3.0",
  "org.specs2" %% "specs2" % "2.3.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.1-M3" % "test"
)

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"
