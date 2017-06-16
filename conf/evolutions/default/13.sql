-- noinspection SqlDialectInspectionForFile

# --- !Ups

UPDATE "pubs" SET "img_url" = '/assets/images/pubs/constant.jpg' WHERE "name" = 'The Constant Service';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/geese.jpg' WHERE "name" = 'The Geese';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/greys.jpg' WHERE "name" = 'The Greys';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/hanover.jpg' WHERE "name" = 'Hanover';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/village.jpg' WHERE "name" = 'Village';

# --- !Downs
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Constant Service';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Geese';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Greys';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Hanover';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Village';