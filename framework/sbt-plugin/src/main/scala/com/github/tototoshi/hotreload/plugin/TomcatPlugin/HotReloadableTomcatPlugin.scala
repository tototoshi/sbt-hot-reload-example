package com.github.tototoshi.hotreload.plugin.TomcatPlugin

import java.net.{URL, URLClassLoader}

import com.github.tototoshi.hotreload.buildlink.BuildLink
import com.github.tototoshi.hotreload.server.Server
import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

object HotReloadableTomcatPlugin extends AutoPlugin {

  override def requires = JvmPlugin

  private def urls(files: Classpath): Array[URL] = files.map(_.data.toURI.toURL).toArray

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    run in Compile := Def.inputTask {
      val buildEnvironment = BuildLink.getInstance()

      val buildLoader = this.getClass.getClassLoader

      val dependencyLoader = new URLClassLoader(
        urls(dependencyClasspath.in(Compile).value), buildLoader)

      buildEnvironment.setFrameworkLoader(dependencyLoader)
      buildEnvironment.setApplicationClassPath(urls(exportedProducts.in(Compile).value))

      val server = dependencyLoader.loadClass("com.github.tototoshi.hotreload.server.Server")
        .newInstance()
        .asInstanceOf[Server]
      server.start(dependencyLoader, baseDirectory.value / "src" / "main" / "webapp", 8080)
    }.evaluated
  )

}
