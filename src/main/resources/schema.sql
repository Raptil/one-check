DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.user_dictionary (
  id          SERIAL PRIMARY KEY,
  user_name   VARCHAR NOT NULL,
  first_name  VARCHAR NOT NULL
  );
CREATE TABLE IF NOT EXISTS public.company (
  id    SERIAL PRIMARY KEY,
  company_name  VARCHAR NOT NULL
 );
 CREATE TABLE IF NOT EXISTS public.product_item (
    id SERIAL PRIMARY KEY,
    id_company INT NOT NULL,
    product_name VARCHAR NOT NULL,
    category VARCHAR NOT NULL,
    price REAL NOT NULL,
    description VARCHAR,
    img_url VARCHAR,
    CONSTRAINT "FK_id_company" FOREIGN KEY ("id_company")
        REFERENCES "company" ("id")
 );
  CREATE TABLE IF NOT EXISTS public.basket (
  id SERIAL PRIMARY KEY,
  id_user INT NOT NULL,
  active_flg VARCHAR NOT NULL,
  total_price INT NOT NULL,
   CONSTRAINT "FK_id_user" FOREIGN KEY ("id_user")
      REFERENCES "user_dictionary" ("id")
 );
CREATE TABLE IF NOT EXISTS public.check_products (
 id SERIAL PRIMARY KEY,
 id_basket int,
 id_user int not null,
 total_price int NOT NULL,
 description VARCHAR NOT NULL,
 CONSTRAINT "FK_id_basket" FOREIGN KEY ("id_basket")
    REFERENCES "basket" ("id"),
  CONSTRAINT "FK_id_user" FOREIGN KEY ("id_user")
     REFERENCES "user_dictionary" ("id")
);
CREATE TABLE IF NOT EXISTS public.check_to_product (
 id_check int not null,
 id_product int not null,
 CONSTRAINT "FK_id_check" FOREIGN KEY ("id_check")
    REFERENCES "check_products" ("id"),
 CONSTRAINT "FK_id_product" FOREIGN KEY ("id_product")
    REFERENCES "product_item" ("id")
 );