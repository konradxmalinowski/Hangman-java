# 🎯 Hangman Game - Java Edition

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A classic **Hangman** word guessing game built with Java! 🎮 Challenge yourself with different difficulty levels and test your vocabulary skills. The game now supports a real **MySQL database** for words and score history.

## ✨ Features

- 🎯 **Multiple Difficulty Levels**

  - Easy Mode: 30 chances
  - Medium Mode: 20 chances
  - Hard Mode: 10 chances

- 🎲 **Random Word Selection** from a database-backed word list
- 🛠️ **Settings Menu** to manage content and history:
  - Add a new word
  - Remove an existing word
  - Remove a score by ID
- 🧠 **Score Tracking**: saves every win/lose with remaining chances and date
- 📊 **Real-time Progress** with remaining chances
- 🎨 **Clean Console Interface** with intuitive UX
- 🔄 **Continuous Gameplay** with option to exit

## 🚀 Getting Started

### Prerequisites

- **Java 17** or higher (matches `pom.xml`)
- **Maven 3.6+** (for dependency management)
- **Git** (for cloning the repository)
- **MySQL 8+** running locally with access to create a database
- IDE users: enable Lombok annotation processing

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/konradxmalinowski/Hangman-java.git
   cd Hangman-java
   ```

2. **Compile the project**

   ```bash
   mvn compile
   ```

3. **Run the game**

   Using Maven (includes runtime dependencies on the classpath):

   ```bash
   mvn -q exec:java -Dexec.mainClass=Main
   ```

   Or run compiled classes directly (you must add dependencies to the classpath manually):

   ```bash
   java -cp target/classes Main
   ```

### Database setup (MySQL)

Run these SQL statements to prepare the database used by the app:

```sql
CREATE DATABASE IF NOT EXISTS `hangman-java` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `hangman-java`;

CREATE TABLE IF NOT EXISTS words (
  id   INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS scores (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  leftChances  INT NOT NULL,
  if_win       BOOLEAN NOT NULL,
  date         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

Default connection (can be changed in `DatabaseConnection.java`):

```
jdbc:mysql://127.0.0.1:3306/hangman-java
user: root
password: ""
```

## 🎮 How to Play

1. **Launch the game** and you'll see the welcome message
2. **Choose your difficulty level**:

   - Type `1` for Easy (30 chances)
   - Type `2` for Medium (20 chances)
   - Type `3` for Hard (10 chances)
   - Type `4` to exit the game

3. **Guess letters** one by one to reveal the hidden word
4. **Win** by guessing all letters before running out of chances
5. **Play again** or exit when you're done

### Example Gameplay

```
Welcome to Hangman!

Choose game mode:
1. Easy - 30 chances
2. Medium - 20 chances
3. Hard - 10 chances
4. Go to settings
5. End

Enter your choice: 2

_ _ _ _ _ _ _ _ _ _ _ _

Enter a character: a
You guessed it!
Chances: 20
_ a _ _ _ _ _ _ _ _ _ _

Enter a character: e
Invalid character!
Chances: 19
_ a _ _ _ _ _ _ _ _ _ _
```

## 🏗️ Project Structure

```
Hangman-java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Main.java               # Entry point
│   │   │   ├── Hangman.java            # Game logic + menu + score
│   │   │   ├── Score.java              # Score model (Lombok)
│   │   │   └── DatabaseConnection.java # MySQL access layer
│   │   └── resources/
│   └── test/
│       └── java/
├── target/                            # Compiled classes
├── pom.xml                           # Maven configuration
└── README.md                         # This file
```

## 🔧 Technical Details

- **Language**: Java 17
- **Build Tool**: Maven
- **Architecture**: Object-oriented design with separation of concerns
- **Input Method**: Console-based user interaction
- **Word Storage**: MySQL tables `words` and `scores`
- **Dependencies**: `mysql-connector-j`, `slf4j-simple`, `lombok`

### Key Classes

- **`Main`**: Application entry point
- **`Hangman`**: Core game logic, settings menu, score persistence
- **`DatabaseConnection`**: JDBC MySQL operations for words and scores

## 🚧 Future Enhancements

- [ ] 🗄️ **Database Integration** for word management and score tracking
- [ ] 📈 **Score System** with high score leaderboards
- [ ] 🎨 **GUI Interface** using JavaFX or Swing
- [ ] 🌍 **Multi-language Support** for different word sets
- [ ] 🎵 **Sound Effects** and background music
- [ ] 📱 **Mobile Version** using Java ME or Android
- [ ] 🤖 **AI Opponent** for competitive gameplay

## 🤝 Contributing

Contributions are welcome! 🎉 Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Development Guidelines

- Follow Java coding conventions
- Add comments for complex logic
- Test your changes thoroughly
- Update documentation as needed

## 👨‍💻 Author

**Konrad Malinowski** - _Initial work_ - [https://github.com/konradxmalinowski](https://github.com/konradxmalinowski)

## 🙏 Acknowledgments

- Classic Hangman game inspiration
- Java community for best practices
- Contributors and testers

---

<div align="center">

**⭐ Star this repository if you found it helpful! ⭐**

Made with ❤️ and Java ☕

</div>
