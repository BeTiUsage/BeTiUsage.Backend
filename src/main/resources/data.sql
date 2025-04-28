CREATE SCHEMA IF NOT EXISTS better_time_usage_db;


INSERT INTO category (id, name, social) VALUES (1, 'Outdoor', true);
INSERT INTO category (id, name, social) VALUES (2, 'Board Game', false);
INSERT INTO category (id, name, social) VALUES (3, 'Other', false);

INSERT INTO required_equipment (id, name) VALUES (1, 'Hiking boots');
INSERT INTO required_equipment (id, name) VALUES (2, 'Backpack');
INSERT INTO required_equipment (id, name) VALUES (3, 'Chess board');


-- Insert hobbies into the hobby table
INSERT INTO hobby (id, name, description, average_time_consumption, required_equipment_id, minimum_start_capital, average_capital)
VALUES
    (1, 'Hiking', 'Exploring outdoor trails and mountains.', '2-4 hours', 1, 100.0, 200.0),
    (2, 'Backpacking', 'Multi-day hikes with camping gear.', '8-12 hours', 2, 500.0, 1000.0),
    (3, 'Chess', 'A strategic board game.', '1-2 hours', 3, 20.0, 50.0);

-- Insert hobbies into the hobby_category table
INSERT INTO hobby_category (hobby_id, category_id) VALUES (1, 1);
INSERT INTO hobby_category (hobby_id, category_id) VALUES (2, 1);
INSERT INTO hobby_category (hobby_id, category_id) VALUES (3, 2);