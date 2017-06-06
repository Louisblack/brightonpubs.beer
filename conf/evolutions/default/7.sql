-- noinspection SqlDialectInspectionForFile

# --- !Ups

UPDATE "pubs" SET "name" = 'Village' WHERE "name" = 'Horse & Groom';

# --- !Downs

UPDATE "pubs" SET "name" = 'Horse & Groom' WHERE "name" = 'Village'
