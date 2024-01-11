package package2;


import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5;
        int totalRounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number!");

        for (int round = 1; round <= totalRounds; round++) {
            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;

            System.out.println("\nRound " + round + ":");
            System.out.println("I have generated a number between " + lowerBound + " and " + upperBound + ". Try to guess it!");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                scanner.nextLine(); 

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number!");
                    int roundScore = maxAttempts - attempts; 
                    totalScore += roundScore;
                    System.out.println("Round Score: " + roundScore + " | Total Score: " + totalScore);
                    break; 
                } else if (userGuess < randomNumber) {
                    System.out.println("Try again! The number is higher.");
                } else {
                    System.out.println("Try again! The number is lower.");
                }

                attempts++;
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Your Total Score: " + totalScore);

        scanner.close();
    }
}
