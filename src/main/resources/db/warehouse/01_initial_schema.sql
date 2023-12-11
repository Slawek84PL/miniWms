create table warehouse(
    id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    short_name varchar(4) unique,
    name varchar(25),
    address_id BIGINT,
    CONSTRAINT fk_warehouse_address FOREIGN KEY (address_id) REFERENCES address(id)
);