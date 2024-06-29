
Use catalog;

INSERT INTO movies ( movie_name, main_actors, category, description_, time_, year_,image_location,director,price)
VALUES
( 'Moana', 'Aulii Cravalho, Dwayne Johnson', 'Family', 'Disney animated film about a brave Polynesian girl who sets sail on a daring mission to save her people, with the help of the demigod Maui', '1h47m', 2016,'1.jpg','Ron',40),
('Anyone But You', 'Sydney Sweeney, Glen Powell', 'Romance', 'romantic comedy about two ex-lovers who must pretend to be a couple again for a wedding', '1h44m', 2023,'2.png','Mias',40),
('Cruella', 'Emma Stone, Emma Thompson', 'Comedy', 'A stylish and rebellious origin story of Cruella de Vil, set in 1970s London amidst the punk rock revolution', '2h14m', 2021,'3.jpg','Sami',50),
('Divergent', 'Shailene Woodley, Theo James', 'Action', 'In a dystopian future, a young woman discovers she is a Divergent and must uncover the secrets behind her societys facade', '2h19m', 2014,'4.jpg','Aisha',30),
('Spider-Man: No Way Home', 'Tom Holland, Zendaya, Benedict Cumberbatch', 'Action', 'Spider-Man seeks Doctor Stranges help to restore his secret identity, unleashing multiverse chaos', '2h30m', 2021,'5.jpg','Jan',10);

INSERT INTO screening (movie_id, time_, date_, room_number)
VALUES
(1, '17:00', '02/07/2024', 5),
(1, '19:30', '02/07/2024', 2),
(1, '22:00', '02/07/2024', 1),
(2, '17:00', '02/07/2024', 1),
(2, '20:00', '02/07/2024', 4),
(2, '23:00', '02/07/2024', 5),
(3, '17:00', '02/07/2024', 4),
(3, '19:00', '02/07/2024', 3),
(4, '16:00', '02/07/2024', 2);