DROP DATABASE IF EXISTS homework;
CREATE DATABASE IF NOT EXISTS homework;
USE homework;
CREATE TABLE IF NOT EXISTS companies
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40),
    city VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS customers
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(40),
    city   VARCHAR(40),
    budget DECIMAL
);

CREATE TABLE IF NOT EXISTS developers
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(30),
    age        INT,
    gender     ENUM ('Male','Female'),
    email      VARCHAR(30),
    salary     BIGINT(10),
    company_id INT,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS skills
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    activity ENUM ('Java','C#','C+','JS')       DEFAULT 'Java',
    level    ENUM ('Junior', 'Middle', 'Senior')  DEFAULT 'Junior'
);

CREATE TABLE IF NOT EXISTS projects
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(40),
    cost        DECIMAL,
    first_date  VARCHAR(50) DEFAULT 'SunOct10/18:29:38/EEST2021',
--   first_date     DATETIME DEFAULT CURRENT_TIMESTAMP,
    company_id  INT,
    customer_id INT,
    FOREIGN KEY (company_id) REFERENCES companies (id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS developers_projects
(
    developer_id int NOT NULL,
    project_id   int NOT NULL,
    PRIMARY KEY (developer_id, project_id),
    FOREIGN KEY (developer_id) REFERENCES developers (id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS developers_skills
(
    developer_id int NOT NULL,
    skill_id     int NOT NULL,
    PRIMARY KEY (developer_id, skill_id),
    FOREIGN KEY (developer_id) REFERENCES developers (id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES skills (id) ON DELETE CASCADE
);



