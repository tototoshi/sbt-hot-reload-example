# sbt-hot-reload-example

This project shows how to implement Hot Deploy by sbt like Play framework.

## Demo


Execute the following command to build the project and start the server.

```
$ cd framework
$ sbt publishLocal
$ cd ..
$ cd app
$ sbt run
```

At the same time, start another sbt and the compilation loop.

```
$ sbt '~compile'
```

Next, access http://localhost:8080 and confirm that "Hello, World!" is displayed. If you change the message by editing app/src/main/scala/com/github/tototoshi/hotreload/app/Application.scala, you can see the change is shown without restarting the server.
