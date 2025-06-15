CREATE TABLE place (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(500),
    google_ratings_count INT DEFAULT 0,
    google_rating DECIMAL(2,1) DEFAULT 0.0,
    app_ratings_count INT DEFAULT 0,
    app_rating DECIMAL(2,1) DEFAULT 0.0,
    image_url TEXT
);

INSERT INTO place (name, address, google_ratings_count, google_rating, app_ratings_count, app_rating, image_url) VALUES
('Charlotte Menora', 'Plac Grzybowski 2, Warszawa', 1300, 4.5, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipPJqMT7jq4zv6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v6v=w408-h306-k-no'),
('Aioli', 'ul. Nowy Świat 22, Warszawa', 850, 4.3, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipNmnOIiDftCahCahCahCahCahCahCahCahCahCahCahCah=w408-h306-k-no'),
('Restauracja Stary Dom', 'ul. Puławska 104/106, Warszawa', 900, 4.4, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipP6W6m_MmE7ACACA=w408-h306-k-no'),
('Między Nami Bistro', 'ul. Grójecka 194, Warszawa', 230, 4.2, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipOLdVN9k9v_DD=w408-h306-k-no'),
('Delizia Pizza & Pasta', 'ul. Żurawia 6, Warszawa', 500, 4.1, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipNXPe9b1GL4KD=w408-h306-k-no'),
('Kawiarnia Relaks', 'ul. Marszałkowska 10, Warszawa', 150, 4.0, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipXwxyz=w408-h306-k-no'),
('Bar Mleczny Prasowy', 'ul. Złota 2, Warszawa', 310, 3.9, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipZYX123=w408-h306-k-no'),
('Cafe Bristol', 'Krakowskie Przedmieście 42/44, Warszawa', 850, 4.6, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipBristolPhoto=w408-h306-k-no'),
('Pizzeria Napoli', 'ul. Chmielna 15, Warszawa', 400, 4.3, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipNapoli=w408-h306-k-no'),
('Restauracja Warszawska', 'ul. Marszałkowska 99/101, Warszawa', 270, 4.2, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipWarszawska=w408-h306-k-no'),
('Sushi Time', 'ul. Nowy Świat 30, Warszawa', 190, 4.5, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipSushiTime=w408-h306-k-no'),
('Bar Studio', 'ul. Emilii Plater 25, Warszawa', 120, 4.0, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipBarStudio=w408-h306-k-no'),
('Bistro Mokotów', 'ul. Rakowiecka 10, Warszawa', 140, 4.1, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipBistroMokotow=w408-h306-k-no'),
('Cafe Kultura', 'ul. Chmielna 20, Warszawa', 210, 4.3, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipCafeKultura=w408-h306-k-no'),
('Pijalnia Czekolady', 'ul. Marszałkowska 111, Warszawa', 330, 4.4, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipPijalnia=w408-h306-k-no'),
('Kebab King', 'ul. Świętokrzyska 12, Warszawa', 520, 4.0, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipKebabKing=w408-h306-k-no'),
('Restauracja Zielona', 'ul. Wspólna 15, Warszawa', 110, 4.1, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipZielona=w408-h306-k-no'),
('Bar Prowokacja', 'ul. Puławska 45, Warszawa', 90, 3.8, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipProwokacja=w408-h306-k-no'),
('Trattoria Roma', 'ul. Koszykowa 30, Warszawa', 220, 4.3, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipTrattoriaRoma=w408-h306-k-no'),
('Bar Studio Mokotów', 'ul. Domaniewska 41, Warszawa', 130, 4.0, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipBarStudioMokotow=w408-h306-k-no'),
('Cukiernia Słodka', 'ul. Filtrowa 62, Warszawa', 180, 4.2, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipCukierniaSlodka=w408-h306-k-no'),
('Pub Warszawa', 'ul. Marszałkowska 84, Warszawa', 260, 4.1, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipPubWarszawa=w408-h306-k-no'),
('Piekarnia Rodzinna', 'ul. Grochowska 50, Warszawa', 210, 4.3, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipPiekarniaRodzinna=w408-h306-k-no'),
('Sushi Bar', 'ul. Żurawia 8, Warszawa', 190, 4.4, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipSushiBar=w408-h306-k-no'),
('Kawiarnia Nowa', 'ul. Wilcza 15, Warszawa', 170, 4.2, 0, 0, 'https://lh5.googleusercontent.com/p/AF1QipKawiarniaNowa=w408-h306-k-no');


INSERT INTO place (name, address, google_ratings_count, google_rating, app_ratings_count, app_rating, image_url) VALUES
('Cafe Kawiarnia', 'ul. Marszałkowska 10, Warszawa', 130, 4.4, 8, 4.2, 'https://images.unsplash.com/photo-1509042239860-f550ce710b93'),
('Bar Mleczny Smak', 'ul. Nowy Świat 22, Warszawa', 95, 4.0, 5, 3.8, 'https://images.unsplash.com/photo-1525610553991-2bede1a236e2'),
('Restauracja Zielony Ogród', 'ul. Krakowskie Przedmieście 45, Warszawa', 210, 4.7, 15, 4.5, 'https://images.unsplash.com/photo-1478145046317-39f10e56b5e9'),
('Pizzeria Napoli', 'ul. Chmielna 18, Warszawa', 340, 4.3, 20, 4.1, 'https://images.unsplash.com/photo-1548365328-6426e8ec5a6e'),
('Bistro Przytulne Miejsce', 'ul. Żurawia 32, Warszawa', 88, 4.1, 6, 3.9, 'https://images.unsplash.com/photo-1517248135467-4c7edcad34c4'),
('Klub Koktajlowy', 'ul. Foksal 16, Warszawa', 120, 4.5, 10, 4.4, 'https://images.unsplash.com/photo-1532634726-7a3b86f7b7c9'),
('Śniadaniownia', 'ul. Puławska 91, Warszawa', 140, 4.6, 12, 4.3, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
('Lodziarnia Słodka Chwila', 'ul. Aleje Jerozolimskie 29, Warszawa', 180, 4.8, 25, 4.7, 'https://images.unsplash.com/photo-1501594907352-04cda38ebc29'),
('Restauracja Ocean Smaków', 'ul. Grzybowska 5, Warszawa', 200, 4.2, 14, 4.0, 'https://images.unsplash.com/photo-1516685018646-54958b7918a0'),
('Kawiarnia Retro', 'ul. Hoża 21, Warszawa', 70, 4.0, 4, 3.7, 'https://images.unsplash.com/photo-1515442261605-66bb0db93215'),
('Pub Stary Browar', 'ul. Próżna 12, Warszawa', 230, 4.4, 18, 4.2, 'https://images.unsplash.com/photo-1484981138541-3d074aa97716'),
('Bar Vegan Love', 'ul. Wilcza 43, Warszawa', 90, 4.1, 7, 4.0, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
('Restauracja Sushi Zen', 'ul. Złota 59, Warszawa', 160, 4.7, 20, 4.6, 'https://images.unsplash.com/photo-1549887534-1e445a7b6c24'),
('Kawiarnia Czarna Fala', 'ul. Marszałkowska 140, Warszawa', 110, 4.3, 9, 4.1, 'https://images.unsplash.com/photo-1495474472287-4d71bcdd2085'),
('Piekarnia Chlebowa', 'ul. Elektoralna 17, Warszawa', 95, 4.2, 6, 4.0, 'https://images.unsplash.com/photo-1470337458703-46ad1756a187'),
('Restauracja Urokliwa', 'ul. Wspólna 55, Warszawa', 140, 4.5, 10, 4.3, 'https://images.unsplash.com/photo-1504674900247-0877df9cc836'),
('Bar Sportowy', 'ul. Grochowska 60, Warszawa', 130, 4.0, 8, 3.9, 'https://images.unsplash.com/photo-1509042239860-f550ce710b93'),
('Kawiarnia Zielona', 'ul. Świętokrzyska 20, Warszawa', 75, 4.2, 5, 4.1, 'https://images.unsplash.com/photo-1517248135467-4c7edcad34c4'),
('Restauracja Włoska', 'ul. Ordynacka 12, Warszawa', 190, 4.6, 22, 4.4, 'https://images.unsplash.com/photo-1543352634-30cc7a0e51a1'),
('Bistro Na Rogu', 'ul. Tamka 34, Warszawa', 100, 4.1, 7, 3.9, 'https://images.unsplash.com/photo-1525610553991-2bede1a236e2'),
('Kawiarnia Artystyczna', 'ul. Bracka 15, Warszawa', 85, 4.3, 5, 4.2, 'https://images.unsplash.com/photo-1478145046317-39f10e56b5e9'),
('Bar Jazzowy', 'ul. Chłodna 8, Warszawa', 120, 4.4, 9, 4.3, 'https://images.unsplash.com/photo-1549887534-1e445a7b6c24'),
('Restauracja Złoty Smok', 'ul. Wolska 25, Warszawa', 210, 4.7, 15, 4.6, 'https://images.unsplash.com/photo-1525610553991-2bede1a236e2'),
('Pizzeria Bella Italia', 'ul. Grójecka 48, Warszawa', 320, 4.5, 18, 4.3, 'https://images.unsplash.com/photo-1509042239860-f550ce710b93'),
('Kawiarnia Nastrojowa', 'ul. Krucza 30, Warszawa', 90, 4.2, 6, 4.0, 'https://images.unsplash.com/photo-1517248135467-4c7edcad34c4');


CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE place_category (
    place_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (place_id, category_id),
    FOREIGN KEY (place_id) REFERENCES place(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
);


INSERT INTO category (name) VALUES
('Kawa & Chill'),
('Brunch Vibes'),
('Cocktail Bar'),
('Street Food'),
('Vegan Love'),
('Foodie Heaven'),
('Sweet Spot'),
('Healthy Bites'),
('Retro Cafe'),
('Jazz & Drinks'),
('Pizza Time'),
('Breakfast Goals'),
('Late Night Spot'),
('Craft Beer'),
('Sushi Time'),
('Cozy Corner'),
('Power Lunch'),
('Dessert Place'),
('Hipster Hangout'),
('Outdoor Seating'),
('Family Friendly'),
('Romantic Dinner'),
('Live Music'),
('Coffee Lovers'),
('Gourmet Eats');



-- Кожен з перших 25 закладів отримає по одній категорії
INSERT INTO place_category (place_id, category_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25);



-- Заклад 26
INSERT INTO place_category (place_id, category_id) VALUES
(26, 1),
(26, 5),
(26, 12);

-- Заклад 27
INSERT INTO place_category (place_id, category_id) VALUES
(27, 2),
(27, 7);

-- Заклад 28
INSERT INTO place_category (place_id, category_id) VALUES
(28, 3),
(28, 11),
(28, 18);

-- Заклад 29
INSERT INTO place_category (place_id, category_id) VALUES
(29, 4),
(29, 9);

-- Заклад 30
INSERT INTO place_category (place_id, category_id) VALUES
(30, 6),
(30, 14),
(30, 20);

-- Заклад 31
INSERT INTO place_category (place_id, category_id) VALUES
(31, 8),
(31, 15);

-- Заклад 32
INSERT INTO place_category (place_id, category_id) VALUES
(32, 10),
(32, 19),
(32, 24);

-- Заклад 33
INSERT INTO place_category (place_id, category_id) VALUES
(33, 13),
(33, 21);

-- Заклад 34
INSERT INTO place_category (place_id, category_id) VALUES
(34, 16),
(34, 22),
(34, 25);

-- Заклад 35
INSERT INTO place_category (place_id, category_id) VALUES
(35, 17),
(35, 23);

-- Заклад 36
INSERT INTO place_category (place_id, category_id) VALUES
(36, 1),
(36, 6),
(36, 14);

-- Заклад 37
INSERT INTO place_category (place_id, category_id) VALUES
(37, 2),
(37, 8);

-- Заклад 38
INSERT INTO place_category (place_id, category_id) VALUES
(38, 3),
(38, 11),
(38, 19);

-- Заклад 39
INSERT INTO place_category (place_id, category_id) VALUES
(39, 4),
(39, 7);

-- Заклад 40
INSERT INTO place_category (place_id, category_id) VALUES
(40, 5),
(40, 10),
(40, 20);

-- Заклад 41
INSERT INTO place_category (place_id, category_id) VALUES
(41, 9),
(41, 15);

-- Заклад 42
INSERT INTO place_category (place_id, category_id) VALUES
(42, 12),
(42, 18),
(42, 24);

-- Заклад 43
INSERT INTO place_category (place_id, category_id) VALUES
(43, 13),
(43, 22);

-- Заклад 44
INSERT INTO place_category (place_id, category_id) VALUES
(44, 16),
(44, 21),
(44, 25);

-- Заклад 45
INSERT INTO place_category (place_id, category_id) VALUES
(45, 17),
(45, 23);

-- Заклад 46
INSERT INTO place_category (place_id, category_id) VALUES
(46, 1),
(46, 5),
(46, 12);

-- Заклад 47
INSERT INTO place_category (place_id, category_id) VALUES
(47, 2),
(47, 8);

-- Заклад 48
INSERT INTO place_category (place_id, category_id) VALUES
(48, 3),
(48, 11),
(48, 19);

-- Заклад 49
INSERT INTO place_category (place_id, category_id) VALUES
(49, 4),
(49, 7);

-- Заклад 50
INSERT INTO place_category (place_id, category_id) VALUES
(50, 5),
(50, 10),
(50, 20);


CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    place_id INT NOT NULL,
    author VARCHAR(100) NOT NULL,
    text TEXT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (place_id) REFERENCES place(id) ON DELETE CASCADE
);


INSERT INTO comment (place_id, author, text, rating) VALUES
(1, 'AniaK', 'Super miejsce na kawę i chill!', 5),
(2, 'Mateusz123', 'Fajny brunch, polecam!', 4),
(3, 'Kasia_89', 'Koktajle świetne, ale muzyka trochę za głośna.', 4),
(1, 'TomekW', 'Może być, ale ceny trochę wysokie.', 3),
(5, 'MartaZ', 'Wegańskie opcje są pyszne!', 5);



ALTER TABLE category
ADD COLUMN image_url VARCHAR(512),
ADD COLUMN author VARCHAR(255),
ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN deleted BOOLEAN DEFAULT FALSE;


CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
   RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER trigger_update_timestamp
BEFORE UPDATE ON category
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();



ALTER TABLE category
ADD COLUMN description VARCHAR(512)
