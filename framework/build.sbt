lazy val commonSettings = Seq(
  organization := "com.github.tototoshi",
  // https://github.com/scala/bug/issues/11832#issuecomment-578517653
  scalaVersion := "2.12.13"

)

lazy val buildLink = project
  .in(file("build-link"))
  .settings(commonSettings)
  .settings(
    name := "hot-reload-example-build-link"
  )

lazy val framework = project
  .in(file("servlet"))
  .settings(commonSettings)
  .settings(
    name := "hot-reload-example-servlet",
    libraryDependencies ++= Seq(
      "jakarta.servlet" % "jakarta.servlet-api" % "5.0.0" % "provided",
    )
  )
  .dependsOn(buildLink)

lazy val server = project
  .in(file("server"))
  .settings(commonSettings)
  .settings(
    name := "hot-reload-example-server",
    libraryDependencies ++= Seq(
      "javax.servlet" % "javax.servlet-api" % "4.0.1",
      "org.apache.tomcat.embed" % "tomcat-embed-jasper" % "10.0.6"
    )
  )

lazy val plugin = project
  .in(file("sbt-plugin"))
  .settings(commonSettings)
  .settings(
    name := "hot-reload-example-sbt-plugin",
    sbtPlugin := true
  )
  .dependsOn(buildLink, server, framework)

lazy val root = project
  .in(file("."))
  .aggregate(buildLink, framework, plugin, server)
