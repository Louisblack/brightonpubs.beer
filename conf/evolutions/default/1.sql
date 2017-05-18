# --- !Ups

create table "people" (
  "id" bigserial not null primary key,
  "name" varchar not null,
  "age" int not null
);

# --- !Downs

drop table "people" if exists;
