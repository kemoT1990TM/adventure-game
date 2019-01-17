## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE adventure;

#Create database services accounts
CREATE USER 'kemot'@'%' IDENTIFIED BY 'kemot';


#Database grants
GRANT SELECT ON adventure.* to 'kemot'@'%';
GRANT INSERT ON adventure.* to 'kemot'@'%';
GRANT DELETE ON adventure.* to 'kemot'@'%';
GRANT UPDATE ON adventure.* to 'kemot'@'%';
