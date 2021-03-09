package com.github.tototoshi.hotreload.plugin

import java.net.{URL, URLClassLoader}

import com.github.tototoshi.hotreload.buildlink.BuildLink
import com.github.tototoshi.hotreload.server.Server
import com.github.tototoshi.hotreload.servlet.{Application, ApplicationProvider}
import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

object HotReloadableTomcatPlugin extends AutoPlugin {

  override def requires = JvmPlugin

  private def urls(files: Classpath): Array[URL] = files.map(_.data.toURI.toURL).toArray

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    run in Compile := Def.inputTask {
      val buildLink = BuildLink.getInstance()
      val buildLoader = this.getClass.getClassLoader

      val dependencyLoader = new URLClassLoader(
        urls(dependencyClasspath.in(Compile).value), buildLoader)

      val applicationClasspath = urls(exportedProducts.in(Compile).value)

      ApplicationProvider.onReload(_ => {
        val applicationLoader = new URLClassLoader(applicationClasspath, dependencyLoader)
        val appClass = applicationLoader.loadClass("com.github.tototoshi.hotreload.app.Application")
        appClass.newInstance().asInstanceOf[Application]
      })

      val server = dependencyLoader.loadClass("com.github.tototoshi.hotreload.server.Server")
        .newInstance()
        .asInstanceOf[Server]
      server.start(dependencyLoader, baseDirectory.value / "src" / "main" / "webapp", 8080)

    }.evaluated
  )

}

