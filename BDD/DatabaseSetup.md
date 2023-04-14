## Installation base de donnée

```shell
$ docker run -e "MYSQL_ROOT_PASSWORD=singlepageapplication" --name=mysql4 -p 3308:3306 --restart on-failure -td mysql:latest
$ mysql -P 3308 -uroot -psinglepageapplication -uroot

```