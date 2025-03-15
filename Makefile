down:
	docker compose -f docker/docker-compose.yml down -v
up:
	docker compose -f docker/docker-compose.yml up -d

dup: down up

categories:
	curl --request GET -sL \
	     --url 'http://localhost:8080/catalog/categories'


doc:
	open http://localhost:8080/catalog/swagger-ui/index.html