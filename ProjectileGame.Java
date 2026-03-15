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
