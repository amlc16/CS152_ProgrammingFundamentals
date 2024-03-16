import java.util.Scanner;
 class WordleDraft {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Set the hidden word
        String hiddenWord = "glass";

        int maxGuesses = 6;

        System.out.println("Welcome to Wordle!");

        for (int guesses = 1; guesses <= maxGuesses; guesses++) {
            System.out.println("Guess " + guesses + ":");
            String guess = scanner.nextLine().toLowerCase(); // Convert guess to lowercase for case insensitivity

            // Initialize feedback
            char[] feedback = new char[hiddenWord.length()];

            // Find correct values
            for (int i = 0; i < hiddenWord.length(); i++) {
                if (guess.charAt(i) == hiddenWord.charAt(i)) {
                    feedback[i] = 'X';
                }
            }

            // Find present values
            for (int i = 0; i < hiddenWord.length(); i++) {
                char guessChar = guess.charAt(i);
                if (feedback[i] != 'X') {
                    for (int j = 0; j < hiddenWord.length(); j++) {
                        if (guessChar == hiddenWord.charAt(j) && feedback[j] != 'X' && feedback[j] != 'o') {
                            feedback[j] = 'o';
                            break;
                        }
                    }
                }
            }

            // Fill in missing values
            for (int i = 0; i < feedback.length; i++) {
                if (feedback[i] != 'X' && feedback[i] != 'o') {
                    feedback[i] = '.';
                }
            }

            // Display feedback
            System.out.println(new String(feedback));

            // Check for a win
            if (new String(feedback).equals("XXXXX")) {
                System.out.println("Congratulations! You've won!");
                break;
            }

            // Check for last guess
            if (guesses == maxGuesses) {
                System.out.println("Sorry, you've run out of guesses. The word was " + hiddenWord);
            }
        }

        scanner.close();
    }
}
