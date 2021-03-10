lazy val app = project
  .in(file("."))
  .enablePlugins(HotReloadableTomcatPlugin)
  .settings(
    name := "sbt-hot-reload-example-app",
    scalaVersion := "2.12.13",
    libraryDependencies ++= Seq(
      "jakarta.servlet" % "jakarta.servlet-api" % "5.0.0" % "provided",
      "com.github.tototoshi" %% "hot-reload-example-servlet" % "0.1.0-SNAPSHOT"
    )
  )
