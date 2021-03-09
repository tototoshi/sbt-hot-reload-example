.DEFAULT_GOAL := run
.PHONY: run build_all build_app build_framework watch clean

build_framework:
	cd framework && sbt publishLocal
build_app:
	cd app && sbt test:compile

build_all: clean build_framework build_app
	
watch:
	cd app && sbt '~compile'
run:
	cd app && sbt run

clean:
	cd framework && sbt clean
	cd app && sbt clean
