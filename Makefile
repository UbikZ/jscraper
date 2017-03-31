.PHONY: usage

topdir = $(dir $(shell pwd))

usage:
	@echo "JScraper Control :"
	@echo "  * make exec"

nginx-restart:
    /etc/init.d/nginx restart

pull:
    docker pull ubikz/jscraper

clean:
    docker ps | grep jscraper | awk '{print $1}' | xargs docker rm -f
    docker images -aq -f "dangling=true" | xargs docker rmi

up:
    docker-compose -f prod.docker-compose.yml up -d

exec: clean pull up nginx-restart