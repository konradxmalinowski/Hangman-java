import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    Connection connection;
    Statement statement;

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hangman-java", "root", "");
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database. Error message: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to disconnect from database. Error message: " + e.getMessage());
        }
    }

    public List<String> getWords() {
        try {
            List<String> result = new ArrayList<>();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM words");
            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }

            return result;
        } catch (SQLException e) {
            System.out.println("Failed to get words from database. Error message: " + e.getMessage());
        }
        return null;
    }

    public boolean addWord(String word) {
        return false;
    }

    public boolean removeWord(String word) {
        return false;
    }

    public List<Score> getScores() {
        try {
            List<Score> result = new ArrayList<>();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, score, date FROM scores");
            while (resultSet.next()) {
                Score score = Score.builder().id(resultSet.getInt(1)).score(resultSet.getInt(2)).date(resultSet.getDate(3)).build();
                result.add(score);
            }

            return result;
        } catch (SQLException e) {
            System.out.println("Failed to get scores from database. Error message: " + e.getMessage());
        }
        return null;
    }

    public boolean addScore(int score) {
        return false;
    }

    public boolean removeScore(int score) {
        return false;
    }
}
