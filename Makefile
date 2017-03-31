.PHONY: usage

imageId = ubikz/jscraper
containerIds = $(shell docker ps | grep jscraper | awk '{print $$1}')
delImageIds = $(shell docker images -aq -f "dangling=true")

usage:
	@echo "JScraper Control :"
	@echo "  * make exec"

nginx-restart:
	/etc/init.d/nginx restart

pull:
	docker pull $(imageId)

clean:
	for id in $(containerIds); do docker rm -f $$id; done
	for id in $(delImageIds); do docker rmi $$id; done

up:
	@echo "Up container"
	docker-compose -f prod.docker-compose.yml up -d

exec: clean pull up nginx-restart