-- Insert data into City table
INSERT INTO city (name) VALUES
                            ('Hyderabad'),
                            ('Bangalore');

-- Insert data into Theater table
INSERT INTO theater (name, city_id) VALUES
                                        ('PVR', 1),
                                        ('Sashikala', 1),
                                        ('Sai Ranga', 1),
                                        ('Thulasi', 2),
                                        ('Narasimha', 2);

-- Insert data into Movie table
INSERT INTO movie (title) VALUES
                              ('Mission Impossible'),
                              ('Fast and Furious'),
                              ('Kalki');

INSERT INTO show (id, movie_id, theater_id, show_time, show_date) VALUES
                                                                      (1, 1, 1, '14:00:00', '2023-07-22'),
                                                                      (2, 2, 1, '17:30:00', '2023-07-23'),
                                                                      (3, 3, 1, '20:00:00', '2023-07-24'),
                                                                      (4, 1, 1, '10:00:00', '2023-07-25'),
                                                                      (5, 2, 1, '14:00:00', '2023-07-26'),
                                                                      (6, 3, 1, '17:30:00', '2023-07-27'),
                                                                      (7, 1, 2, '15:00:00', '2023-07-22'),
                                                                      (8, 2, 2, '19:30:00', '2023-07-23'),
                                                                      (9, 3, 2, '14:00:00', '2023-07-24'),
                                                                      (10, 1, 2, '12:00:00', '2023-07-25'),
                                                                      (11, 2, 3, '16:30:00', '2023-07-22'),
                                                                      (12, 3, 3, '20:00:00', '2023-07-23'),
                                                                      (13, 1, 3, '15:30:00', '2023-07-24'),
                                                                      (14, 2, 3, '18:00:00', '2023-07-25'),
                                                                      (15, 1, 4, '13:30:00', '2023-07-22'),
                                                                      (16, 2, 4, '17:00:00', '2023-07-23'),
                                                                      (17, 3, 4, '20:30:00', '2023-07-24'),
                                                                      (18, 1, 4, '12:30:00', '2023-07-25'),
                                                                      (19, 2, 5, '14:30:00', '2023-07-22'),
                                                                      (20, 3, 5, '18:30:00', '2023-07-23'),
                                                                      (21, 1, 5, '11:00:00', '2023-07-24'),
                                                                      (22, 2, 5, '16:00:00', '2023-07-25');


-- Insert data into SeatRow table
INSERT INTO seat_row (id, theater_id, row_number) VALUES
                                                      (1, 1, 'A'),
                                                      (2, 1, 'B'),
                                                      (3, 1, 'C'),
                                                      (4, 1, 'X'),
                                                      (5, 1, 'Y'),
                                                      (6, 2, 'P'),
                                                      (7, 2, 'Q'),
                                                      (8, 3, 'M'),
                                                      (9, 3, 'N'),
                                                      (10, 5, 'I'),
                                                      (11, 5, 'J');


INSERT INTO seat (id, theater_id, seat_row_id, show_id, seat_number, price, version, booked) VALUES
                                                                                                 (1, 1, 1, 1, '1', 100, 1, false),
                                                                                                 (2, 1, 1, 1, '2', 100, 1, false),
                                                                                                 (3, 1, 1, 1, '3', 100, 1, false),
                                                                                                 (4, 1, 2, 1, '1', 120, 1, false),
                                                                                                 (5, 1, 2, 1, '2', 120, 1, false),
                                                                                                 (6, 1, 2, 1, '3', 120, 1, false),
                                                                                                 (31, 4, 4, 4, '1', 150, 1, false),
                                                                                                 (32, 4, 4, 4, '2', 150, 1, false),
                                                                                                 (33, 4, 4, 4, '3', 150, 1, false),
                                                                                                 (34, 4, 5, 4, '1', 130, 1, false),
                                                                                                 (35, 4, 5, 4, '2', 130, 1, false),
                                                                                                 (36, 4, 5, 4, '3', 130, 1, false),
                                                                                                 (61, 2, 6, 2, '1', 90, 1, false),
                                                                                                 (62, 2, 6, 2, '2', 90, 1, false),
                                                                                                 (63, 2, 6, 2, '3', 90, 1, false),
                                                                                                 (64, 2, 7, 2, '1', 80, 1, false),
                                                                                                 (65, 2, 7, 2, '2', 80, 1, false),
                                                                                                 (66, 2, 7, 2, '3', 80, 1, false),
                                                                                                 (91, 3, 8, 3, '1', 110, 1, false),
                                                                                                 (92, 3, 8, 3, '2', 110, 1, false),
                                                                                                 (93, 3, 8, 3, '3', 110, 1, false),
                                                                                                 (94, 3, 9, 3, '1', 100, 1, false),
                                                                                                 (95, 3, 9, 3, '2', 100, 1, false),
                                                                                                 (96, 3, 9, 3, '3', 100, 1, false),
                                                                                                 (121, 5, 10, 5, '1', 140, 1, false),
                                                                                                 (122, 5, 10, 5, '2', 140, 1, false),
                                                                                                 (123, 5, 10, 5, '3', 140, 1, false),
                                                                                                 (124, 5, 11, 5, '1', 120, 1, false),
                                                                                                 (125, 5, 11, 5, '2', 120, 1, false),
                                                                                                 (126, 5, 11, 5, '3', 120, 1, false);


-- Insert data into Offer table
INSERT INTO offer (id, offer_type, offer_name, description, discount_amount, discount_type, start_date, end_date, theater_id) VALUES
                                                                                                                                  (1, 'THIRD_TICKET_DISCOUNT', 'Offer 1', '50% discount on the third ticket', 50, 'PERCENTAGE', '2023-07-22T00:00:00', '2023-08-22T00:00:00', 1),
                                                                                                                                  (2, 'AFTERNOON_SHOW_DISCOUNT', 'Offer 2', '20% discount on tickets booked for the afternoon show', 20, 'PERCENTAGE', '2023-07-22T00:00:00', '2023-09-22T00:00:00', 2);


-- Insert data into Customer table
INSERT INTO customer (id, name, email) VALUES
                                           (1, 'John Doe', 'john@example.com'),
                                           (2, 'Jane Smith', 'jane@example.com');


COMMIT;
