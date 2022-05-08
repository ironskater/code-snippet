DROP TABLE IF EXISTS book;

CREATE TABLE book (
	book_id int NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar(255) NULL,
	author varchar(255) NULL,
	CONSTRAINT book_pk PRIMARY key(book_id)
);
