package com.github.tototoshi.hotreload.servlet

object ApplicationProvider {

    private var application: Application = _

    private var handleReload: Application => Application = _

    def get(): Application = synchronized {
        application
    }

    def onReload(handleReload: Application => Application): Unit = synchronized {
        this.handleReload = handleReload
    }

    def reload(): Unit = synchronized {
        if (handleReload == null) {
            throw new RuntimeException("handler is not set")
        }
        application = handleReload(application)
    }

}

trait ApplicationProvider {

    def get(): Application

    def reload(): Application

}