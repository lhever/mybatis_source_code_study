

DROP SEQUENCE IF EXISTS s_book;
CREATE SEQUENCE s_book
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807;

DROP TABLE IF EXISTS book;
-- 导出  表 ssm.book 结构
CREATE TABLE IF NOT EXISTS book (
  id int4 NOT NULL DEFAULT nextval('s_book' :: REGCLASS),
  name varchar(50),
  price numeric,
  create_time timestamp with time zone not null default now(),
  PRIMARY KEY (id)
) WITH (OIDS=FALSE);


INSERT INTO book (id, name, price, create_time) VALUES
	(1, '测试生成的书名11', 2.0, now()),
	(2, '测试生成的书名22', 233, now()),
	(3, '测试生成的书名33', 20, now());


------------------------------------------
------------------------------------------


DROP SEQUENCE IF EXISTS s_student;
CREATE SEQUENCE s_student
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807;

DROP TABLE IF EXISTS student;
-- 导出  表 ssm.book 结构
CREATE TABLE IF NOT EXISTS student (
  id int4 NOT NULL DEFAULT nextval('s_student' :: REGCLASS),
  name varchar(50),
  age int4,
  address VARCHAR(100),
  create_time timestamp with time zone not null default now(),
  PRIMARY KEY (id)
) WITH (OIDS=FALSE);


INSERT INTO student (id, name, age, address, create_time) VALUES
	(1, '力宏', 16, '台湾', now()),
	(2, '王天琦', 12, '云南', now());


------------------------------------------
------------------------------------------

DROP SEQUENCE IF EXISTS s_card;
CREATE SEQUENCE s_card
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807;

DROP TABLE IF EXISTS card;
-- 导出  表 ssm.book 结构
CREATE TABLE IF NOT EXISTS card (
  id int4 NOT NULL DEFAULT nextval('s_card' :: REGCLASS),
  code varchar(50),
  money numeric,
  student_id int4,
  create_time timestamp with time zone not null default now(),
  PRIMARY KEY (id)
) WITH (OIDS=FALSE);


INSERT INTO card (id, code, money, student_id, create_time) VALUES
	(1, '00001345', 2.0, 1, now()),
	(2, '00001346', 233, 2, now()),
	(3, '00002222', 100, 3, now());

------------------------------------------
------------------------------------------


DROP SEQUENCE IF EXISTS s_score;
CREATE SEQUENCE s_score
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 9223372036854775807;

DROP TABLE IF EXISTS score;
-- 导出  表 ssm.book 结构
CREATE TABLE IF NOT EXISTS score (
  id int4 NOT NULL DEFAULT nextval('s_score' :: REGCLASS),
  name varchar(50),
  score numeric,
  student_id int4,
  create_time timestamp with time zone not null default now(),
  PRIMARY KEY (id)
) WITH (OIDS=FALSE);


INSERT INTO score (id, name, score, student_id, create_time) VALUES
	(1, '语文', 2.0, 1, now()),
	(2, '数学', 2.0, 1, now()),
	(3, '外语', 2.0, 1, now()),
	(4, '语文', 233, 2, now()),
	(5, '数学', 20, 2, now());



