CREATE SCHEMA IF NOT EXISTS better_time_usage_db;

INSERT INTO category (id, name, social)
VALUES
    (1, 'Outdoor', true),
    (2, 'Board Game', false),
    (3, 'Other', false),
    (4, 'Creative Arts', false),
    (5, 'Sports', true),
    (6, 'Music', false),
    (7, 'Technology', false),
    (8, 'Cooking', false),
    (9, 'Fitness', true),
    (10, 'Crafts', false),
    (11, 'Reading', false),
    (12, 'Gardening', false);

INSERT INTO goal (id, name, completed, goal_number, hobby_name)
VALUES
-- Hiking Goals
(1, 'Complete a 10-mile trail hike', false, 1, 'Hiking'),
(2, 'Explore 5 new hiking locations', false, 2, 'Hiking'),
(3, 'Improve hiking endurance', false, 3, 'Hiking'),

-- Chess Goals
(4, 'Win a local chess tournament', false, 1, 'Chess'),
(5, 'Learn 10 new chess openings', false, 2, 'Chess'),
(6, 'Solve chess puzzles daily', false, 3, 'Chess'),

-- Photography Goals
(7, 'Master night photography', false, 1, 'Photography'),
(8, 'Create a nature photo portfolio', false, 2, 'Photography'),
(9, 'Learn advanced photo editing', false, 3, 'Photography'),

-- Rock Climbing Goals
(10, 'Climb a challenging indoor route', false, 1, 'Rock Climbing'),
(11, 'Participate in an outdoor climbing event', false, 2, 'Rock Climbing'),
(12, 'Improve bouldering techniques', false, 3, 'Rock Climbing'),

-- Pottery Goals
(13, 'Create a set of functional mugs', false, 1, 'Pottery'),
(14, 'Attend a pottery workshop', false, 2, 'Pottery'),
(15, 'Experiment with glazing techniques', false, 3, 'Pottery'),

-- Guitar Playing Goals
(16, 'Learn to play 5 songs', false, 1, 'Guitar Playing'),
(17, 'Practice daily finger exercises', false, 2, 'Guitar Playing'),
(18, 'Perform in a small concert', false, 3, 'Guitar Playing'),

-- Cooking Goals
(19, 'Master 10 new recipes', false, 1, 'Cooking'),
(20, 'Learn baking techniques', false, 2, 'Cooking'),
(21, 'Host a dinner party', false, 3, 'Cooking'),

-- Painting Goals
(22, 'Complete 3 landscape paintings', false, 1, 'Painting'),
(23, 'Experiment with acrylics and oils', false, 2, 'Painting'),
(24, 'Join a local art exhibition', false, 3, 'Painting'),

-- Yoga Goals
(25, 'Practice daily yoga for 30 days', false, 1, 'Yoga'),
(26, 'Learn advanced poses', false, 2, 'Yoga'),
(27, 'Attend a yoga retreat', false, 3, 'Yoga'),

-- Knitting Goals
(28, 'Finish a full sweater', false, 1, 'Knitting'),
(29, 'Learn 3 new knitting patterns', false, 2, 'Knitting'),
(30, 'Gift a handmade scarf', false, 3, 'Knitting'),

-- Cycling Goals
(31, 'Complete a 50-mile ride', false, 1, 'Cycling'),
(32, 'Explore new cycling trails', false, 2, 'Cycling'),
(33, 'Join a cycling club event', false, 3, 'Cycling'),

-- Reading Goals
(34, 'Read 20 books in a year', false, 1, 'Reading'),
(35, 'Join a book club', false, 2, 'Reading'),
(36, 'Explore new genres', false, 3, 'Reading'),

-- Gardening Goals
(37, 'Grow a vegetable garden', false, 1, 'Gardening'),
(38, 'Plant seasonal flowers', false, 2, 'Gardening'),
(39, 'Set up a compost system', false, 3, 'Gardening'),

-- Programming Goals
(40, 'Build a personal project app', false, 1, 'Programming'),
(41, 'Learn a new programming language', false, 2, 'Programming'),
(42, 'Contribute to open-source', false, 3, 'Programming'),

-- Dancing Goals
(43, 'Learn 3 dance styles', false, 1, 'Dancing'),
(44, 'Perform at a local event', false, 2, 'Dancing'),
(45, 'Improve flexibility and rhythm', false, 3, 'Dancing'),

-- Woodworking Goals
(46, 'Build a wooden chair', false, 1, 'Woodworking'),
(47, 'Learn finishing techniques', false, 2, 'Woodworking'),
(48, 'Create a decorative wooden box', false, 3, 'Woodworking'),

-- Swimming Goals
(49, 'Swim 1km without stopping', false, 1, 'Swimming'),
(50, 'Attend a swim technique class', false, 2, 'Swimming'),
(51, 'Participate in a swim race', false, 3, 'Swimming'),

-- Baking Goals
(52, 'Bake 5 types of bread', false, 1, 'Baking'),
(53, 'Perfect pastry making', false, 2, 'Baking'),
(54, 'Create a baking blog', false, 3, 'Baking'),

-- Tennis Goals
(55, 'Improve backhand technique', false, 1, 'Tennis'),
(56, 'Play weekly matches', false, 2, 'Tennis'),
(57, 'Join a local tennis league', false, 3, 'Tennis'),

-- Astronomy Goals
(58, 'Identify 20 constellations', false, 1, 'Astronomy'),
(59, 'Attend a star-gazing event', false, 2, 'Astronomy'),
(60, 'Learn astrophotography', false, 3, 'Astronomy'),

-- Skateboarding Goals
(61, 'Learn to ollie', false, 1, 'Skateboarding'),
(62, 'Practice park tricks', false, 2, 'Skateboarding'),
(63, 'Join a skateboarding community', false, 3, 'Skateboarding'),

-- Meditation Goals
(64, 'Meditate daily for 10 minutes', false, 1, 'Meditation'),
(65, 'Try different meditation techniques', false, 2, 'Meditation'),
(66, 'Attend a meditation workshop', false, 3, 'Meditation'),

-- Fishing Goals
(67, 'Catch 3 different fish species', false, 1, 'Fishing'),
(68, 'Learn fly fishing', false, 2, 'Fishing'),
(69, 'Go on a fishing trip', false, 3, 'Fishing');


INSERT INTO sub_goal (id, name, completed, goal_id) VALUES
-- Subgoals for Hiking Goals
(1, 'Train by completing shorter hikes of 4-6 miles', false, 1),
(2, 'Prepare hiking kit with essential supplies', false, 1),
(3, 'Map out trail route and checkpoints', false, 1),

(4, 'Research 5 new hiking locations online', false, 2),
(5, 'Plan weekend trips to explore locations', false, 2),

(6, 'Do cardio workouts 3 times per week', false, 3),
(7, 'Increase hike distance progressively', false, 3),

-- Subgoals for Chess Goals
(8, 'Practice openings daily', false, 4),
(9, 'Play timed games to improve speed', false, 4),

(10, 'Study 2 new openings weekly', false, 5),
(11, 'Watch tutorials on opening strategies', false, 5),

(12, 'Solve 5 puzzles daily', false, 6),
(13, 'Review solved puzzles for learning', false, 6),

-- Subgoals for Photography Goals
(14, 'Read about techniques for night shots', false, 7),
(15, 'Scout locations good for night photography', false, 7),

(16, 'Take daily nature photos', false, 8),
(17, 'Select best photos for portfolio', false, 8),

