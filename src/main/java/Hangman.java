import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final Scanner scanner = new Scanner(System.in);
    private String randomWord;
    private char[] userWord;
    private int chances;
    private boolean ifWin;

    public void saveWord(String word) {
        databaseConnection.addWord(word);
    }

    public void removeWord(String word) {
        databaseConnection.removeWord(word);
    }

    public void removeScore(int id) {
        databaseConnection.removeScore(id);
    }

    public void play() {
        System.out.println("Welcome to Hangman!");
        databaseConnection.connect();
        List<String> words = databaseConnection.getWords();

        while (true) {
            System.out.println("""
                    \nChoose game mode:\s
                    1. Easy - 30 chances
                    2. Medium - 20 chances
                    3. Hard - 10 chances
                    4. Go to settings
                    5. End\s""");
            int mode = Integer.parseInt(scanner.nextLine());

            switch (mode) {
                case 1 -> chances = 30;
                case 2 -> chances = 20;
                case 3 -> chances = 10;
                case 4 -> {
                    System.out.println("""
                            \n1. Add word
                            2. Remove word
                            3. Remove score""");
                    int chosenOption = Integer.parseInt(scanner.nextLine());
                    switch (chosenOption) {
                        case 1 -> {
                            System.out.println("Enter word: ");
                            String wordToAdd = scanner.nextLine();
                            saveWord(wordToAdd.toLowerCase().trim());
                            words = databaseConnection.getWords();
                        }
                        case 2 -> {
                            System.out.println("Choose word name: ");
                            words.forEach(System.out::println);
                            String wordName = scanner.nextLine();
                            removeWord(wordName.toLowerCase().trim());
                            words = databaseConnection.getWords();
                        }
                        case 3 -> {
                            System.out.println("Enter score id: ");
                            List<Score> scores = databaseConnection.getScores();
                            if (scores.isEmpty()) {
                                System.out.println("No scores found!");
                                return;
                            }

                            int scoreId = Integer.parseInt(scanner.nextLine());
                            removeScore(scoreId);
                        }
                        default -> {
                            System.out.println("Invalid option!");
                            return;
                        }
                    }
                }
                case 5 -> {
                    databaseConnection.disconnect();
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid input! Chances = 20");
                    chances = 20;
                }
            }

            Random rand = new Random();
            randomWord = words.get(rand.nextInt(words.size()));
            userWord = new char[randomWord.length()];
            Arrays.fill(userWord, '_');

            System.out.println(new String(userWord));

            while (chances > 0) {
                System.out.println("\nEnter a character: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Invalid input! Please enter a valid character!");
                    continue;
                }
                char userCharacter = input.charAt(0);


                guessChar(userCharacter);

                if (String.valueOf(userWord).equals(randomWord)) {
                    win();
                    break;
                }
            }

        }
    }

    private void checkChar(char character) {
        boolean found = false;

        for (int i = 0; i < randomWord.length(); i++) {
            if (character == randomWord.charAt(i)) {
                found = true;
                userWord[i] = randomWord.charAt(i);
            }
        }

        if (found) {
            guessed();
            return;
        }

        notGuessed();
    }

    private void guessChar(char character) {
        if (randomWord.indexOf(character) == -1) {
            notGuessed();
            return;
        }

        boolean alreadyGuessed = true;
        for (int i = 0; i < randomWord.length(); i++) {
            if (randomWord.charAt(i) == character && userWord[i] == '_') {
                alreadyGuessed = false;
                break;
            }
        }

        if (alreadyGuessed) {
            System.out.println("This letter is already in use!");
            return;
        }

        checkChar(character);
    }

    private void guessed() {
        System.out.println("You guessed it!");
        System.out.println("Chances: " + chances);
        System.out.println(new String(userWord));
    }

    private void notGuessed() {
        System.out.println("Invalid character!");
        chances--;
        System.out.println("Chances: " + chances);
        System.out.println(new String(userWord));

        if (chances == 0) {
            lose();
        }
    }

    private void win() {
        ifWin = true;
        System.out.println("You win!");
        System.out.println("Chances: " + chances);
        System.out.println("Guessed word " + randomWord);
        saveScore();
    }

    private void lose() {
        ifWin = false;
        System.out.println("You lose!");
        System.out.println("Chances: " + chances);
        System.out.println("Not guessed word: " + randomWord);
        saveScore();
    }

    private void saveScore() {
        Score score = Score.builder().leftChances(chances).ifWin(ifWin).build();
        databaseConnection.addScore(score);
    }
}
