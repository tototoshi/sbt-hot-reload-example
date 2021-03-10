package com.github.tototoshi.hotreload.servlet

import jakarta.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}


class AppServlet extends HttpServlet {

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    handleRequest(req, resp)
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    handleRequest(req, resp)
  }

  def handleRequest(req: HttpServletRequest, res: HttpServletResponse): Unit = {
    ApplicationProvider.reload()
    val app = ApplicationProvider.get()
    app.handleRequest(req, res)
  }

}