(18, 'Learn photo editing software basics', false, 9),
(19, 'Practice editing different photo types', false, 9),

-- Subgoals for Rock Climbing Goals
(20, 'Attend indoor climbing sessions twice a week', false, 10),
(21, 'Practice footwork drills', false, 10),

(22, 'Join local outdoor climbing group', false, 11),
(23, 'Plan outdoor climbing trips', false, 11),

(24, 'Watch technique videos', false, 12),
(25, 'Practice boulder problems regularly', false, 12),

-- Subgoals for Pottery Goals
(26, 'Research pottery mug designs', false, 13),
(27, 'Practice wheel throwing', false, 13),

(28, 'Register for a pottery workshop', false, 14),

(29, 'Experiment with glaze mixtures', false, 15),
(30, 'Test glaze on sample pieces', false, 15),

-- Subgoals for Guitar Playing Goals
(31, 'Learn chord transitions for new songs', false, 16),
(32, 'Practice fingering exercises daily', false, 17),
(33, 'Rehearse for concert performance', false, 18),

-- Subgoals for Cooking Goals
(34, 'Collect recipes for 10 new dishes', false, 19),
(35, 'Shop for required ingredients weekly', false, 19),

(36, 'Attend baking class', false, 20),

(37, 'Plan menu for dinner party', false, 21),
(38, 'Invite friends and family', false, 21),

-- Subgoals for Painting Goals
(39, 'Sketch landscapes as practice', false, 22),
(40, 'Complete final versions of 3 paintings', false, 22),

(41, 'Test acrylic and oil paints', false, 23),

(42, 'Prepare artworks for exhibition', false, 24),

-- Subgoals for Yoga Goals
(43, 'Perform yoga daily for 30 min', false, 25),
(44, 'Learn 3 new poses', false, 26),

(45, 'Book and attend yoga retreat', false, 27),

-- Subgoals for Knitting Goals
(46, 'Complete sweater pattern sections', false, 28),
(47, 'Practice new knitting stitches', false, 29),

(48, 'Make scarf with chosen pattern', false, 30),

-- Subgoals for Cycling Goals
(49, 'Train with long rides on weekends', false, 31),
(50, 'Explore 3 new trails each month', false, 32),

(51, 'Register for cycling club events', false, 33),

-- Subgoals for Reading Goals
(52, 'Set monthly book reading targets', false, 34),
(53, 'Attend monthly book club meetings', false, 35),

(54, 'Try a new genre every 2 months', false, 36),

-- Subgoals for Gardening Goals
(55, 'Prepare soil and plant vegetables', false, 37),
(56, 'Water and maintain the garden regularly', false, 37),

(57, 'Choose and plant seasonal flowers', false, 38),

(58, 'Start compost bin for kitchen waste', false, 39),

-- Subgoals for Programming Goals
(59, 'Plan features for the project app', false, 40),
(60, 'Code core modules step-by-step', false, 40),

(61, 'Complete tutorials for new language', false, 41),

(62, 'Find an open-source project to contribute', false, 42),

-- Subgoals for Dancing Goals
(63, 'Take dance classes for 3 styles', false, 43),

(64, 'Practice routine for event performance', false, 44),

(65, 'Stretch and improve rhythm weekly', false, 45),

-- Subgoals for Woodworking Goals
(66, 'Design chair blueprint', false, 46),
(67, 'Select wood type and tools', false, 46),

(68, 'Learn wood finishing methods', false, 47),

(69, 'Build wooden box prototype', false, 48),

-- Subgoals for Swimming Goals
(70, 'Swim laps to build endurance', false, 49),
(71, 'Take technique class weekly', false, 50),

(72, 'Sign up for swim race', false, 51),

-- Subgoals for Baking Goals
(73, 'Try recipes for 5 bread types', false, 52),
(74, 'Practice kneading and baking techniques', false, 52),

(75, 'Bake pastries weekly', false, 53),

(76, 'Write blog posts on baking experiences', false, 54),

-- Subgoals for Tennis Goals
(77, 'Practice backhand daily', false, 55),

(78, 'Schedule weekly matches', false, 56),

(79, 'Join tennis league and attend matches', false, 57),

-- Subgoals for Astronomy Goals
(80, 'Learn star charts for constellations', false, 58),
(81, 'Attend guided star-gazing sessions', false, 59),

(82, 'Practice astrophotography techniques', false, 60),

-- Subgoals for Skateboarding Goals
(83, 'Practice ollie fundamentals', false, 61),

(84, 'Train park tricks on weekends', false, 62),

(85, 'Connect with skateboarding community', false, 63),

-- Subgoals for Meditation Goals
(86, 'Start meditation with 10 min sessions', false, 64),

(87, 'Experiment with different meditation methods', false, 65),

(88, 'Register for meditation workshop', false, 66),

-- Subgoals for Fishing Goals
(89, 'Research habits of fish species', false, 67),
(90, 'Practice fly casting techniques', false, 68),
(91, 'Plan fishing trips at local lakes', false, 69);

