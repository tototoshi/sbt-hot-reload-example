.DEFAULT_GOAL := run
.PHONY: run build_framework watch clean

build_framework:
	cd framework && sbt clean publishLocal

watch:
	cd app && sbt '~compile'
run:
	cd app && sbt run

clean:
	cd framework && sbt clean
	cd app && sbt clean
