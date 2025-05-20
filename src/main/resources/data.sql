CREATE SCHEMA IF NOT EXISTS better_time_usage_db;

INSERT INTO category (id, name, social)
VALUES
    (1, 'Outdoor', true),
    (2, 'Board Game', false),
    (3, 'Other', false);

INSERT INTO profile (id, email, username, clerk_id)
VALUES
    (1, 'JohnDoe@gmail.com', 'JohnDoe', 'clerkDummyId1'),
    (2, 'DudeMan@gmail.com', 'DudeMan', 'clerkDummyId2'),
    (3, 'ChicaGirl@gmail.com', 'ChicaGirl', 'clerkDummyId3'),
    (4, 'localhost.lagging197@passmail.net', 'hanspeter34', 'user_2xIl8RlWfatMQ44D5Wgt05mB8f4');

INSERT INTO hobby (id, name, description, average_time_consumption, minimum_start_capital, average_capital, img)
VALUES
    (1, 'Hiking', 'A long walk in nature.', '2-3 hours', 100.0, 200.0, 'https://as1.ftcdn.net/v2/jpg/02/91/63/48/1000_F_291634839_WF8mQtlpNZFlG1X34p8RX2dGQhUxynJK.jpg'),
    (2, 'Chess', 'A strategic board game.', '1-2 hours', 50.0, 100.0, 'https://www.lolaapp.com/wp-content/uploads/2023/11/Facts-on-Chess_2.jpg'),
    (3, 'Photography', 'Capturing moments through a lens.', '1-3 hours', 200.0, 500.0, 'https://media.licdn.com/dms/image/C5612AQHhGWNpvI1BiA/article-cover_image-shrink_720_1280/0/1520108955015?e=2147483647&v=beta&t=IcU1LBhINXlNoIDtvqWt_4hyxoIYiwFhJ8g21oS3yXI');

INSERT INTO required_equipment (id, name, hobby_id)
VALUES
    (1, 'Hiking boots', 1),
    (2, 'Backpack', 1),
    (3, 'Chess board', 2);

INSERT INTO tracking (id, money_spent, xp, start_date, profile_id, hobby_id)
VALUES
    (1, 4.2, 0, '2025-04-04 10:10:10', 4, 1),
    (2, 3.5, 0, '2025-04-05 11:11:11', 1, 2),
    (3, 2.8, 0, '2025-04-06 12:12:12', 1, 3);

INSERT INTO goal (id, name, completed, tracking_id, goal_number)
VALUES
    (1, 'Lay a Strategy', false, 1, 1),
    (2, 'Win 20 games', false, 1, 2),
    (3, 'Overcome opponent ', false, 1, 3);

INSERT INTO sub_goal (id, name, completed, goal_id)
VALUES
    (1, 'Play 5 games', false, 1),
    (2, 'Analyze games', false, 1);

