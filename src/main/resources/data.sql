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
INSERT INTO event (id, name, description, start_time, end_time, location, hobby_id, ticket_price)
VALUES
    (1, 'Mountain Hiking', 'A challenging hike in the mountains.', '2023-10-01 08:00:00', '2023-10-01 17:00:00', 'Mountain Trail', 1, 20.0),
    (2, 'Chess Tournament', 'A competitive chess tournament.', '2023-10-15 09:00:00', '2023-10-15 18:00:00', 'Community Center', 2, 15.0),
    (3, 'Photography Workshop', 'Learn photography skills.', '2023-10-20 10:00:00', '2023-10-20 16:00:00', 'Art Studio', 3, 50.0);

-- Communities for Hiking (hobby_id = 1)
INSERT INTO community (hobby_id, description, url)
VALUES
    (1, 'TrailBlazers - A community of passionate hikers exploring mountain trails across the country. We organize weekly group hikes and monthly camping trips.', 'https://www.trailblazers-hiking.com'),
    (1, 'Urban Hikers Club - Discover hidden trails and green spaces in urban environments. Perfect for city dwellers who want to connect with nature without traveling far.', 'https://www.urbanhikersclub.org'),
    (1, 'Family Trekkers - A hiking community focused on family-friendly trails and outdoor activities. We specialize in age-appropriate hikes and nature education for children.', 'https://www.familytrekkers.net'),
    (1, 'Alpine Explorers - Dedicated to high-altitude hiking and mountain exploration. Our members tackle challenging peaks and share advanced hiking techniques.', 'https://www.alpine-explorers.com');

-- Communities for Chess (hobby_id = 2)
INSERT INTO community (hobby_id, description, url)
VALUES
    (2, 'Checkmate Society - An online chess community with daily tournaments and strategic discussions. Players of all levels welcome.', 'https://www.checkmatesociety.com'),
    (2, 'Knights & Pawns - A chess community focused on teaching the game to beginners through step-by-step tutorials and mentorship programs.', 'https://www.knightsandpawns.org'),
    (2, 'Grandmaster Hub - An elite chess community for advanced players looking to refine their skills. Features analysis of professional matches and advanced tactics.', 'https://www.grandmasterhub.chess'),
    (2, 'Chess for Youth - A community dedicated to promoting chess in schools and developing young chess players through competitions and training programs.', 'https://www.chessforyouth.edu');

-- Communities for Photography (hobby_id = 3)
INSERT INTO community (hobby_id, description, url)
VALUES
    (3, 'Lens Craft Collective - A supportive community for photographers of all skill levels to share their work, get feedback, and participate in monthly themed challenges.', 'https://www.lenscraft.photo'),
    (3, 'Nature Photographers Alliance - Specialized community for wildlife and landscape photography enthusiasts. Includes location guides and conservation initiatives.', 'https://www.naturephotographers.org'),
    (3, 'Street Snappers - Urban photography community focused on candid street photography and city scenes. Features regular photo walks in metropolitan areas.', 'https://www.streetsnappers.com'),
    (3, 'Portrait Masters - Community dedicated to portrait photography techniques, lighting setups, and post-processing. Includes portfolio reviews and model connections.', 'https://www.portraitmasters.art');