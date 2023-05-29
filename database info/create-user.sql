CREATE USER 'student'@'localhost' IDENTIFIED BY 'student';

GRANT ALL PRIVILEGES ON classicmodels.* TO 'student'@'localhost';
GRANT ALL PRIVILEGES ON demo.* TO 'student'@'localhost';
