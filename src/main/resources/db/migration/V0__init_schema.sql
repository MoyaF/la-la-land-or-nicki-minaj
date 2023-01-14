CREATE TABLE artist
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_artist PRIMARY KEY (id)
);

CREATE TABLE quote
(
    id               UUID NOT NULL,
    text             VARCHAR(255),
    artist_entity_id UUID,
    CONSTRAINT pk_quote PRIMARY KEY (id)
);

ALTER TABLE quote
    ADD CONSTRAINT FK_QUOTE_ON_ARTIST_ENTITY FOREIGN KEY (artist_entity_id) REFERENCES artist (id);