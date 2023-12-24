CREATE TABLE delivery
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    warehouse_id BIGINT,
    company_id   BIGINT,
    CONSTRAINT pk_delivery PRIMARY KEY (id)
);

ALTER TABLE delivery
    ADD CONSTRAINT FK_DELIVERY_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE delivery
    ADD CONSTRAINT FK_DELIVERY_ON_WAREHOUSE FOREIGN KEY (warehouse_id) REFERENCES warehouse (id);

CREATE TABLE position
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    delivery_id BIGINT,
    article_id  BIGINT,
    place_id    BIGINT,
    CONSTRAINT pk_position PRIMARY KEY (id)
);

ALTER TABLE position
    ADD CONSTRAINT FK_POSITION_ON_ARTICLE FOREIGN KEY (article_id) REFERENCES article (id);

ALTER TABLE position
    ADD CONSTRAINT FK_POSITION_ON_DELIVERY FOREIGN KEY (delivery_id) REFERENCES delivery (id);

ALTER TABLE position
    ADD CONSTRAINT FK_POSITION_ON_PLACE FOREIGN KEY (place_id) REFERENCES place (id);