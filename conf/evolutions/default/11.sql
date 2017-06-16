-- noinspection SqlDialectInspectionForFile

# --- !Ups

ALTER TABLE "pubs" ADD COLUMN "img_url" varchar;

# --- !Downs

ALTER TABLE "pubs" DROP COLUMN "img_url";
