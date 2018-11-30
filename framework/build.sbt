lazy val commonSettings = Seq(
  organization := "com.github.tototoshi"
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
      "javax.servlet" % "javax.servlet-api" % "3.1.0"
    )
  )
  .dependsOn(buildLink)

lazy val server = project
  .in(file("server"))
  .settings(commonSettings)
  .settings(
    name := "hot-reload-example-server",
    libraryDependencies ++= Seq(
      "javax.servlet" % "javax.servlet-api" % "3.1.0",
      "org.apache.tomcat.embed" % "tomcat-embed-jasper" % "9.0.8"
    )
  )

lazy val plugin = project
  .in(file("sbt-plugin"))
  .settings(commonSettings)
  .settings(
    name := "hot-reload-example-sbt-plugin",
    sbtPlugin := true
  )
  .dependsOn(buildLink, server)

lazy val root = project
  .in(file("."))
  .aggregate(buildLink, framework, plugin, server)
