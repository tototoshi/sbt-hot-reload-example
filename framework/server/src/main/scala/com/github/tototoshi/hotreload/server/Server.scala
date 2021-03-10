package com.github.tototoshi.hotreload.server

import jakarta.servlet.{ServletContainerInitializer, ServletContext}

import java.io.File
import java.util
import org.apache.catalina.core.StandardContext
import org.apache.catalina.startup.Tomcat

class Server {

  def start(
      classLoader: ClassLoader,
      webappDirLocation: File,
      port: Int
  ): Unit = {
    var tomcat = new Tomcat
    tomcat.setPort(port)

    tomcat.getConnector

    val ctx = tomcat.addWebapp("", webappDirLocation.getAbsolutePath).asInstanceOf[StandardContext]

    ctx.setParentClassLoader(classLoader)

    ctx.addServletContainerInitializer(new ServletContainerInitializer {
      override def onStartup(c: util.Set[Class[_]], ctx: ServletContext): Unit = {
        ctx.addServlet("App", "com.github.tototoshi.hotreload.servlet.AppServlet").addMapping("/*")
      }
    }, null)

    tomcat.start()

    println("Press Enter to stop")
    scala.io.StdIn.readLine()

    tomcat.stop()
    tomcat.destroy()
  }

}
