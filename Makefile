.PHONY: usage

rootDir = $(shell pwd)
volDir = $(rootDir)/docker
imageId = ubikz/jscraper
containerIds = $(shell docker ps | grep jscraper | awk '{print $$1}')
delImageIds = $(shell docker images -aq -f "dangling=true")

usage:
	@echo "JScraper Control :"
	@echo "  * make dev"
	@echo "  * make prod"

nginx-stop:
	@/etc/init.d/nginx stop

nginx-start:
	@/etc/init.d/nginx start

pull:
	@docker pull $(imageId)

clean-docker: clean-ui
	@for id in $(containerIds); do docker rm -f $$id; done
	@for id in $(delImageIds); do docker rmi -f $$id; done

clean-db:
	@rm -rf $(volDir)/pgsql

clean-ui:
	@rm -rf $(volDir)/scraper-ui

clean-cache:
	@rm -rf $(volDir)/gradle

clean-logs:
	@rm -rf $(volDir)/log

up-prod:
	@docker-compose -f prod.docker-compose.yml up -d

up-dev:
	@docker-compose -f docker-compose.yml up --build

prod: nginx-stop clean-docker pull up-prod nginx-start

dev: clean-docker up-dev

dev-new: clean-db clean-logs dev