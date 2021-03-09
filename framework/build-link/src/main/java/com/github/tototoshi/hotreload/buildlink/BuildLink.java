package com.github.tototoshi.hotreload.buildlink;

public class BuildLink {

    private static BuildLink instance;

    private BuildLink() {
    }

    public synchronized static BuildLink getInstance() {
        if (instance == null) {
            instance = new BuildLink();
        }
        return instance;
    }

}