INSERT INTO hobby (id, name, description, average_time_consumption, minimum_start_capital, average_capital, img)
VALUES
    (1, 'Hiking', 'A long walk in nature.', '2-3 hours', 100.0, 200.0, 'https://as1.ftcdn.net/v2/jpg/02/91/63/48/1000_F_291634839_WF8mQtlpNZFlG1X34p8RX2dGQhUxynJK.jpg'),
    (2, 'Chess', 'A strategic board game.', '1-2 hours', 50.0, 100.0, 'https://www.lolaapp.com/wp-content/uploads/2023/11/Facts-on-Chess_2.jpg'),
    (3, 'Photography', 'Capturing moments through a lens.', '1-3 hours', 200.0, 500.0, 'https://images.unsplash.com/photo-1542038784456-1ea8e935640e?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
    (4, 'Rock Climbing', 'Indoor and outdoor climbing adventures.', '2-4 hours', 300.0, 600.0, 'https://images.unsplash.com/photo-1544551763-46a013bb70d5?w=800'),
    (5, 'Pottery', 'Creating ceramic art and functional pieces.', '2-3 hours', 150.0, 400.0, 'https://images.unsplash.com/photo-1578662996442-48f60103fc96?w=800'),
    (6, 'Guitar Playing', 'Learning and mastering the guitar.', '1-2 hours', 200.0, 500.0, 'https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=800'),
    (7, 'Cooking', 'Exploring culinary arts and recipes.', '1-3 hours', 100.0, 300.0, 'https://images.unsplash.com/photo-1556909114-f6e7ad7d3136?w=800'),
    (8, 'Painting', 'Watercolor, acrylic, and oil painting.', '2-4 hours', 80.0, 250.0, 'https://images.unsplash.com/photo-1513475382585-d06e58bcb0e0?w=800'),
    (9, 'Yoga', 'Physical and mental wellness practice.', '1-1.5 hours', 50.0, 150.0, 'https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=800'),
    (10, 'Knitting', 'Creating textiles through knitting techniques.', '2-3 hours', 40.0, 120.0, 'https://images.unsplash.com/photo-1584464491033-06628f3a6b7b?w=800'),
    (11, 'Cycling', 'Road and mountain biking adventures.', '2-5 hours', 400.0, 800.0, 'https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=800'),
    (12, 'Reading', 'Exploring literature and knowledge through books.', '1-3 hours', 20.0, 100.0, 'https://images.unsplash.com/photo-1481627834876-b7833e8f5570?w=800'),
    (13, 'Gardening', 'Growing plants, flowers, and vegetables.', '2-4 hours', 100.0, 250.0, 'https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=800'),
    (14, 'Programming', 'Software development and coding skills.', '2-6 hours', 500.0, 1200.0, 'https://images.unsplash.com/photo-1461749280684-dccba630e2f6?w=800'),
    (15, 'Dancing', 'Various dance styles and techniques.', '1-2 hours', 80.0, 200.0, 'https://images.unsplash.com/photo-1547153760-18fc86324498?w=800'),
    (16, 'Woodworking', 'Creating furniture and decorative items from wood.', '3-6 hours', 300.0, 800.0, 'https://images.unsplash.com/photo-1504148455328-c376907d081c?w=800'),
    (17, 'Swimming', 'Recreational and competitive swimming.', '1-2 hours', 100.0, 250.0, 'https://images.unsplash.com/photo-1530549387789-4c1017266635?w=800'),
    (18, 'Baking', 'Creating breads, pastries, and desserts.', '2-4 hours', 150.0, 350.0, 'https://images.unsplash.com/photo-1597528662465-55ece5734101?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
    (19, 'Tennis', 'Racquet sport for fitness and competition.', '1-3 hours', 150.0, 400.0, 'https://images.unsplash.com/photo-1622279457486-62dcc4a431d6?w=800'),
    (20, 'Astronomy', 'Observing and studying celestial objects.', '2-5 hours', 200.0, 600.0, 'https://images.unsplash.com/photo-1446776653964-20c1d3a81b06?w=800'),
    (21, 'Skateboarding', 'Street and park skateboarding skills.', '2-4 hours', 120.0, 300.0, 'https://images.unsplash.com/photo-1517582837435-fdb3ccb5bb41?q=80&w=2304&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
    (22, 'Meditation', 'Mindfulness and mental wellness practice.', '0.5-2 hours', 20.0, 80.0, 'https://images.unsplash.com/photo-1593811167562-9cef47bfc4d7?w=800'),
    (23, 'Fishing', 'Recreational angling in various water bodies.', '3-8 hours', 100.0, 350.0, 'https://images.unsplash.com/photo-1545450660-3378a7f3a364?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');


INSERT INTO required_equipment (id, name, hobby_id)
VALUES
    (1, 'Hiking boots', 1),
    (2, 'Backpack', 1),
    (3, 'Chess board', 2),
    (4, 'Climbing shoes', 4),
    (5, 'Harness', 4),
    (6, 'Pottery wheel', 5),
    (7, 'Clay', 5),
    (8, 'Acoustic guitar', 6),
    (9, 'Guitar picks', 6),
    (10, 'Chef knife', 7),
    (11, 'Cutting board', 7),
    (12, 'Paint brushes', 8),
    (13, 'Canvas', 8),
    (14, 'Yoga mat', 9),
    (15, 'Knitting needles', 10),
    (16, 'Yarn', 10),
    (17, 'Road bike', 11),
    (18, 'Helmet', 11),
    (19, 'Garden tools', 13),
    (20, 'Seeds', 13),
    (21, 'Computer', 14),
    (22, 'IDE software', 14),
    (23, 'Dance shoes', 15),
    (24, 'Hand saw', 16),
    (25, 'Wood planks', 16),
    (26, 'Swimsuit', 17),
    (27, 'Goggles', 17),
    (28, 'Mixing bowls', 18),
    (29, 'Measuring cups', 18),
    (30, 'Tennis racket', 19),
    (31, 'Tennis balls', 19),
    (32, 'Telescope', 20),
    (33, 'Star chart', 20),
    (34, 'Skateboard', 21),
    (35, 'Protective pads', 21),
    (36, 'Meditation cushion', 22),
    (37, 'Fishing rod', 23),
    (38, 'Fishing reel', 23);

INSERT INTO hobby_categories (hobbies_id, categories_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 5),
    (5, 4),
    (6, 6),
    (7, 8),
    (8, 4),
    (9, 9),
    (10, 10),
    (11, 5),
    (12, 11),
    (13, 12),
    (14, 7),
    (15, 5),
    (16, 10),
    (17, 9),
    (18, 8),
    (19, 5),
    (20, 7),
    (21, 5),
    (22, 9),
    (23, 1);

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
    (20, 'Hiking Gear Swap Meetup', 'Exchange or sell hiking gear with fellow hikers.', '2025-06-27 11:00:00', '2025-06-27 15:00:00', 'Outdoor Center', 1, 5.0, 'Denver'),
    (21, 'Beginner Rock Climbing', 'Introduction to indoor rock climbing.', '2025-05-10 14:00:00', '2025-05-10 17:00:00', 'Climbing Gym', 4, 35.0, 'Los Angeles'),
    (22, 'Pottery Workshop', 'Learn basic pottery techniques.', '2025-05-12 10:00:00', '2025-05-12 15:00:00', 'Art Center', 5, 45.0, 'San Francisco'),
    (23, 'Guitar Basics Class', 'Learn your first chords and songs.', '2025-05-15 18:00:00', '2025-05-15 20:00:00', 'Music Studio', 6, 30.0, 'New York'),
    (24, 'Italian Cooking Class', 'Master authentic Italian dishes.', '2025-05-18 17:00:00', '2025-05-18 21:00:00', 'Culinary School', 7, 60.0, 'Chicago'),
    (25, 'Watercolor Painting Session', 'Paint landscapes with watercolors.', '2025-05-20 13:00:00', '2025-05-20 16:00:00', 'Art Studio', 8, 40.0, 'Denver'),
    (26, 'Morning Yoga Class', 'Start your day with energizing yoga.', '2025-05-22 08:00:00', '2025-05-22 09:30:00', 'Yoga Studio', 9, 20.0, 'Los Angeles'),
    (27, 'Knitting Circle', 'Knit and socialize with fellow crafters.', '2025-05-25 14:00:00', '2025-05-25 17:00:00', 'Community Center', 10, 15.0, 'San Francisco'),
    (28, 'City Bike Tour', 'Explore the city on two wheels.', '2025-05-28 09:00:00', '2025-05-28 13:00:00', 'City Park', 11, 25.0, 'New York'),
    (29, 'Book Club Meeting', 'Discuss this months selected novel.', '2025-05-30 19:00:00', '2025-05-30 21:00:00', 'Local Library', 12, 0.0, 'Chicago'),
    (30, 'Community Garden Workshop', 'Learn sustainable gardening practices.', '2025-06-02 10:00:00', '2025-06-02 14:00:00', 'Community Garden', 13, 20.0, 'Denver'),
    (31, 'Python Programming Bootcamp', 'Weekend intensive Python course.', '2025-06-05 09:00:00', '2025-06-05 17:00:00', 'Tech Hub', 14, 150.0, 'San Francisco'),
    (32, 'Salsa Dancing Night', 'Learn salsa steps and dance socially.', '2025-06-07 20:00:00', '2025-06-07 23:00:00', 'Dance Studio', 15, 25.0, 'New York'),
    (33, 'Woodworking Project Day', 'Build a small furniture piece.', '2025-06-10 10:00:00', '2025-06-10 16:00:00', 'Workshop Space', 16, 80.0, 'Chicago'),
    (34, 'Swimming Competition', 'Local amateur swimming meet.', '2025-06-12 14:00:00', '2025-06-12 18:00:00', 'Aquatic Center', 17, 15.0, 'Los Angeles'),
    (35, 'French Pastry Class', 'Learn to make croissants and éclairs.', '2025-06-15 09:00:00', '2025-06-15 15:00:00', 'Baking School', 18, 85.0, 'San Francisco'),
    (36, 'Tennis Tournament', 'Amateur doubles tennis competition.', '2025-06-18 08:00:00', '2025-06-18 16:00:00', 'Tennis Club', 19, 40.0, 'Denver'),
    (37, 'Stargazing Night', 'Observe planets and constellations.', '2025-06-20 21:00:00', '2025-06-21 01:00:00', 'Observatory', 20, 30.0, 'Chicago'),
    (38, 'Skateboard Workshop', 'Learn basic tricks and safety.', '2025-06-22 15:00:00', '2025-06-22 18:00:00', 'Skate Park', 21, 20.0, 'Los Angeles'),
    (39, 'Meditation Retreat', 'Day-long mindfulness meditation.', '2025-06-25 09:00:00', '2025-06-25 17:00:00', 'Zen Center', 22, 50.0, 'San Francisco'),
    (40, 'Fishing Derby', 'Catch and release fishing competition.', '2025-06-28 06:00:00', '2025-06-28 14:00:00', 'Lake Park', 23, 25.0, 'Denver');

INSERT INTO community (hobby_id, description, url, forum_name)
VALUES
    (1, 'TrailBlazers - A community of passionate hikers exploring mountain trails across the country. We organize weekly group hikes and monthly camping trips.', 'https://www.trailblazers-hiking.com', 'TrailBlazers Forum'),
    (1, 'Urban Hikers Club - Discover hidden trails and green spaces in urban environments. Perfect for city dwellers who want to connect with nature without traveling far.', 'https://www.urbanhikersclub.org', 'Urban Hikers Forum'),
    (1, 'Family Trekkers - A hiking community focused on family-friendly trails and outdoor activities. We specialize in age-appropriate hikes and nature education for children.', 'https://www.familytrekkers.net', 'Family Trekkers Forum'),
    (1, 'Alpine Explorers - Dedicated to high-altitude hiking and mountain exploration. Our members tackle challenging peaks and share advanced hiking techniques.', 'https://www.alpine-explorers.com', 'Alpine Explorers Forum'),

    (2, 'Checkmate Society - An online chess community with daily tournaments and strategic discussions. Players of all levels welcome.', 'https://www.checkmatesociety.com', 'Checkmate Society Forum'),
    (2, 'Knights & Pawns - A chess community focused on teaching the game to beginners through step-by-step tutorials and mentorship programs.', 'https://www.knightsandpawns.org', 'Knights & Pawns Forum'),
    (2, 'Grandmaster Hub - An elite chess community for advanced players looking to refine their skills. Features analysis of professional matches and advanced tactics.', 'https://www.grandmasterhub.chess', 'Grandmaster Hub Forum'),
    (2, 'Chess for Youth - A community dedicated to promoting chess in schools and developing young chess players through competitions and training programs.', 'https://www.chessforyouth.edu', 'Chess for Youth Forum'),

    (3, 'Lens Craft Collective - A supportive community for photographers of all skill levels to share their work, get feedback, and participate in monthly themed challenges.', 'https://www.lenscraft.photo', 'Lens Craft Collective Forum'),
    (3, 'Nature Photographers Alliance - Specialized community for wildlife and landscape photography enthusiasts. Includes location guides and conservation initiatives.', 'https://www.naturephotographers.org', 'Nature Photographers Alliance Forum'),
    (3, 'Street Snappers - Urban photography community focused on candid street photography and city scenes. Features regular photo walks in metropolitan areas.', 'https://www.streetsnappers.com', 'Street Snappers Forum'),
    (3, 'Portraita Masters - Community dedicated to portrait photography techniques, lighting setups, and post-processing. Includes portfolio reviews and model connections.', 'https://www.portraitmasters.art', 'Portrait Masters Forum'),

    (4, 'Vertical Limits - Rock climbing community for indoor and outdoor enthusiasts. Share routes, safety tips, and organize climbing expeditions.', 'https://www.verticallimits.com', 'Vertical Limits Forum'),
    (4, 'Boulder Buddies - Focus on bouldering techniques and community. Great for beginners and experts to share beta and training tips.', 'https://www.boulderbuddies.org', 'Boulder Buddies Forum'),

    (5, 'Clay Creators - Community of pottery enthusiasts sharing techniques, firing tips, and showcasing ceramic art pieces.', 'https://www.claycreators.art', 'Clay Creators Forum'),
    (5, 'Wheel Warriors - Advanced pottery community focusing on wheel throwing techniques and glazing methods.', 'https://www.wheelwarriors.net', 'Wheel Warriors Forum'),

    (6, 'Guitar Galaxy - Comprehensive guitar learning community with tabs, lessons, and gear reviews for all skill levels.', 'https://www.guitargalaxy.com', 'Guitar Galaxy Forum'),
    (6, 'Acoustic Alliance - Focused on acoustic guitar playing, fingerstyle techniques, and songwriting collaboration.', 'https://www.acousticalliance.org', 'Acoustic Alliance Forum'),

    (7, 'Culinary Craftsmen - Community for home cooks to share recipes, cooking techniques, and kitchen equipment reviews.', 'https://www.culinarycraftsmen.com', 'Culinary Craftsmen Forum'),
    (7, 'Global Flavors - International cooking community exploring cuisines from around the world with authentic recipes.', 'https://www.globalflavors.net', 'Global Flavors Forum'),

    (8, 'Paint & Palette - Art community for painters of all mediums to share work, get critiques, and learn new techniques.', 'https://www.paintandpalette.art', 'Paint & Palette Forum'),
    (8, 'Watercolor Wonders - Specialized community for watercolor artists with tutorials and technique sharing.', 'https://www.watercolorwonders.com', 'Watercolor Wonders Forum'),

    (9, 'Yoga Journey - Supportive yoga community for practitioners of all levels with pose guidance and mindfulness discussions.', 'https://www.yogajourney.wellness', 'Yoga Journey Forum'),
    (9, 'Zen Flow - Advanced yoga community focusing on challenging poses, meditation integration, and teacher training.', 'https://www.zenflow.org', 'Zen Flow Forum'),

    (10, 'Stitch Society - Knitting and crocheting community sharing patterns, yarn reviews, and finished project showcases.', 'https://www.stitchsociety.craft', 'Stitch Society Forum'),
    (10, 'Yarn Enthusiasts - Community for fiber artists exploring advanced techniques like colorwork and lace knitting.', 'https://www.yarnenthusiasts.com', 'Yarn Enthusiasts Forum'),

    (11, 'Pedal Power - Cycling community for road and mountain bikers sharing routes, maintenance tips, and group ride organization.', 'https://www.pedalpower.bike', 'Pedal Power Forum'),
    (11, 'Trail Riders - Mountain biking focused community with trail reviews, bike setup advice, and safety discussions.', 'https://www.trailriders.mtb', 'Trail Riders Forum'),

    (12, 'Book Lovers United - Reading community with book recommendations, author discussions, and virtual book clubs.', 'https://www.bookloversunited.com', 'Book Lovers United Forum'),
    (12, 'Literary Circle - Advanced reading community focusing on classic literature analysis and literary criticism.', 'https://www.literarycircle.org', 'Literary Circle Forum'),

    (13, 'Green Thumbs - Gardening community sharing growing tips, plant identification, and seasonal gardening advice.', 'https://www.greenthumbs.garden', 'Green Thumbs Forum'),
    (13, 'Organic Growers - Sustainable gardening community focused on organic methods and permaculture principles.', 'https://www.organicgrowers.eco', 'Organic Growers Forum'),

    (14, 'Code Collective - Programming community for developers of all levels sharing code, debugging help, and career advice.', 'https://www.codecollective.dev', 'Code Collective Forum'),
    (14, 'Algorithm Academy - Advanced programming community focusing on data structures, algorithms, and competitive programming.', 'https://www.algorithmacademy.com', 'Algorithm Academy Forum'),

    (15, 'Dance Dynasty - Multi-style dance community with technique videos, event listings, and partner finding.', 'https://www.dancedynasty.dance', 'Dance Dynasty Forum'),
    (15, 'Rhythm Realm - Social dance community specializing in partner dances like salsa, swing, and tango.', 'https://www.rhythmrealm.social', 'Rhythm Realm Forum'),

    (16, 'Wood Warriors - Woodworking community sharing project plans, tool reviews, and craftsmanship tips.', 'https://www.woodwarriors.craft', 'Wood Warriors Forum'),
    (16, 'Fine Furniture Makers - Advanced woodworking community focusing on furniture making and fine craftsmanship.', 'https://www.finefurnituremakers.com', 'Fine Furniture Makers Forum'),

    (17, 'Swim Squad - Swimming community for lap swimmers and competitors with training plans and technique analysis.', 'https://www.swimsquad.pool', 'Swim Squad Forum'),
    (17, 'Aquatic Athletes - Competitive swimming community with race preparation, stroke technique, and meet results.', 'https://www.aquaticathletes.com', 'Aquatic Athletes Forum'),

    (18, 'Baking Brotherhood - Community for home bakers sharing recipes, troubleshooting, and decorating techniques.', 'https://www.bakingbrotherhood.bake', 'Baking Brotherhood Forum'),
    (18, 'Pastry Perfection - Advanced baking community focusing on pastry arts, bread making, and professional techniques.', 'https://www.pastryperfection.art', 'Pastry Perfection Forum'),

    (19, 'Tennis Titans - Tennis community for players of all levels with court booking, partner matching, and technique tips.', 'https://www.tennistitans.sport', 'Tennis Titans Forum'),
    (19, 'Racquet Rebels - Competitive tennis community with tournament listings, coaching resources, and match analysis.', 'https://www.racquetrebels.com', 'Racquet Rebels Forum'),

    (20, 'Stargazers Society - Astronomy community sharing observation reports, equipment reviews, and celestial event notifications.', 'https://www.stargazerssociety.space', 'Stargazers Society Forum'),
    (20, 'Cosmic Explorers - Advanced astronomy community with astrophotography, telescope building, and research discussions.', 'https://www.cosmicexplorers.astro', 'Cosmic Explorers Forum'),

    (21, 'Skate Squad - Skateboarding community sharing trick tutorials, skate spot reviews, and video submissions.', 'https://www.skatesquad.board', 'Skate Squad Forum'),
    (21, 'Board Masters - Advanced skateboarding community focusing on street skating, vert, and skateboard modification.', 'https://www.boardmasters.sk8', 'Board Masters Forum'),

    (22, 'Mindful Moments - Meditation community with guided sessions, mindfulness techniques, and spiritual discussions.', 'https://www.mindfulmoments.zen', 'Mindful Moments Forum'),
    (22, 'Inner Peace Circle - Advanced meditation community exploring different traditions and deeper contemplative practices.', 'https://www.innerpeacecircle.org', 'Inner Peace Circle Forum'),

    (23, 'Anglers Anonymous - Fishing community sharing fishing spots, technique tips, and catch reports.', 'https://www.anglersanonymous.fish', 'Anglers Anonymous Forum'),
    (23, 'Trophy Hunters - Competitive fishing community with tournament information and advanced angling techniques.', 'https://www.trophyhunters.fishing', 'Trophy Hunters Forum');

INSERT INTO economic_detail (
    comment, cost_range_max, cost_range_min, currency,
    duration, estimated_cost, is_required, label,
    location_dependent, purchase_link, hobby_id
) VALUES
      ('Essential boots for hiking. Waterproof and durable.', 150.00, 100.00, 'EUR', 'N/A', 120.00, TRUE, 'Hiking Boots', FALSE, 'https://example.com/hiking-boots', 1),
      ('Sturdy backpack for day hikes.', 100.00, 60.00, 'EUR', 'N/A', 80.00, TRUE, 'Hiking Backpack', FALSE, 'https://example.com/hiking-backpack', 1),
      ('Optional hiking poles for better balance and joint support.', 70.00, 30.00, 'EUR', 'N/A', 50.00, FALSE, 'Hiking Poles', FALSE, 'https://example.com/hiking-poles', 1),
      ('Weather-appropriate clothing like layers, rain jackets, etc.', 200.00, 100.00, 'EUR', 'Seasonal', 150.00, TRUE, 'Clothing', TRUE, 'https://example.com/hiking-clothing', 1),
      ('Trail map or GPS device – optional for well-marked paths.', 50.00, 20.00, 'EUR', 'One-time', 35.00, FALSE, 'Navigation Tools', TRUE, 'https://example.com/navigation', 1),
      ('Standard tournament chess set with wooden pieces and board.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Chess Set', FALSE, 'https://example.com/chess-set', 2),
      ('Digital chess clock for timed games and tournaments.', 120.00, 60.00, 'EUR', 'N/A', 90.00, TRUE, 'Chess Clock', FALSE, 'https://example.com/chess-clock', 2),
      ('Chess notation pad for recording moves during games.', 15.00, 5.00, 'EUR', 'Consumable', 10.00, TRUE, 'Score Sheets', FALSE, 'https://example.com/score-sheets', 2),
      ('Comprehensive chess opening theory and strategy books.', 100.00, 30.00, 'EUR', 'N/A', 65.00, FALSE, 'Chess Books', FALSE, 'https://example.com/chess-books', 2),
      ('Premium chess software for analysis and training.', 200.00, 50.00, 'EUR', 'Annual', 125.00, FALSE, 'Chess Software', FALSE, 'https://example.com/chess-software', 2),
      ('Monthly membership for online chess platform with lessons.', 20.00, 10.00, 'EUR', 'Monthly', 15.00, FALSE, 'Online Chess Membership', FALSE, 'https://example.com/chess-membership', 2),
      ('Travel chess set for playing on the go.', 40.00, 15.00, 'EUR', 'N/A', 25.00, FALSE, 'Travel Chess Set', FALSE, 'https://example.com/travel-chess', 2),
      ('Entry fees for local chess tournaments and competitions.', 50.00, 20.00, 'EUR', 'Per Event', 35.00, FALSE, 'Tournament Entry Fees', TRUE, 'https://example.com/tournament-entry', 2),
      ('Entry-level DSLR or mirrorless camera body for beginners.', 1200.00, 400.00, 'EUR', 'N/A', 800.00, TRUE, 'Camera Body', FALSE, 'https://example.com/camera-body', 3),
      ('Standard 18-55mm kit lens for general photography.', 400.00, 150.00, 'EUR', 'N/A', 275.00, TRUE, 'Kit Lens (18-55mm)', FALSE, 'https://example.com/kit-lens', 3),
      ('High-speed SD cards for storing photos and videos.', 80.00, 30.00, 'EUR', 'N/A', 55.00, TRUE, 'Memory Cards', FALSE, 'https://example.com/memory-cards', 3),
      ('Camera bag or backpack for protection and transport.', 150.00, 50.00, 'EUR', 'N/A', 100.00, TRUE, 'Camera Bag', FALSE, 'https://example.com/camera-bag', 3),
      ('Sturdy tripod for stable shots and long exposures.', 300.00, 80.00, 'EUR', 'N/A', 190.00, FALSE, 'Tripod', FALSE, 'https://example.com/tripod', 3),
      ('Portrait lens for sharp subject focus and background blur.', 800.00, 300.00, 'EUR', 'N/A', 550.00, FALSE, 'Portrait Lens (85mm)', FALSE, 'https://example.com/portrait-lens', 3),
      ('Wide-angle lens for landscape and architecture photography.', 1000.00, 400.00, 'EUR', 'N/A', 700.00, FALSE, 'Wide Angle Lens (16-35mm)', FALSE, 'https://example.com/wide-lens', 3),
      ('External flash unit for better lighting control.', 400.00, 150.00, 'EUR', 'N/A', 275.00, FALSE, 'External Flash', FALSE, 'https://example.com/external-flash', 3),
      ('Polarizing and ND filters for enhanced image quality.', 150.00, 50.00, 'EUR', 'N/A', 100.00, FALSE, 'Camera Filters', FALSE, 'https://example.com/camera-filters', 3),
      ('Photo editing software subscription for post-processing.', 240.00, 120.00, 'EUR', 'Annual', 180.00, FALSE, 'Editing Software', FALSE, 'https://example.com/editing-software', 3),
      ('Extra batteries for extended shooting sessions.', 100.00, 40.00, 'EUR', 'N/A', 70.00, FALSE, 'Extra Batteries', FALSE, 'https://example.com/batteries', 3),
      ('Photography workshop or course for skill development.', 500.00, 200.00, 'EUR', 'One-time', 350.00, FALSE, 'Photography Course', TRUE, 'https://example.com/photo-course', 3),
      ('Cloud storage subscription for photo backup and sharing.', 120.00, 60.00, 'EUR', 'Annual', 90.00, FALSE, 'Cloud Storage', FALSE, 'https://example.com/cloud-storage', 3),
      ('Specialized climbing shoes for better grip and precision.', 120.00, 80.00, 'EUR', 'N/A', 100.00, TRUE, 'Climbing Shoes', FALSE, 'https://example.com/climbing-shoes', 4),
      ('Safety harness for belaying and climbing.', 80.00, 50.00, 'EUR', 'N/A', 65.00, TRUE, 'Climbing Harness', FALSE, 'https://example.com/climbing-harness', 4),
      ('Dynamic rope for outdoor climbing.', 200.00, 120.00, 'EUR', 'N/A', 160.00, FALSE, 'Climbing Rope', FALSE, 'https://example.com/climbing-rope', 4),
      ('Protection gear including carabiners and quickdraws.', 150.00, 80.00, 'EUR', 'N/A', 115.00, FALSE, 'Protection Gear', FALSE, 'https://example.com/protection-gear', 4),
      ('Protective helmet for outdoor climbing.', 100.00, 60.00, 'EUR', 'N/A', 80.00, FALSE, 'Climbing Helmet', FALSE, 'https://example.com/climbing-helmet', 4),

      ('Basic pottery tools including ribs, wire, and sponges.', 60.00, 30.00, 'EUR', 'N/A', 45.00, TRUE, 'Pottery Tools', FALSE, 'https://example.com/pottery-tools', 5),
      ('Clay for pottery projects - various types available.', 40.00, 20.00, 'EUR', 'Consumable', 30.00, TRUE, 'Pottery Clay', FALSE, 'https://example.com/pottery-clay', 5),
      ('Glazes for finishing pottery pieces.', 80.00, 40.00, 'EUR', 'Consumable', 60.00, FALSE, 'Pottery Glazes', FALSE, 'https://example.com/pottery-glazes', 5),
      ('Access to pottery wheel for throwing.', 30.00, 20.00, 'EUR', 'Per Session', 25.00, FALSE, 'Wheel Rental', TRUE, 'https://example.com/wheel-rental', 5),
      ('Kiln firing service for completed pieces.', 20.00, 10.00, 'EUR', 'Per Piece', 15.00, TRUE, 'Kiln Firing', TRUE, 'https://example.com/kiln-firing', 5),

      ('Acoustic guitar for beginners to intermediate players.', 400.00, 150.00, 'EUR', 'N/A', 275.00, TRUE, 'Acoustic Guitar', FALSE, 'https://example.com/acoustic-guitar', 6),
      ('Guitar picks in various thicknesses.', 15.00, 5.00, 'EUR', 'N/A', 10.00, TRUE, 'Guitar Picks', FALSE, 'https://example.com/guitar-picks', 6),
      ('Guitar tuner for keeping instrument in tune.', 50.00, 20.00, 'EUR', 'N/A', 35.00, TRUE, 'Guitar Tuner', FALSE, 'https://example.com/guitar-tuner', 6),
      ('Guitar case or gig bag for protection and transport.', 100.00, 40.00, 'EUR', 'N/A', 70.00, TRUE, 'Guitar Case', FALSE, 'https://example.com/guitar-case', 6),
      ('Guitar lessons with professional instructor.', 80.00, 40.00, 'EUR', 'Per Lesson', 60.00, FALSE, 'Guitar Lessons', TRUE, 'https://example.com/guitar-lessons', 6),

      ('High-quality chef knife for food preparation.', 150.00, 80.00, 'EUR', 'N/A', 115.00, TRUE, 'Chef Knife', FALSE, 'https://example.com/chef-knife', 7),
      ('Wooden cutting board for safe food prep.', 60.00, 30.00, 'EUR', 'N/A', 45.00, TRUE, 'Cutting Board', FALSE, 'https://example.com/cutting-board', 7),
      ('Essential pots and pans set for cooking.', 200.00, 100.00, 'EUR', 'N/A', 150.00, TRUE, 'Cookware Set', FALSE, 'https://example.com/cookware-set', 7),
      ('Quality ingredients for recipe experimentation.', 100.00, 50.00, 'EUR', 'Monthly', 75.00, TRUE, 'Ingredients', TRUE, 'https://example.com/ingredients', 7),
      ('Cooking classes to learn new techniques.', 120.00, 60.00, 'EUR', 'Per Class', 90.00, FALSE, 'Cooking Classes', TRUE, 'https://example.com/cooking-classes', 7),

      ('Acrylic paint set with primary and secondary colors.', 60.00, 30.00, 'EUR', 'N/A', 45.00, TRUE, 'Acrylic Paint Set', FALSE, 'https://example.com/acrylic-paint', 8),
      ('Various brushes for different painting techniques.', 50.00, 20.00, 'EUR', 'N/A', 35.00, TRUE, 'Paint Brushes', FALSE, 'https://example.com/paint-brushes', 8),
      ('Canvas boards and stretched canvases for painting.', 40.00, 15.00, 'EUR', 'Consumable', 25.00, TRUE, 'Canvas', FALSE, 'https://example.com/canvas', 8),
      ('Easel for comfortable painting position.', 80.00, 40.00, 'EUR', 'N/A', 60.00, FALSE, 'Art Easel', FALSE, 'https://example.com/art-easel', 8),
      ('Palette knife and palette for color mixing.', 30.00, 15.00, 'EUR', 'N/A', 22.50, TRUE, 'Painting Palette', FALSE, 'https://example.com/painting-palette', 8),

      ('Non-slip yoga mat for practice surface.', 80.00, 30.00, 'EUR', 'N/A', 55.00, TRUE, 'Yoga Mat', FALSE, 'https://example.com/yoga-mat', 9),
      ('Yoga blocks for support and alignment.', 40.00, 20.00, 'EUR', 'N/A', 30.00, FALSE, 'Yoga Blocks', FALSE, 'https://example.com/yoga-blocks', 9),
      ('Comfortable clothing for yoga practice.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Yoga Clothing', FALSE, 'https://example.com/yoga-clothing', 9),
      ('Yoga strap for improved flexibility.', 25.00, 15.00, 'EUR', 'N/A', 20.00, FALSE, 'Yoga Strap', FALSE, 'https://example.com/yoga-strap', 9),
      ('Monthly unlimited yoga class membership.', 120.00, 80.00, 'EUR', 'Monthly', 100.00, FALSE, 'Studio Membership', TRUE, 'https://example.com/yoga-membership', 9),

      ('Bamboo knitting needles in various sizes.', 40.00, 20.00, 'EUR', 'N/A', 30.00, TRUE, 'Knitting Needles', FALSE, 'https://example.com/knitting-needles', 10),
      ('Quality yarn in different weights and colors.', 60.00, 20.00, 'EUR', 'Per Project', 40.00, TRUE, 'Yarn', FALSE, 'https://example.com/yarn', 10),
      ('Stitch markers for pattern tracking.', 20.00, 10.00, 'EUR', 'N/A', 15.00, TRUE, 'Stitch Markers', FALSE, 'https://example.com/stitch-markers', 10),
      ('Scissors for cutting yarn.', 30.00, 15.00, 'EUR', 'N/A', 22.50, TRUE, 'Knitting Scissors', FALSE, 'https://example.com/knitting-scissors', 10),
      ('Knitting pattern books and guides.', 50.00, 20.00, 'EUR', 'N/A', 35.00, FALSE, 'Pattern Books', FALSE, 'https://example.com/pattern-books', 10),

      ('Road or hybrid bike suitable for beginners.', 800.00, 400.00, 'EUR', 'N/A', 600.00, TRUE, 'Bicycle', FALSE, 'https://example.com/bicycle', 11),
      ('Safety helmet meeting current standards.', 100.00, 40.00, 'EUR', 'N/A', 70.00, TRUE, 'Bike Helmet', FALSE, 'https://example.com/bike-helmet', 11),
      ('Basic bike repair tools and tire pump.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Bike Tools', FALSE, 'https://example.com/bike-tools', 11),
      ('Padded cycling shorts for comfort.', 60.00, 30.00, 'EUR', 'N/A', 45.00, FALSE, 'Cycling Clothing', FALSE, 'https://example.com/cycling-clothing', 11),
      ('Bike lights for safety during low light conditions.', 50.00, 25.00, 'EUR', 'N/A', 37.50, TRUE, 'Bike Lights', FALSE, 'https://example.com/bike-lights', 11),

      ('Physical books for reading collection.', 20.00, 10.00, 'EUR', 'Per Book', 15.00, TRUE, 'Books', FALSE, 'https://example.com/books', 12),
      ('E-reader device for digital books.', 150.00, 80.00, 'EUR', 'N/A', 115.00, FALSE, 'E-Reader', FALSE, 'https://example.com/e-reader', 12),
      ('Reading lamp for comfortable reading.', 60.00, 25.00, 'EUR', 'N/A', 42.50, FALSE, 'Reading Lamp', FALSE, 'https://example.com/reading-lamp', 12),
      ('Bookshelf for organizing book collection.', 200.00, 80.00, 'EUR', 'N/A', 140.00, FALSE, 'Bookshelf', FALSE, 'https://example.com/bookshelf', 12),
      ('Book club membership or subscription service.', 30.00, 15.00, 'EUR', 'Monthly', 22.50, FALSE, 'Book Subscription', FALSE, 'https://example.com/book-subscription', 12),

      ('Basic gardening tools including trowel, pruners, gloves.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Garden Tools', FALSE, 'https://example.com/garden-tools', 13),
      ('Seeds and seedlings for planting.', 50.00, 20.00, 'EUR', 'Seasonal', 35.00, TRUE, 'Seeds & Plants', FALSE, 'https://example.com/seeds-plants', 13),
      ('Quality soil and compost for plant health.', 60.00, 30.00, 'EUR', 'Seasonal', 45.00, TRUE, 'Soil & Compost', FALSE, 'https://example.com/soil-compost', 13),
      ('Watering can or hose for plant irrigation.', 40.00, 20.00, 'EUR', 'N/A', 30.00, TRUE, 'Watering Equipment', FALSE, 'https://example.com/watering-equipment', 13),
      ('Plant containers and pots for container gardening.', 100.00, 40.00, 'EUR', 'N/A', 70.00, FALSE, 'Plant Containers', FALSE, 'https://example.com/plant-containers', 13),

      ('Computer suitable for software development.', 1500.00, 800.00, 'EUR', 'N/A', 1150.00, TRUE, 'Development Computer', FALSE, 'https://example.com/dev-computer', 14),
      ('Code editor or IDE software license.', 200.00, 0.00, 'EUR', 'Annual', 100.00, FALSE, 'IDE Software', FALSE, 'https://example.com/ide-software', 14),
      ('Programming books and learning resources.', 100.00, 50.00, 'EUR', 'N/A', 75.00, FALSE, 'Programming Books', FALSE, 'https://example.com/programming-books', 14),
      ('Online course subscriptions for learning.', 50.00, 20.00, 'EUR', 'Monthly', 35.00, FALSE, 'Online Courses', FALSE, 'https://example.com/online-courses', 14),
      ('External monitor for better productivity.', 300.00, 150.00, 'EUR', 'N/A', 225.00, FALSE, 'External Monitor', FALSE, 'https://example.com/external-monitor', 14),

      ('Comfortable dancing shoes with proper sole.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Dance Shoes', FALSE, 'https://example.com/dance-shoes', 15),
      ('Flexible clothing suitable for movement.', 60.00, 30.00, 'EUR', 'N/A', 45.00, TRUE, 'Dance Clothing', FALSE, 'https://example.com/dance-clothing', 15),
      ('Dance classes with professional instructors.', 100.00, 60.00, 'EUR', 'Per Month', 80.00, FALSE, 'Dance Classes', TRUE, 'https://example.com/dance-classes', 15),
      ('Practice mirror for home training.', 120.00, 60.00, 'EUR', 'N/A', 90.00, FALSE, 'Practice Mirror', FALSE, 'https://example.com/practice-mirror', 15),
      ('Music streaming service for practice songs.', 15.00, 10.00, 'EUR', 'Monthly', 12.50, FALSE, 'Music Streaming', FALSE, 'https://example.com/music-streaming', 15),

      ('Essential hand tools including saws, chisels, planes.', 300.00, 150.00, 'EUR', 'N/A', 225.00, TRUE, 'Hand Tools', FALSE, 'https://example.com/hand-tools', 16),
      ('Various wood types for different projects.', 100.00, 50.00, 'EUR', 'Per Project', 75.00, TRUE, 'Wood Materials', FALSE, 'https://example.com/wood-materials', 16),
      ('Sandpaper and finishing supplies.', 50.00, 25.00, 'EUR', 'Consumable', 37.50, TRUE, 'Finishing Supplies', FALSE, 'https://example.com/finishing-supplies', 16),
      ('Workbench for woodworking projects.', 400.00, 200.00, 'EUR', 'N/A', 300.00, FALSE, 'Workbench', FALSE, 'https://example.com/workbench', 16),
      ('Safety equipment including goggles and dust masks.', 60.00, 30.00, 'EUR', 'N/A', 45.00, TRUE, 'Safety Equipment', FALSE, 'https://example.com/safety-equipment', 16),

      ('Swimsuit suitable for lap swimming.', 60.00, 30.00, 'EUR', 'N/A', 45.00, TRUE, 'Swimsuit', FALSE, 'https://example.com/swimsuit', 17),
      ('Swimming goggles for clear underwater vision.', 40.00, 15.00, 'EUR', 'N/A', 27.50, TRUE, 'Swimming Goggles', FALSE, 'https://example.com/swimming-goggles', 17),
      ('Swimming cap for hair protection.', 20.00, 10.00, 'EUR', 'N/A', 15.00, FALSE, 'Swimming Cap', FALSE, 'https://example.com/swimming-cap', 17),
      ('Pool membership or day passes.', 80.00, 40.00, 'EUR', 'Monthly', 60.00, TRUE, 'Pool Access', TRUE, 'https://example.com/pool-access', 17),
      ('Swimming lessons for technique improvement.', 60.00, 40.00, 'EUR', 'Per Lesson', 50.00, FALSE, 'Swimming Lessons', TRUE, 'https://example.com/swimming-lessons', 17),

      ('Essential baking tools including mixing bowls and measuring cups.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Baking Tools', FALSE, 'https://example.com/baking-tools', 18),
      ('Stand mixer for efficient mixing and kneading.', 400.00, 200.00, 'EUR', 'N/A', 300.00, FALSE, 'Stand Mixer', FALSE, 'https://example.com/stand-mixer', 18),
      ('Baking pans and sheets in various sizes.', 100.00, 50.00, 'EUR', 'N/A', 75.00, TRUE, 'Baking Pans', FALSE, 'https://example.com/baking-pans', 18),
      ('Quality ingredients including flour, sugar, vanilla.', 60.00, 30.00, 'EUR', 'Monthly', 45.00, TRUE, 'Baking Ingredients', FALSE, 'https://example.com/baking-ingredients', 18),
      ('Baking classes for advanced techniques.', 100.00, 60.00, 'EUR', 'Per Class', 80.00, FALSE, 'Baking Classes', TRUE, 'https://example.com/baking-classes', 18),

      ('Tennis racket suitable for skill level.', 200.00, 80.00, 'EUR', 'N/A', 140.00, TRUE, 'Tennis Racket', FALSE, 'https://example.com/tennis-racket', 19),
      ('Tennis balls for practice and matches.', 20.00, 10.00, 'EUR', 'Consumable', 15.00, TRUE, 'Tennis Balls', FALSE, 'https://example.com/tennis-balls', 19),
      ('Tennis shoes with proper court grip.', 120.00, 60.00, 'EUR', 'N/A', 90.00, TRUE, 'Tennis Shoes', FALSE, 'https://example.com/tennis-shoes', 19),
      ('Tennis clothing suitable for active play.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Tennis Clothing', FALSE, 'https://example.com/tennis-clothing', 19),
      ('Court rental fees for practice and matches.', 40.00, 20.00, 'EUR', 'Per Hour', 30.00, TRUE, 'Court Rental', TRUE, 'https://example.com/court-rental', 19),

      ('Beginner telescope for stargazing.', 400.00, 200.00, 'EUR', 'N/A', 300.00, TRUE, 'Telescope', FALSE, 'https://example.com/telescope', 20),
      ('Star charts and constellation guides.', 40.00, 20.00, 'EUR', 'N/A', 30.00, TRUE, 'Star Charts', FALSE, 'https://example.com/star-charts', 20),
      ('Red flashlight for night vision preservation.', 30.00, 15.00, 'EUR', 'N/A', 22.50, TRUE, 'Red Flashlight', FALSE, 'https://example.com/red-flashlight', 20),
      ('Astronomy apps and software.', 50.00, 20.00, 'EUR', 'Annual', 35.00, FALSE, 'Astronomy Software', FALSE, 'https://example.com/astronomy-software', 20),
      ('Planetarium membership for continued learning.', 60.00, 40.00, 'EUR', 'Annual', 50.00, FALSE, 'Planetarium Membership', TRUE, 'https://example.com/planetarium-membership', 20),

      ('Complete skateboard suitable for beginners.', 150.00, 80.00, 'EUR', 'N/A', 115.00, TRUE, 'Skateboard', FALSE, 'https://example.com/skateboard', 21),
      ('Protective gear including helmet, knee and elbow pads.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Protective Gear', FALSE, 'https://example.com/protective-gear', 21),
      ('Skate shoes with proper grip and durability.', 80.00, 50.00, 'EUR', 'N/A', 65.00, TRUE, 'Skate Shoes', FALSE, 'https://example.com/skate-shoes', 21),
      ('Skateboard tool for adjustments and maintenance.', 20.00, 10.00, 'EUR', 'N/A', 15.00, TRUE, 'Skate Tool', FALSE, 'https://example.com/skate-tool', 21),
      ('Skateboarding lessons for proper technique.', 60.00, 40.00, 'EUR', 'Per Lesson', 50.00, FALSE, 'Skateboard Lessons', TRUE, 'https://example.com/skateboard-lessons', 21),

      ('Comfortable meditation cushion or mat.', 60.00, 30.00, 'EUR', 'N/A', 45.00, FALSE, 'Meditation Cushion', FALSE, 'https://example.com/meditation-cushion', 22),
      ('Meditation app subscription for guided sessions.', 100.00, 60.00, 'EUR', 'Annual', 80.00, FALSE, 'Meditation App', FALSE, 'https://example.com/meditation-app', 22),
      ('Books on meditation techniques and philosophy.', 50.00, 20.00, 'EUR', 'N/A', 35.00, FALSE, 'Meditation Books', FALSE, 'https://example.com/meditation-books', 22),
      ('Incense or essential oils for ambiance.', 40.00, 20.00, 'EUR', 'Consumable', 30.00, FALSE, 'Aromatherapy', FALSE, 'https://example.com/aromatherapy', 22),
      ('Meditation retreat or workshop fees.', 200.00, 100.00, 'EUR', 'Per Event', 150.00, FALSE, 'Meditation Retreats', TRUE, 'https://example.com/meditation-retreats', 22),

      ('Fishing rod suitable for target fish species.', 100.00, 50.00, 'EUR', 'N/A', 75.00, TRUE, 'Fishing Rod', FALSE, 'https://example.com/fishing-rod', 23),
      ('Fishing reel to match rod specifications.', 80.00, 40.00, 'EUR', 'N/A', 60.00, TRUE, 'Fishing Reel', FALSE, 'https://example.com/fishing-reel', 23),
      ('Tackle box with hooks, sinkers, and lures.', 60.00, 30.00, 'EUR', 'N/A', 45.00, TRUE, 'Tackle Box', FALSE, 'https://example.com/tackle-box', 23),
      ('Fishing license as required by local regulations.', 50.00, 20.00, 'EUR', 'Annual', 35.00, TRUE, 'Fishing License', TRUE, 'https://example.com/fishing-license', 23),
      ('Cooler for keeping catch fresh.', 80.00, 40.00, 'EUR', 'N/A', 60.00, FALSE, 'Fishing Cooler', FALSE, 'https://example.com/fishing-cooler', 23);