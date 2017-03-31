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
	containers = $(docker ps | grep jscraper | awk '{print $1}')
	$(foreach container,$(containers),docker rm -f $(container);)
	@echo "Remove 'failed' images"
	images = $(docker images -aq -f "dangling=true")
	$(foreach image,$(images),docker rmi $(image);)

up:
	@echo "Up container"
	docker-compose -f prod.docker-compose.yml up -d

exec: clean pull up nginx-restart