# --- !Ups

create table "pubs" (
  "id" bigserial not null primary key,
  "name" varchar not null
);

create table "locations" (
  "id" bigserial not null primary key,
  "pub_id" bigint  not null,
  "lat" numeric not null,
  "lng" numeric not null,
   CONSTRAINT "fk_pubs" FOREIGN KEY ("pub_id") REFERENCES "pubs"("id")
);


# --- !Downs

drop table "pubs" if exists;
drop table "locations" if exists;
