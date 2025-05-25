CREATE TABLE Movie (
                       movie_id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(50) NOT NULL,
                       genre VARCHAR(30) NOT NULL,
                       rating FLOAT  NOT NULL,
                       director VARCHAR(50)  NOT NULL,
                       cast VARCHAR(50)  NOT NULL,
                       summary  VARCHAR(100)  NOT NULL,
                       age_limit INT NOT NULL,
                       duration INT NOT NULL,
                       release_date DATE NOT NULL
);

CREATE TABLE Schedule (
                          schedule_id INT PRIMARY KEY AUTO_INCREMENT,
                          movie_id INT NOT NULL,
                          screen_date DATE NOT NULL,
                          start_time TIME NOT NULL,
                          end_time TIME NOT NULL,
                          FOREIGN KEY (movie_id) REFERENCES Movie(movie_id)
                              ON DELETE CASCADE
);

CREATE TABLE Seat (
                      seat_id INT PRIMARY KEY AUTO_INCREMENT,
                      schedule_id INT NOT NULL,
                      seat_code VARCHAR(5) NOT NULL,
                      is_reserved BOOLEAN DEFAULT FALSE,
                      FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id)
                          ON DELETE CASCADE
);

CREATE TABLE Reservation (
                             reservation_id VARCHAR(20) PRIMARY KEY,
                             schedule_id INT NOT NULL,
                             reservation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             total_person INT NOT NULL,
                             total_price INT  NOT NULL,
                             status  VARCHAR(20)  NOT NULL,
                             FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id)
                                 ON DELETE CASCADE
);

CREATE TABLE Reservation_Seat (
                                  reservation_seat_id INT PRIMARY KEY AUTO_INCREMENT,
                                  reservation_id VARCHAR(20) NOT NULL,
                                  seat_id INT NOT NULL,
                                  FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id)
                                      ON DELETE CASCADE,
                                  FOREIGN KEY (seat_id) REFERENCES Seat(seat_id)
                                      ON DELETE CASCADE
);

CREATE TABLE Payment (
                         payment_id INT PRIMARY KEY AUTO_INCREMENT,
                         reservation_id VARCHAR(20) NOT NULL UNIQUE,
                         use_cash_receipt BOOLEAN DEFAULT FALSE ,
                         phone_number VARCHAR(15) NULL,
                         payment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (reservation_id) REFERENCES Reservation(reservation_id)
                             ON DELETE CASCADE
);

INSERT INTO Movie (
    movie_id, title, genre, rating, director, cast, summary, duration, age_limit, release_date
) VALUES
      (1, '서울의 봄', '정치', 9.1, '김성수',
       '황정민, 정우성, 이성민, 박해준, 김성균',
       '1979년 12.12 군사반란 당시, 혼란한 정국 속에서 권력을 잡으려는 신군부와 이를 막으려는 인물들의 치열한 대립을 그린 정치 드라마.',
       141, 15, '2023-11-22'),

      (2, '파묘', '스릴러', 8.7, '장재현',
       '최민식, 김고은, 유해진',
       '한 무속인이 의뢰를 받아 오래된 무덤을 파헤치면서 벌어지는 미스터리한 사건들을 그린 스릴러.',
       130, 15, '2024-02-22'),

      (3, '범죄도시4', '범죄', 8.5, '이상용',
       '마동석, 김무열, 박지환',
       '형사 마석도와 그의 팀이 국제 범죄 조직을 소탕하는 과정을 그린 범죄 액션 시리즈의 네 번째 작품.',
       110, 15, '2024-04-24'),

      (4, '야당', '범죄', 9.4, '김성훈',
       '하정우, 조진웅, 이엘',
       '정치권의 부패와 권력 투쟁 속에서 진실을 밝히려는 야당 정치인의 고군분투를 그린 정치 스릴러.',
       125, 15, '2025-04-16'),

      (5, '거룩한 밤: 데몬 헌터스', '액션', 8.9, '박훈정',
       '김남길, 이솜, 박성웅',
       '악마를 사냥하는 데몬 헌터들의 비밀스러운 임무와 그 속에서 벌어지는 초자연적 사건들을 그린 오컬트 액션.',
       118, 15, '2025-04-30');


INSERT INTO Schedule (schedule_id, movie_id, screen_date, start_time, end_time)
VALUES
    (1, 1, '2025-05-20', '10:00:00', '12:21:00'),
    (2, 2, '2025-05-20', '13:00:00', '15:10:00'),

    (3, 3, '2025-05-21', '11:00:00', '12:50:00'),
    (4, 4, '2025-05-21', '13:30:00', '15:35:00'),

    (5, 5, '2025-05-22', '12:00:00', '13:58:00'),
    (6, 1, '2025-05-22', '14:30:00', '16:51:00'),

    (7, 2, '2025-05-23', '10:00:00', '12:10:00'),
    (8, 3, '2025-05-23', '12:40:00', '14:30:00'),

    (9, 4, '2025-05-24', '13:00:00', '15:05:00'),
    (10, 5, '2025-05-24', '15:30:00', '17:28:00');

