# 🎯 Hangman Game - Java Edition

[![Java](https://img.shields.io/badge/Java-24-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A classic **Hangman** word guessing game built with Java! 🎮 Challenge yourself with different difficulty levels and test your vocabulary skills. This project features a clean console interface and is designed with extensibility in mind for future database integration.

## ✨ Features

- 🎯 **Multiple Difficulty Levels**

  - Easy Mode: 30 chances
  - Medium Mode: 20 chances
  - Hard Mode: 10 chances

- 🎲 **Random Word Selection** from a curated word list
- 📊 **Real-time Progress Tracking** with remaining chances
- 🎨 **Clean Console Interface** with intuitive user experience
- 🔄 **Continuous Gameplay** with option to exit
- 🗄️ **Database Ready** - prepared for future score tracking and word management

## 🚀 Getting Started

### Prerequisites

- **Java 24** or higher
- **Maven 3.6+** (for dependency management)
- **Git** (for cloning the repository)

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/Hangman-java.git
   cd Hangman-java
   ```

2. **Compile the project**

   ```bash
   mvn compile
   ```

3. **Run the game**

   ```bash
   mvn exec:java -Dexec.mainClass="Main"
   ```

   Or simply:

   ```bash
   java -cp target/classes Main
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
4. End

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
│   │   │   ├── Main.java              # Entry point
│   │   │   ├── Hangman.java           # Core game logic
│   │   │   └── DatabaseConnection.java # Database interface (future)
│   │   └── resources/
│   └── test/
│       └── java/
├── target/                            # Compiled classes
├── pom.xml                           # Maven configuration
└── README.md                         # This file
```

## 🔧 Technical Details

- **Language**: Java 24
- **Build Tool**: Maven
- **Architecture**: Object-oriented design with separation of concerns
- **Input Method**: Console-based user interaction
- **Word Storage**: Currently uses a predefined list (expandable to database)

### Key Classes

- **`Main`**: Application entry point
- **`Hangman`**: Core game logic and user interaction
- **`DatabaseConnection`**: Database interface (prepared for future implementation)

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

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name** - _Initial work_ - [YourGitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Classic Hangman game inspiration
- Java community for best practices
- Contributors and testers

---

<div align="center">

**⭐ Star this repository if you found it helpful! ⭐**

Made with ❤️ and Java ☕

</div>
