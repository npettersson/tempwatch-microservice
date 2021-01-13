-- create the temp_watch database
CREATE DATABASE IF NOT EXISTS temp_watch;

-- create the user for the temp_watch database
CREATE USER 'temp_watch_user'@'%' IDENTIFIED BY 'somepassword';
GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `temp_watch`.* TO 'temp_watch_user'@'%';

FLUSH PRIVILEGES;
