TRUNCATE TABLE ARTICLE;
TRUNCATE TABLE MEMBER;
ALTER TABLE ARTICLE ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE MEMBER ALTER COLUMN ID RESTART WITH 1;
