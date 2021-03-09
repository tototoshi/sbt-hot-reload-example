lazy val app = project
  .in(file("."))
  .enablePlugins(HotReloadableTomcatPlugin)
  .settings(
    name := "sbt-hot-reload-example-app",
    scalaVersion := "2.13.5",
    libraryDependencies ++= Seq(
      "com.github.tototoshi" %% "hot-reload-example-servlet" % "0.1.0-SNAPSHOT"
    )
  )
