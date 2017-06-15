-- noinspection SqlDialectInspectionForFile

# --- !Ups

INSERT INTO "pubs" ("name") VALUES ('Brewdog');
INSERT INTO "locations" ("pub_id", "lat", "lng") SELECT "id", 50.8245164, -0.1376913 from "pubs" where "name" = 'Brewdog';

INSERT INTO "pubs" ("name") VALUES ('Brighton Bierhaus');
INSERT INTO "locations" ("pub_id", "lat", "lng") SELECT "id", 50.822454, -0.1364597 from "pubs" where "name" = 'Brighton Bierhaus';

INSERT INTO "pubs" ("name") VALUES ('Fiveways');
INSERT INTO "locations" ("pub_id", "lat", "lng") SELECT "id", 50.8434465,-0.1365969 from "pubs" where "name" = 'Fiveways';

# --- !Downs

delete from "pubs" where "name" = 'Brewdog';
delete from "pubs" where "name" = 'Brighton Bierhaus';
delete from "pubs" where "name" = 'Fiveways';
