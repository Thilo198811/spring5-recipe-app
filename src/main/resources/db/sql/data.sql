--liquibase formatted sql
--changeset thilo:2
INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Fast Food');
INSERT INTO category (description) VALUES ('Belgium');
INSERT INTO unit_of_measure (description) VALUES ('Teaspoon');
INSERT INTO unit_of_measure (description) VALUES ('Tablespoon');
INSERT INTO unit_of_measure (description) VALUES ('Cup');
INSERT INTO unit_of_measure (description) VALUES ('Pinch');
INSERT INTO unit_of_measure (description) VALUES ('Ounce');
INSERT INTO unit_of_measure (description) VALUES ('kg');