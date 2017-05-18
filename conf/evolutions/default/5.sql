# --- !Ups

create table "users" (
  "id" bigserial not null primary key,
  "email" varchar not null,
  "password" varchar not null
);

create table "visits" (
  "pub_id" bigint not null,
  "user_id" bigint not null,
   CONSTRAINT "fk_pubs_visits" FOREIGN KEY ("pub_id") REFERENCES "pubs"("id"),
   CONSTRAINT "fk_users_visits" FOREIGN KEY ("user_id") REFERENCES "users"("id"),
   PRIMARY KEY ("pub_id", "user_id")
);


# --- !Downs

drop table "users" if exists;
drop table "visits" if exists;
