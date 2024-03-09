package efs.task.syntax;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    private final int upperNumber;

    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) throws IllegalArgumentException {
        try {
            int number = Integer.parseInt(argument);

            if (number < 1 || number > 400) {
                System.out.println(number + " to NIEPOPRAWNY ARGUMENT - jest spoza zakresu <1, 400>");
                throw new IllegalArgumentException("NIEPOPRAWNY ARGUMENT");
            }
            this.upperNumber = number;
        } catch (NumberFormatException e) {
            System.out.println("'" + argument + "' to NIEPOPRAWNY ARGUMENT - to nie liczba");
            throw new IllegalArgumentException("NIEPOPRAWNY ARGUMENT");
        }
    }

    public void play() {
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + upperNumber + ">");
        Random generator = new Random();
        int randomNumber = generator.nextInt(upperNumber) + 1;

        double calculateL = Math.log(upperNumber) / Math.log(2);
        int maxOfTries  = (int) Math.floor(calculateL) + 1;

        int numberOfTries = 1;
        boolean guessedCorrectly = false;

        Scanner scanner = new Scanner(System.in);

        while (numberOfTries <= maxOfTries) {
            System.out.println("Liczba prób: " + maxOfTries + " [" + "*".repeat(numberOfTries) + ".".repeat(maxOfTries - numberOfTries) + "]");
            // printProgressBar(tries, numberOfTries);
            System.out.println("PODAJ liczbę :");

            String input = scanner.next();

            if (isInteger(input)) {
                int guess = Integer.parseInt(input);

                if (guess > randomNumber) {
                    System.out.println("ZBYT WIELE");
                } else if (guess < randomNumber) {
                    System.out.println("NIE WYSTARCZY");
                } else {
                    System.out.println("TAK");

                    guessedCorrectly = true;
                    break;
                }
            } else {
                System.out.println("NIE LICZBA");
                numberOfTries++;
                continue;
            }

            numberOfTries++;
        }

        if (!guessedCorrectly) {
            System.out.println("NIESTETY, wyczerpałeś limit prób (" + numberOfTries + ") do odgadnięcia liczby " + randomNumber);
        }
        else {
            System.out.println("GRATULACJE");
        }
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
