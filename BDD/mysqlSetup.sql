CREATE DATABASE mbone;
GRANT ALL PRIVILEGES ON *.* TO spring@localhost;
CREATE USER 'spring'@'localhost' IDENTIFIED BY 'angularcestbonnard';
GRANT ALL PRIVILEGES ON mbone.* TO 'spring'@'localhost';
CREATE USER 'spring'@'%' IDENTIFIED BY 'angularcestbonnard';
GRANT ALL PRIVILEGES ON mbone.* TO 'spring'@'%';