/**
 * CS 152 Lab 5 - Wordle
 *
 * Implement the methods needed to play a Wordle-like game.
 *
 * @author Alexander Leon
 */
import java.util.Scanner;

/** Contains methods for a wordle clone */
public class Wordle {

    /** Represents a letter in the guess in the correct position. */
    public static final char CORRECT = 'X';

    /** Represents a letter in the guess that is present, but in wrong place. */
    public static final char PRESENT = 'o';

    /** Represents a letter in the guess does not occur in the word at all. */
    public static final char MISSING = '.';

    /** How many guesses do we get to solve the puzzle? */
    public static final int NUMBER_OF_GUESSES = 6;

    /**
     * Picks a random word from the dictionary.
     * @param dictionary An array of words.
     * @return Randomly chosen word from dictionary.
     */
    public static String getRandomWord(String[] dictionary) {
        int randomIndex = (int) (Math.random() * dictionary.length);
        return dictionary[randomIndex];
    }

    /**
     * Is the guess a recognized word?
     * @param dictionary Array of known words.
     * @param guess The guess word.
     * @return True if guess is in dictionary, false if not.
     */
    public static boolean isValidWord(String[] dictionary, String guess) {
        for (String word : dictionary) {
            if (word.equals(guess)) {
                return true;
            }
        }
        return false;
    }

    /**
     * How close is the guess to the secret word?
     * @param word The secret word
     * @param guess Guessed word
     * @return Array of characters corresponding to the letters of guess,
     *       where the char at a given index is:
     *           CORRECT if the guess letter appears at that position in word
     *           PRESENT if the guess letter is present in word elsewhere
     *           MISSING if the guess letter does not occur in word
     */
    public static char[] getGuessResult(String word, String guess) {
        char[] result = new char[word.length()];
        char[] guessedChars = new char [word.length()];
        char[] leftChars = new char [word.length()];

        //Initialize result and gets the values for guessedChars and leftChars
        for (int i = 0; i < word.length(); i++) {
            result[i] = MISSING;
            guessedChars[i] = guess.charAt(i);
            leftChars[i] = word.charAt(i);
        }

        //Find CORRECT values
        for (int i = 0; i < word.length(); i++) {
            if (guess.charAt(i) == word.charAt(i)) {
                result[i] = CORRECT;
                guessedChars[i] = ' ';
                leftChars[i] = ' ';
            }
        }

        //Find PRESENT values
        for (int i = 0; i < word.length(); i++) {
            if (guessedChars[i] != ' ' ) {
                for (int j = 0; j < word.length(); j++) {
                    if (guessedChars[i] == leftChars[j]) {
                        result[i] = PRESENT;
                        guessedChars[i] = ' ';
                        leftChars[j] = ' ';
                        break;
                    }
                }
            }
        }
        return result;

//        for (int i = 0; i < guess.length(); i++) {
//            if (result[i] != CORRECT ) {
//                char guessChar = guess.charAt(i);
//                for (int j = 0; j < word.length(); j++) {
//                    if (!used[j] && guessedChar == word.charAt(j)) {
//                        result[i] = PRESENT;
//                        guessedChars[i] = ' ';
//                        leftChars[j] = ' ';
//                        break;
//                    }
//                }
//            }
//        }
    }

    /**
     * Is this a winning result?
     * @param guessResult Array as returned by getGuessResult
     * @return True if all places are CORRECT, false if not
     */
    public static boolean isWinningResult(char[] guessResult) {
        for (char result : guessResult) {
            if (result != CORRECT) {
                return false;
            }
        }
        return true;
    }

    /**
     * Plays a console based Wordle game
     * @param args Ignored
     */
    public static void main(String[] args) {

        System.out.println("Let's play Wordle!");
        System.out.println();
        // The big array of words is in a separate file
        String[] words = WordleDictionary.FIVE_LETTER_WORDS;

        Scanner in = new Scanner(System.in);

        String secret = getRandomWord(words);
        int guesses = NUMBER_OF_GUESSES;

        boolean winning;

        do {
            System.out.println("Guesses remaining: " + guesses);
            System.out.println("What is your guess?");

            String guess = in.nextLine().trim().toLowerCase();

            while(!isValidWord(words, guess)) {
                System.out.println("Not a recognized word! Try again");
                guess = in.nextLine().trim().toLowerCase();
            }

            char[] guessResult = getGuessResult(secret, guess);
            System.out.println(new String(guessResult));

            winning = isWinningResult(guessResult);
            guesses--;

        } while(guesses > 0 && !winning);

        System.out.println("The word was " + secret);
    }
}
