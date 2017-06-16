-- noinspection SqlDialectInspectionForFile

# --- !Ups

UPDATE "pubs" SET "img_url" = '/assets/images/pubs/dover_castle.jpg' WHERE "name" = 'Dover Castle';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/charles_napier.jpg' WHERE "name" = 'Sir Charles Napier';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/setting_sun.jpg' WHERE "name" = 'The Setting Sun';

# --- !Downs

UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Dover Castle';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Sir Charles Napier';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Setting Sun';
