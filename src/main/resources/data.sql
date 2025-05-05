CREATE SCHEMA IF NOT EXISTS better_time_usage_db;

INSERT INTO category (id, name, social) VALUES (1, 'Outdoor', true);
INSERT INTO category (id, name, social) VALUES (2, 'Board Game', false);
INSERT INTO category (id, name, social) VALUES (3, 'Other', false);

INSERT INTO required_equipment (id, name) VALUES (1, 'Hiking boots');
INSERT INTO required_equipment (id, name) VALUES (2, 'Backpack');
INSERT INTO required_equipment (id, name) VALUES (3, 'Chess board');

INSERT INTO profile (id, email, username) VALUES (1, 'JohnDoe@gmail.com', 'JohnDoe');
INSERT INTO profile (id, email, username) VALUES (2, 'DudeMan@gmail.com', 'DudeMan');
INSERT INTO profile (id, email, username) VALUES (3, 'ChicaGirl@gmail.com', 'ChicaGirl');

-- Insert a new hobby into the hobby table
INSERT INTO hobby (id, name, description, average_time_consumption, minimum_start_capital, average_capital, profile_id)
VALUES
    (1, 'Hiking', 'A long walk in nature.', '2-3 hours', 100.0, 200.0, 1),
    (2, 'Chess', 'A strategic board game.', '1-2 hours', 50.0, 100.0, 2),
    (3, 'Photography', 'Capturing moments through a lens.', '1-3 hours', 200.0, 500.0, 3);

INSERT INTO tracking (id, money_spent, xp, start_date, profile_id, hobby_id) VALUES (1, 4.2, 42, '2025-04-04 10:10:10', 1, 1);

INSERT INTO goal (id, name, completed, tracking_id) VALUES (1, 'Get 600 elo', true, 1);
INSERT INTO goal (id, name, completed, tracking_id) VALUES (2, 'Get 800 elo', false, 1);
INSERT INTO goal (id, name, completed, tracking_id) VALUES (3, 'Get 1000 elo', false, 1);

INSERT INTO sub_goal (id, name, completed, goal_id) VALUES (1, 'Play 5 games', true, 1);
INSERT INTO sub_goal (id, name, completed, goal_id) VALUES (2, 'Analyze games', false, 1);

-- Insert hobby_category associations
INSERT INTO hobby_category (hobby_id, category_id) VALUES (1, 1);
INSERT INTO hobby_category (hobby_id, category_id) VALUES (2, 2);
INSERT INTO hobby_category (hobby_id, category_id) VALUES (3, 3);

-- Insert hobby_required_equipment associations
INSERT INTO hobby_required_equipment (hobby_id, required_equipment_id) VALUES (1, 1);
INSERT INTO hobby_required_equipment (hobby_id, required_equipment_id) VALUES (1, 2);
INSERT INTO hobby_required_equipment (hobby_id, required_equipment_id) VALUES (2, 3);