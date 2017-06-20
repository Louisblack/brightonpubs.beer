-- noinspection SqlDialectInspectionForFile

# --- !Ups

UPDATE "pubs" SET "img_url" = replace("img_url", '/assets/images/', 'https://s3.eu-west-2.amazonaws.com/brighton-pubs-bucket/')

# --- !Downs