-- Schedule 1
INSERT INTO seat (schedule_id, seat_code, is_reserved) VALUES
                                                           (1, 'A1', TRUE), (1, 'A2', TRUE), (1, 'A3', FALSE), (1, 'A4', FALSE), (1, 'A5', FALSE), (1, 'A6', FALSE),
                                                           (1, 'B1', FALSE), (1, 'B2', TRUE), (1, 'B3', FALSE), (1, 'B4', FALSE), (1, 'B5', FALSE), (1, 'B6', FALSE),
                                                           (1, 'C1', FALSE), (1, 'C2', TRUE), (1, 'C3', FALSE), (1, 'C4', FALSE), (1, 'C5', FALSE), (1, 'C6', FALSE);

-- Schedule 2
INSERT INTO seat (schedule_id, seat_code, is_reserved) VALUES
                                                           (2, 'A1', FALSE), (2, 'A2', FALSE), (2, 'A3', TRUE), (2, 'A4', FALSE), (2, 'A5', FALSE), (2, 'A6', FALSE),
                                                           (2, 'B1', TRUE), (2, 'B2', TRUE), (2, 'B3', FALSE), (2, 'B4', FALSE), (2, 'B5', FALSE), (2, 'B6', FALSE),
                                                           (2, 'C1', FALSE), (2, 'C2', FALSE), (2, 'C3', TRUE), (2, 'C4', FALSE), (2, 'C5', FALSE), (2, 'C6', FALSE);

-- Schedule 3
INSERT INTO seat (schedule_id, seat_code, is_reserved) VALUES
                                                           (3, 'A1', FALSE), (3, 'A2', TRUE), (3, 'A3', TRUE), (3, 'A4', FALSE), (3, 'A5', FALSE), (3, 'A6', FALSE),
                                                           (3, 'B1', FALSE), (3, 'B2', FALSE), (3, 'B3', FALSE), (3, 'B4', TRUE), (3, 'B5', FALSE), (3, 'B6', FALSE),
                                                           (3, 'C1', TRUE), (3, 'C2', FALSE), (3, 'C3', FALSE), (3, 'C4', FALSE), (3, 'C5', FALSE), (3, 'C6', FALSE);

-- Schedule 4
INSERT INTO seat (schedule_id, seat_code, is_reserved) VALUES
                                                           (4, 'A1', FALSE), (4, 'A2', FALSE), (4, 'A3', TRUE), (4, 'A4', TRUE), (4, 'A5', FALSE), (4, 'A6', FALSE),
                                                           (4, 'B1', TRUE), (4, 'B2', FALSE), (4, 'B3', FALSE), (4, 'B4', TRUE), (4, 'B5', FALSE), (4, 'B6', FALSE),
                                                           (4, 'C1', FALSE), (4, 'C2', FALSE), (4, 'C3', TRUE), (4, 'C4', FALSE), (4, 'C5', FALSE), (4, 'C6', FALSE);

-- Schedule 5
INSERT INTO seat (schedule_id, seat_code, is_reserved) VALUES
                                                           (5, 'A1', TRUE), (5, 'A2', FALSE), (5, 'A3', FALSE), (5, 'A4', FALSE), (5, 'A5', FALSE), (5, 'A6', FALSE),
                                                           (5, 'B1', FALSE), (5, 'B2', TRUE), (5, 'B3', TRUE), (5, 'B4', FALSE), (5, 'B5', FALSE), (5, 'B6', FALSE),
                                                           (5, 'C1', FALSE), (5, 'C2', FALSE), (5, 'C3', FALSE), (5, 'C4', TRUE), (5, 'C5', FALSE), (5, 'C6', FALSE);

INSERT INTO Reservation (reservation_id, schedule_id, reservation_time, total_person, total_price,status)
VALUES
    ('FD289', 1, '2025-05-19 13:15:00', 2, 22000,'예매완료'),
    ('AB123', 2, '2025-05-19 14:00:00', 1, 11000,'예매중');


INSERT INTO Reservation_Seat (reservation_id, seat_id)
VALUES
    ('FD289', 2),
    ('FD289',3),
    ('AB123', 4),
    ('AB123',5);


INSERT INTO Payment (payment_id, reservation_id, use_cash_receipt,phone_number,payment_time)
VALUES
    (1, 'FD289', TRUE, '01012345678', '2025-05-19 13:16:00');

select * from seat;