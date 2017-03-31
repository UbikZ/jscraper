.PHONY: usage

topdir = $(dir $(shell pwd))

usage:
	@echo "JScraper Control :"
	@echo "  * make exec"

nginx-restart:
	@echo "Restart nginx"
	/etc/init.d/nginx restart

pull:
	docker pull ubikz/jscraper

clean:
	@echo "Remove 'jscraper' containers"
	docker ps | grep jscraper | awk '{print $1}' | xargs docker rm -f
	@echo "Remove 'failed' images"
	docker images -aq -f "dangling=true" | xargs docker rmi

up:
	@echo "Up container"
	docker-compose -f prod.docker-compose.yml up -d

exec: clean pull up nginx-restart