INSERT INTO hobby_categories (hobbies_id, categories_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO event (id, name, description, start_time, end_time, location, hobby_id, ticket_price, city)
VALUES
    (1, 'Mountain Hiking', 'A challenging hike in the mountains.', '2025-05-07 17:00', '2025-05-07 20:00:00', 'Mountain Trail', 1, 20.0, 'Los Angeles'),
    (2, 'Chess Tournament', 'A competitive chess tournament.', '2025-05-07 13:00:00', '2025-05-07 18:00:00', 'Community Center', 2, 15.0, 'Los Angeles'),
    (3, 'Photography Workshop', 'Learn photography skills.', '2025-05-08 10:00:00', '2025-05-08 16:00:00', 'Art Studio', 3, 50.0, 'New York'),
    (4, 'Sunset Hiking', 'Enjoy a breathtaking hike during sunset.', '2025-05-10 18:00:00', '2025-05-10 21:00:00', 'Sunset Peak', 1, 25.0, 'Denver'),
    (5, 'Blitz Chess Competition', 'Fast-paced 5-minute game tournament.', '2025-05-15 14:00:00', '2025-05-15 17:00:00', 'Local Chess Club', 2, 10.0, 'San Francisco'),
    (6, 'Night Photography Workshop', 'Master photography in low-light conditions.', '2025-05-18 19:00:00', '2025-05-18 22:00:00', 'National Park', 3, 60.0, 'Chicago'),
    (7, 'Spring Hiking Festival', 'A day-long festival with multiple hiking trails to choose from.', '2025-05-20 08:00:00', '2025-05-20 18:00:00', 'Hiking Sanctuary', 1, 30.0, 'Los Angeles'),
    (8, 'Chess Puzzle Solving Contest', 'Challenge your mind by solving chess puzzles.', '2025-05-23 12:00:00', '2025-05-23 16:00:00', 'City Library', 2, 5.0, 'New York'),
    (9, 'Beginner Photography Course', 'Learn basic photography skills with hands-on practice.', '2025-05-25 09:00:00', '2025-05-25 15:00:00', 'Photography Studio', 3, 40.0, 'San Francisco'),
    (10, 'Trail Cleanup Hike', 'Combine hiking and environmental conservation by cleaning the trails.', '2025-05-28 10:00:00', '2025-05-28 14:00:00', 'Green Valley', 1, 0.0, 'Los Angeles'),
    (11, 'Chess Strategy Workshop', 'Enhance your chess skills with expert strategies.', '2025-05-30 10:00:00', '2025-05-30 14:00:00', 'Chess Academy', 2, 20.0, 'Chicago'),
    (12, 'Outdoor Portrait Photography Session', 'Capture stunning outdoor portraits with professional guidance.', '2025-06-01 09:00:00', '2025-06-01 13:00:00', 'City Park', 3, 45.0, 'Denver'),
    (13, 'Family Hiking Day', 'A fun-filled day of hiking activities for families.', '2025-06-05 10:00:00', '2025-06-05 16:00:00', 'Family Trail', 1, 20.0, 'San Francisco'),
    (14, 'Chess Endgame Masterclass', 'Learn advanced endgame techniques from a grandmaster.', '2025-06-07 14:00:00', '2025-06-07 18:00:00', 'Chess Club', 2, 25.0, 'New York'),
    (15, 'Wildlife Photography Expedition', 'Join us for a wildlife photography adventure in the national park.', '2025-06-10 08:00:00', '2025-06-10 18:00:00', 'National Park', 3, 70.0, 'Denver'),
    (16, 'Editing and Post-Processing Workshop', 'Learn to enhance your photos with professional editing software.', '2025-06-16 10:00:00', '2025-06-16 16:00:00', 'Creative Studio Center', 3, 45.0, 'Los Angeles'),
    (17, 'Seasonal Trail Run', 'Combine running and hiking on this adventure-packed trail.', '2025-06-20 07:00:00', '2025-06-20 12:00:00', 'Riverbank Trail', 1, 20.0, 'Chicago'),
    (18, 'Advanced Chess Tactics', 'A workshop focused on mastering advanced chess tactics.', '2025-06-22 14:00:00', '2025-06-22 18:00:00', 'Chess Academy', 2, 20.0, 'San Francisco'),
    (19, 'Wildlife Photography Expedition', 'Capture stunning photos of wildlife in their natural habitat.', '2025-06-25 05:00:00', '2025-06-25 18:00:00', 'Savannah Reserves', 3, 100.0, 'New York'),
    (20, 'Hiking Gear Swap Meetup', 'Exchange or sell hiking gear with fellow hikers.', '2025-06-27 11:00:00', '2025-06-27 15:00:00', 'Outdoor Center', 1, 5.0, 'Denver');

INSERT INTO community (hobby_id, description, url, forum_name)
VALUES
    (1, 'TrailBlazers - A community of passionate hikers exploring mountain trails across the country. We organize weekly group hikes and monthly camping trips.', 'https://www.trailblazers-hiking.com', 'TrailBlazers Forum'),
    (1, 'Urban Hikers Club - Discover hidden trails and green spaces in urban environments. Perfect for city dwellers who want to connect with nature without traveling far.', 'https://www.urbanhikersclub.org', 'Urban Hikers Forum'),
    (1, 'Family Trekkers - A hiking community focused on family-friendly trails and outdoor activities. We specialize in age-appropriate hikes and nature education for children.', 'https://www.familytrekkers.net', 'Family Trekkers Forum'),
    (1, 'Alpine Explorers - Dedicated to high-altitude hiking and mountain exploration. Our members tackle challenging peaks and share advanced hiking techniques.', 'https://www.alpine-explorers.com', 'Alpine Explorers Forum');

INSERT INTO community (hobby_id, description, url, forum_name)
VALUES
    (2, 'Checkmate Society - An online chess community with daily tournaments and strategic discussions. Players of all levels welcome.', 'https://www.checkmatesociety.com', 'Checkmate Society Forum'),
    (2, 'Knights & Pawns - A chess community focused on teaching the game to beginners through step-by-step tutorials and mentorship programs.', 'https://www.knightsandpawns.org', 'Knights & Pawns Forum'),
    (2, 'Grandmaster Hub - An elite chess community for advanced players looking to refine their skills. Features analysis of professional matches and advanced tactics.', 'https://www.grandmasterhub.chess', 'Grandmaster Hub Forum'),
    (2, 'Chess for Youth - A community dedicated to promoting chess in schools and developing young chess players through competitions and training programs.', 'https://www.chessforyouth.edu', 'Chess for Youth Forum');

INSERT INTO community (hobby_id, description, url, forum_name)
VALUES
    (3, 'Lens Craft Collective - A supportive community for photographers of all skill levels to share their work, get feedback, and participate in monthly themed challenges.', 'https://www.lenscraft.photo', 'Lens Craft Collective Forum'),
    (3, 'Nature Photographers Alliance - Specialized community for wildlife and landscape photography enthusiasts. Includes location guides and conservation initiatives.', 'https://www.naturephotographers.org', 'Nature Photographers Alliance Forum'),
    (3, 'Street Snappers - Urban photography community focused on candid street photography and city scenes. Features regular photo walks in metropolitan areas.', 'https://www.streetsnappers.com', 'Street Snappers Forum'),
    (3, 'Portrait Masters - Community dedicated to portrait photography techniques, lighting setups, and post-processing. Includes portfolio reviews and model connections.', 'https://www.portraitmasters.art', 'Portrait Masters Forum');