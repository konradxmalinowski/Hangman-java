import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseConnection {
    Connection connection;

    public void connect() {
        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }

            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hangman-java", "root", "");
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database. Error message: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to disconnect from database. Error message: " + e.getMessage());
        }
    }

    public List<String> getWords() {
        final String query = "SELECT name FROM words";
        List<String> result = new ArrayList<>();

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                result.add(resultSet.getString(1));
            }

            return result;
        } catch (SQLException e) {
            System.out.println("Failed to get words from database. Error message: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<String> getWordsWithId() {
        final String query = "SELECT id, name FROM words";
        List<String> result = new ArrayList<>();

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                result.add(resultSet.getString(1) + " " + resultSet.getString(2));
            }

            return result;
        } catch (SQLException e) {
            System.out.println("Failed to get words from database. Error message: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    public void addWord(String word) {
        String validatedWord = word.trim().toLowerCase();
        final String query = "INSERT IGNORE INTO words (name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, validatedWord);
            int addedRows = preparedStatement.executeUpdate();

            if (addedRows > 0) {
                System.out.println("Word added successfully");
                return;
            }

            System.out.println("Word already exists");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeWord(String word) {
        String validatedWord = word.trim().toLowerCase();
        final String query = "DELETE FROM words WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, validatedWord);
            int resultSet = preparedStatement.executeUpdate();

            if (resultSet > 0) {
                System.out.println("Word removed successfully");
                return;
            }

            System.out.println("Word doesn't exist");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Score> getScores() {
        List<Score> result = new ArrayList<>();
        final String query = "SELECT id, leftChances, if_win, date FROM scores";

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Score score = Score.builder().id(resultSet.getInt(1)).leftChances(resultSet.getInt(2)).ifWin(resultSet.getBoolean(3)).date(resultSet.getDate(4)).build();
                result.add(score);
            }

            return result;
        } catch (SQLException e) {
            System.out.println("Failed to get scores from database. Error message: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public void addScore(Score score) {
        final String query = "INSERT IGNORE INTO scores (leftChances, if_win) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, score.getLeftChances());
            preparedStatement.setBoolean(2, score.isIfWin());
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                System.out.println("Score added successfully");
                return;
            }
            System.out.println("Score already exist");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeScore(int id) {
        final String query = "DELETE FROM scores WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int removedRows = preparedStatement.executeUpdate();
            if (removedRows > 0) {
                System.out.println("Score removed successfully");
                return;
            }
            System.out.println("Score doesn't exists");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
