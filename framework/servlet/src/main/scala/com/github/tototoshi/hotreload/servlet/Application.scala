package com.github.tototoshi.hotreload.servlet

import jakarta.servlet.http.{HttpServletRequest, HttpServletResponse}

trait Application {

  def handleRequest(req: HttpServletRequest, res: HttpServletResponse): Unit

}
