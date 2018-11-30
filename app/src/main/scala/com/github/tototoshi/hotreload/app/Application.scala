package com.github.tototoshi.hotreload.app

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class Application extends com.github.tototoshi.hotreload.servlet.Application {

  override def handleRequest(req: HttpServletRequest, res: HttpServletResponse): Unit = {
    res.getWriter.println("Hello, World!")
  }

}
