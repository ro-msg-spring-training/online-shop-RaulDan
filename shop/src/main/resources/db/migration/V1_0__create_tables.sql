CREATE TABLE IF NOT EXISTS product_category (
                                                id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                name NVARCHAR(100) NOT NULL,
                                                description NVARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS supplier (
                                        id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        name NVARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS customer (
                                        id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        first_name NVARCHAR(100) NOT NULL,
                                        last_name NVARCHAR(100) NOT NULL,
                                        username NVARCHAR(100) NOT NULL,
                                        password NVARCHAR(100) NOT NULL,
                                        email NVARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS product (
                                       id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       name NVARCHAR(100) NOT NULL,
                                       description NVARCHAR(255) NOT NULL,
                                       price NUMERIC NOT NULL,
                                       weight DOUBLE PRECISION NOT NULL,
                                       category_id INTEGER NOT NULL REFERENCES product_category(id),
                                       supplier_id INTEGER NOT NULL REFERENCES supplier(id),
                                       image_url NVARCHAR(255) NOT NULL
);

create table if not exists location(
                                       id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       name varchar(255),
                                       address_country varchar(255),
                                       address_city varchar(255),
                                       address_county varchar(255),
                                       address_street_address varchar(255)
);

create table if not exists stock(
                                    product_id integer not null references product(id),
                                    location_id integer not null references location(id),
                                    quantity integer not null ,
                                    constraint PK_Stock PRIMARY KEY (product_id,location_id)
);

create table if not exists product_order
(
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shipped_from_id integer not null references location(id),
    customer_id integer not null references customer(id),
    created_at date not null,
    address_country varchar(255),
    address_city varchar(255),
    address_county varchar(255),
    address_street_address varchar(255)
);

create table if not exists order_detail
(
    product_order int not null references product_order(id),
    product integer not null references product(id),
    quantity integer not null,
    constraint pk_order_details primary key (product_order,product)

);

create table if not exists revenue
(
    id integer not null  primary key auto_increment,
    location integer not null references location(id),
    revenue_date date,
    sum DOUBLE PRECISION
);


