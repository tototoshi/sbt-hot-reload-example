package com.github.tototoshi.hotreload.servlet

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

trait Application {

  def handleRequest(req: HttpServletRequest, res: HttpServletResponse): Unit

}
