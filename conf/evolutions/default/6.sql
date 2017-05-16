# --- !Ups

insert into "users" ("email", "password") values ('louis@example.com', '$2a$10$niF.amAexQMHaevqlkganeSjvMHfTq/OdISyj8/5BQy1FHvlbi3Ne');
insert into "visits" ("pub_id", "user_id") values (1, 1);

# --- !Downs

delete from "users" where "email" = 'louis@example.com';
delete from "visits" where "pub_id" = 1 and "user_id" = 1;
