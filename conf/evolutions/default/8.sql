-- noinspection SqlDialectInspectionForFile

# --- !Ups

ALTER TABLE "pubs"
ADD COLUMN "pub_status" varchar NOT NULL DEFAULT 'open';

# --- !Downs

ALTER TABLE "pubs"
DROP COLUMN "pub_status";
