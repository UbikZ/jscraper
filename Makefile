.PHONY: usage

imageId = ubikz/jscraper
containerIds = $(shell docker ps | grep jscraper | awk '{print $1}')
delImageIds = $(shell docker images -aq -f "dangling=true")

usage:
	@echo "JScraper Control :"
	@echo "  * make exec"

nginx-restart:
	@echo "Restart nginx"
	/etc/init.d/nginx restart

pull:
	docker pull $(imageId)

clean:
	@echo "Remove 'jscraper' containers"
	for id in $(containerIds); do $(shell docker rm -f $(container)); done
	@echo "Remove 'failed' images"
	$(foreach id,$(delImageIds),$(shell docker rmi $(image));)

up:
	@echo "Up container"
	docker-compose -f prod.docker-compose.yml up -d

exec: clean pull up nginx-restart