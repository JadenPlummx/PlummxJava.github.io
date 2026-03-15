/*
 * Name: Jaden Plummer
 * Date: 2026-03-13
 * Description: This program simulates a two player artillery game where players
 *              enter a power and angle to try to hit the other player.
 */

import java.util.Scanner;
import java.util.Random;

public class ProjectileGame {

    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    static String player1Name;
    static String player2Name;

    static int player1Score = 0;
    static int player2Score = 0;

    public static void main(String[] args) {

        // Constants
        final double GRAVITY = 9.81;
        final double MIN_POSITION = 0.0;
        final double MAX_POSITION = 120.0;
        final double HIT_DISTANCE = 1.0;

System.out.println("Welcome to the Projectile Motion Game!");

        // Get player names
        System.out.print("Enter Player 1 name: ");
        player1Name = input.nextLine();

        System.out.print("Enter Player 2 name: ");
        player2Name = input.nextLine();

        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")) {

            int winner = runGame(GRAVITY, MIN_POSITION, MAX_POSITION, HIT_DISTANCE);

            if (winner == 1) {
                player1Score++;
                System.out.println(player1Name + " wins this game!");
            } else {
                player2Score++;
                System.out.println(player2Name + " wins this game!");
            }

            System.out.println();
            System.out.println("Score:");
            System.out.println(player1Name + ": " + player1Score);
            System.out.println(player2Name + ": " + player2Score);

            System.out.print("\nWould you like to play again? (yes/no): ");
            playAgain = input.nextLine();

            System.out.println();
        }

        System.out.println("Final Score:");
        System.out.println(player1Name + ": " + player1Score);
        System.out.println(player2Name + ": " + player2Score);

        input.close();
    }

    // Gets a valid power value from the user
    public static double getPower() {

        final double MIN_POWER = 1.0;
        final double MAX_POWER = 1000.0;

        double power = 0;

        while (power < MIN_POWER || power > MAX_POWER) {

            System.out.print("Enter shot power (1 - 1000): ");

            if (!input.hasNextDouble()) {
                System.out.println("Error: Please enter a numeric value.");
                input.next();
                continue;
            }

            power = input.nextDouble();

            if (power < MIN_POWER || power > MAX_POWER) {
                System.out.println("Error: Power must be between 1 and 1000.");
            }
        }

        input.nextLine();
        return power;
    }

    // Gets a valid angle value from the user
    public static double getAngle() {

        final double MIN_ANGLE = 0.0;
        final double MAX_ANGLE = 180.0;

        double angle = -1;

        while (angle < MIN_ANGLE || angle > MAX_ANGLE) {

            System.out.print("Enter shot angle (0 - 180): ");

            if (!input.hasNextDouble()) {
                System.out.println("Error: Please enter a numeric value.");
                input.next();
                continue;
            }

            angle = input.nextDouble();

            if (angle < MIN_ANGLE || angle > MAX_ANGLE) {
                System.out.println("Error: Angle must be between 0 and 180.");
            }
        }

        input.nextLine();
        return angle;
    }

// Calculates where the projectile lands
    public static double getShot(double startingX, double gravity) {

        double power = getPower();
        double angle = getAngle();

        double angleRadians = Math.toRadians(angle);

        double verticalVelocity = power * Math.sin(angleRadians);
        double horizontalVelocity = power * Math.cos(angleRadians);

        // Time until projectile lands
        double time = (2 * verticalVelocity) / gravity;

        if (time < 0) {
            time = 0;
        }

        double landingX = (horizontalVelocity * time) + startingX;

        return landingX;
    }

    // Runs one full game and returns the winner
    public static int runGame(double gravity, double minX, double maxX, double hitDistance) {

        double player1X = minX + random.nextDouble() * (maxX - minX);
        double player2X = minX + random.nextDouble() * (maxX - minX);

        int currentPlayer = 1;
        int winner = 0;

        // Ensure players are not too close
        while (Math.abs(player1X - player2X) < hitDistance) {
            player2X = minX + random.nextDouble() * (maxX - minX);
        }

        System.out.println("New game started!");
        System.out.printf("%s is at x = %.2f%n", player1Name, player1X);
        System.out.printf("%s is at x = %.2f%n%n", player2Name, player2X);

        while (winner == 0) {

            double shotX;
            double targetX;

            if (currentPlayer == 1) {

                System.out.println(player1Name + "'s turn.");
                shotX = getShot(player1X, gravity);
                targetX = player2X;

            } else {

                System.out.println(player2Name + "'s turn.");
                shotX = getShot(player2X, gravity);
                targetX = player1X;
            }

            System.out.printf("Shot landed at x = %.2f%n", shotX);

            if (Math.abs(shotX - targetX) < hitDistance) {

                winner = currentPlayer;

            } else {

                System.out.println("Miss!\n");

                if (currentPlayer == 1) {
                    currentPlayer = 2;
                } else {
                    currentPlayer = 1;
                }
            }
        }

        return winner;
    }
}
