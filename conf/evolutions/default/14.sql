-- noinspection SqlDialectInspectionForFile

# --- !Ups

UPDATE "pubs" SET "img_url" = '/assets/images/pubs/barley_mow.jpg' WHERE "name" = 'Barley Mow';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/black_dove.jpg' WHERE "name" = 'The Black Dove';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/brewdog.jpg' WHERE "name" = 'Brewdog';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/bulldog.jpg' WHERE "name" = 'Bulldog Tavern';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/cobden.jpg' WHERE "name" = 'Cobden Arms';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/hand_in_hand.jpg' WHERE "name" = 'Hand In Hand';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/hartington.jpg' WHERE "name" = 'The Hartington';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/kings_arms.jpg' WHERE "name" = 'Kings Arms Hotel';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/marine_tavern.jpg' WHERE "name" = 'Marine Tavern';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/marlborough.jpg' WHERE "name" = 'Marlborough Hotel';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/queens_arms.jpg' WHERE "name" = 'Queens Arms';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/ranalagh.jpg' WHERE "name" = 'The Ranelagh';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/reservoir.jpg' WHERE "name" = 'The Reservoir';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/round_georges.jpg' WHERE "name" = 'Round Georges';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/saint_james.jpg' WHERE "name" = 'Saint James';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/sidewinder.jpg' WHERE "name" = 'The Sidewinder';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/southover.jpg' WHERE "name" = 'The Southover';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/st_georges_inn.jpg' WHERE "name" = 'St George''s Inn';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/the_crown.jpg' WHERE "name" = 'The Crown';
UPDATE "pubs" SET "img_url" = '/assets/images/pubs/thomas_kemp.jpg' WHERE "name" = 'The Thomas Kemp';

# --- !Downs
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Barley Mow';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Black Dove';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Brewdog';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Bulldog Tavern';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Cobden Arms';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Hand In Hand';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Hartington';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Kings Arms Hotel';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Marine Tavern';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Marlborough Hotel';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Queens Arms';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Ranelagh';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Reservoir';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Round Georges';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'Saint James';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Sidewinder';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Southover';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'St George''s Inn';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Crown';
UPDATE "pubs" SET "img_url" = NULL WHERE "name" = 'The Thomas Kemp';