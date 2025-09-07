CREATE DATABASE IF NOT EXISTS `hangman-java` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `hangman-java`;

-- Tables
CREATE TABLE IF NOT EXISTS words (
  id   INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS scores (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  leftChances  INT NOT NULL,
  if_win       BOOLEAN NOT NULL,
  date         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Seed data
INSERT INTO words (name) VALUES
('apple'),
('banana'),
('coffee'),
('computer'),
('freedom'),
('friend'),
('guitar'),
('hangman'),
('holiday'),
('js'),
('language'),
('mountain'),
('music'),
('programming'),
('river'),
('school'),
('student'),
('summer'),
('sunshine'),
('teacher'),
('winter');
