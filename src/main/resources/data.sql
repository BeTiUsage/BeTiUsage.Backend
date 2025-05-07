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
    (4, 'localhost.extrovert697@passmail.net', 'martintheman', 'user_2wfZkeECyKZM50lzcjP73TUTyAr');

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
    (1, 4.2, 42, '2025-04-04 10:10:10', 1, 1);

INSERT INTO goal (id, name, completed, tracking_id)
VALUES
    (1, 'Get 600 elo', true, 1),
    (2, 'Get 800 elo', false, 1),
    (3, 'Get 1000 elo', false, 1);

INSERT INTO sub_goal (id, name, completed, goal_id)
VALUES
    (1, 'Play 5 games', true, 1),
    (2, 'Analyze games', false, 1);

INSERT INTO hobby_categories (hobbies_id, categories_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO event (id, name, description, start_time, end_time, location, hobby_id, ticket_price)
VALUES
    (1, 'Mountain Hiking', 'A challenging hike in the mountains.', '2023-10-01 08:00:00', '2023-10-01 17:00:00', 'Mountain Trail', 1, 20.0),
    (2, 'Chess Tournament', 'A competitive chess tournament.', '2023-10-15 09:00:00', '2023-10-15 18:00:00', 'Community Center', 2, 15.0),
    (3, 'Photography Workshop', 'Learn photography skills.', '2023-10-20 10:00:00', '2023-10-20 16:00:00', 'Art Studio', 3, 50.0);

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