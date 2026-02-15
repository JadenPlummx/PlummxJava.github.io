/*
 * Name: Jaden Plummer
 * Date: 2026-02-14
 * Description: This program calculates how many slices a pizza should be cut into
 *              based on its diameter. It also calculates the area of each slice.
 */

import java.util.Scanner;

public class PizzaSlicesCalculator {

    public static void main(String[] args) {

        // Constants
        final double MIN_DIAMETER = 6.0;
        final double MAX_DIAMETER = 24.0;
        final double PI = Math.PI;

        Scanner input = new Scanner(System.in);

        // Prompt user
        System.out.print("Please enter the diameter of your pizza: ");

        // Validate numeric input
        if (!input.hasNextDouble()) {
            System.out.println("Error: Diameter must be a numeric value. Please try again.");
            return;
        }

        double diameter = input.nextDouble();

        // Validate range
        if (diameter < MIN_DIAMETER || diameter > MAX_DIAMETER) {
            System.out.println("Error: Pizza must have a diameter in the range of 6\" to 24\" inclusive! Please try again.");
            return;
        }

        // Determine number of slices based on diameter
        int slices;

        if (diameter < 8) {
            slices = 4;
        } else if (diameter < 12) {
            slices = 6;
        } else if (diameter < 14) {
            slices = 8;
        } else if (diameter < 16) {
            slices = 10;
        } else if (diameter < 20) {
            slices = 12;
        } else {
            slices = 16;
        }

        // Calculate pizza area
        double radius = diameter / 2.0;
        double pizzaArea = PI * radius * radius;

        // Calculate slice area
        double sliceArea = pizzaArea / slices;

        // Output results rounded to two decimals
        System.out.printf("%nA %.2f\" pizza will yield %d slices.%n", diameter, slices);
        System.out.printf("Each slice will have an area of %.2f square inches.%n", sliceArea);

        input.close();
    }
}


