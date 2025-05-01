CREATE SCHEMA IF NOT EXISTS better_time_usage_db;


INSERT INTO category (id, name, social) VALUES (1, 'Outdoor', true);
INSERT INTO category (id, name, social) VALUES (2, 'Board Game', false);
INSERT INTO category (id, name, social) VALUES (3, 'Other', false);

INSERT INTO required_equipment (id, name) VALUES (1, 'Hiking boots');
INSERT INTO required_equipment (id, name) VALUES (2, 'Backpack');
INSERT INTO required_equipment (id, name) VALUES (3, 'Chess board');

-- Insert a new hobby into the hobby table
INSERT INTO hobby (id, name, description, average_time_consumption, minimum_start_capital, average_capital)
VALUES
    (1, 'Hiking', 'A long walk in nature.', '2-3 hours', 100.0, 200.0),
    (2, 'Chess', 'A strategic board game.', '1-2 hours', 50.0, 100.0),
    (3, 'Photography', 'Capturing moments through a lens.', '1-3 hours', 200.0, 500.0);

-- Insert hobby_category associations
INSERT INTO hobby_category (hobby_id, category_id) VALUES (1, 1);
INSERT INTO hobby_category (hobby_id, category_id) VALUES (2, 2);
INSERT INTO hobby_category (hobby_id, category_id) VALUES (3, 3);

-- Insert hobby_required_equipment associations
INSERT INTO hobby_required_equipment (hobby_id, required_equipment_id) VALUES (1, 1);
INSERT INTO hobby_required_equipment (hobby_id, required_equipment_id) VALUES (1, 2);
INSERT INTO hobby_required_equipment (hobby_id, required_equipment_id) VALUES (2, 3);

-- Insert events into the event table
INSERT INTO event (id, name, description, start_time, end_time, location, hobby_id, category_id, ticket_price)
VALUES
    (1, 'Mountain Hiking', 'A challenging hike in the mountains.', '2023-10-01 08:00:00', '2023-10-01 17:00:00', 'Mountain Trail', 1, 1, 50.0),
    (2, 'Chess Tournament', 'A competitive chess tournament.', '2023-10-15 09:00:00', '2023-10-15 18:00:00', 'Community Center', 2, 2, 20.0),
    (3, 'Photography Workshop', 'Learn photography skills.', '2023-10-20 10:00:00', '2023-10-20 16:00:00', 'City Park', 3, 3, 40.0);