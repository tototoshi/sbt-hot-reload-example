package com.github.tototoshi.hotreload.buildlink;

import java.net.URL;

public class BuildLink {

    private static BuildLink instance;

    private ClassLoader frameworkLoader;

    private URL[] applicationClassPath;

    private BuildLink() {
    }

    public synchronized static BuildLink getInstance() {
        if (instance == null) {
            instance = new BuildLink();
        }
        return instance;
    }

    public synchronized ClassLoader getFrameworkLoader() {
        return frameworkLoader;
    }

    public synchronized void setFrameworkLoader(ClassLoader frameworkLoader) {
        this.frameworkLoader = frameworkLoader;
    }

    public synchronized URL[] getApplicationClassPath() {
        return applicationClassPath;
    }

    public synchronized void setApplicationClassPath(URL[] applicationClassPath) {
        this.applicationClassPath = applicationClassPath;
    }

}
