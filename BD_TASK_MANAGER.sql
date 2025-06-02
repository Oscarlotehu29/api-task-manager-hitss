drop database if exists project_tasks;
create database project_tasks;
use project_tasks;

create table people(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25),
    father_last_name VARCHAR(25),
    mother_last_name VARCHAR(25),
    birth_date DATE,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active INT DEFAULT 1
);

create table users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(15) UNIQUE,
    email VARCHAR(255) UNIQUE,
    person_id INT,
    passwd VARCHAR(510),
    id_user_created int,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active INT DEFAULT 1,
    FOREIGN KEY(person_id) REFERENCES people(id)
);

create table categories(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    user_id INT,
    is_active INT DEFAULT 1,
    FOREIGN KEY(user_id) REFERENCES users(id) on update CASCADE on delete CASCADE
);


create table tasks(
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(510),
    category_id INT,
    user_id INT,
    is_active INT DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users(id) on update CASCADE on delete CASCADE,
    FOREIGN KEY(category_id) REFERENCES categories(id) on update CASCADE on delete CASCADE
);

drop user if exists 'hitss_user'@'%';
create user 'hitss_user'@'%' identified by 'holaMundo';

GRANT SELECT, INSERT, UPDATE, DELETE ON project_tasks.* TO 'hitss_user';