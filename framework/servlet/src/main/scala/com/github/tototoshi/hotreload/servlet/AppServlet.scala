package com.github.tototoshi.hotreload.servlet

import java.net.URLClassLoader

import com.github.tototoshi.hotreload.buildlink.BuildLink
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}


class AppServlet extends HttpServlet {

  override def init(): Unit = {
  }

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    handleRequest(req, resp)
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    handleRequest(req, resp)
  }

  def handleRequest(req: HttpServletRequest, res: HttpServletResponse): Unit = {
    val buildEnv = BuildLink.getInstance
    val applicationClassPath = buildEnv.getApplicationClassPath
    val loader = new URLClassLoader(applicationClassPath, buildEnv.getFrameworkLoader)
    val appClass = loader.loadClass("com.github.tototoshi.hotreload.app.Application")
    val app = appClass.newInstance().asInstanceOf[Application]
    app.handleRequest(req, res)
  }

}



