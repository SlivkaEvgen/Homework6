USE homework;

INSERT INTO companies(id, name, city)
VALUES (1, 'GoIT', 'Kiyv'),
       (2, 'StarsIT', 'Kyiv'),
       (3, 'UpIT', 'Lvov'),
       (4, 'Apple', 'LA'),
       (5, 'IT-COM', 'NY');

INSERT INTO customers(id, name, city, budget)
VALUES (1, 'UKR-BANK', 'Kyiv', 100000),
       (2, 'RICH-MOVICH', 'ZP-City', 200000),
       (3, 'American Express', 'New York', 3000000),
       (4, 'Uncle Gora', 'Lvov', 400000);

INSERT INTO developers(id, name, age, gender, email, salary, company_id)
VALUES (1, 'Ivan', 35, 'Male', 'abc@com.ua', 1000, 3),
       (2, 'Nikolia', 25, 'Male', 'abc1@com.ua', 2000, 2),
       (3, 'Kosta', 26, 'Male', 'abc2@com.ua', 3000, 1),
       (4, 'Vita', 27, 'Male', 'abc3@com.ua', 1000, 1),
       (5, 'Ura', 28, 'Male', 'abc4@com.ua', 2000, 1),
       (6, 'Ira', 29, 'Female', 'abc5@com.ua', 3000, 2),
       (7, 'Dmitri', 30, 'Male', 'abc6@com.ua', 3000, 3),
       (8, 'Kate', 32, 'Female', 'abc7@com.ua', 2000, 2),
       (9, 'Antony', 33, 'Male', 'abc8@com.ua', 1000, 3),
       (10, 'Luiza', 36, 'Female', 'abc9@com.ua', 3000, 3),
       (11, 'Brittany', 40, 'Female', 'abc10@com.ua', 2000, 2),
       (12, 'Micha', 42, 'Male', 'abc11@com.ua', 1000, 2);

INSERT INTO skills(id, activity, level)
VALUES (1, 'Java', 'Junior'),
       (2, 'Java', 'Middle'),
       (3, 'Java', 'Senior'),
       (4, 'JS', 'Junior'),
       (5, 'JS', 'Middle'),
       (6, 'JS', 'Senior'),
       (7, 'C+', 'Junior'),
       (8, 'C+', 'Middle'),
       (9, 'C+', 'Senior'),
       (10, 'C#', 'Junior'),
       (11, 'C#', 'Middle'),
       (12, 'C#', 'Senior');

INSERT INTO developers_skills(developer_id, skill_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10),
       (11, 11),
       (12, 12),
       (1, 5),
       (2, 8),
       (3, 12),
       (4, 1);

INSERT INTO projects(id, name, cost, company_id, customer_id)
VALUES (1, 'Bot', 300000, 1, 1),
       (2, 'Car', 350000, 2, 2),
       (3, 'School', 400000, 3, 3);

INSERT INTO developers_projects(developer_id, project_id)
VALUES (1, 3),
       (2, 2),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 2),
       (7, 3),
       (8, 2),
       (9, 3),
       (10, 3),
       (11, 2),
       (12, 2);
#        (12, 4);
