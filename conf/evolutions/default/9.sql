-- noinspection SqlDialectInspectionForFile

# --- !Ups

UPDATE "pubs" SET "pub_status" = 'closed' WHERE "name" = 'Ali Cats';

# --- !Downs

UPDATE "pubs" SET "pub_status" = 'open' WHERE "name" = 'Ali Cats';
