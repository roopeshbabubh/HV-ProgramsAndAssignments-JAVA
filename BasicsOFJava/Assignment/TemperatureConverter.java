import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the temperature: ");

        double temperature = scanner.nextDouble();

        System.out.println("\n----- Conversion Menu -----");

        System.out.println("1. Celsius to Fahrenheit");

        System.out.println("2. Fahrenheit to Celsius");

        System.out.println("---------------------------");

        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        double convertedTemperature;

        switch (choice) {

            case 1:

                convertedTemperature = celsiusToFahrenheit(temperature);
                System.out.println(temperature + "°C = " + convertedTemperature + "°F");

                break;

            case 2:

                convertedTemperature = fahrenheitToCelsius(temperature);
                System.out.println(temperature + "°F = " + convertedTemperature + "°C");

                break;

            default:

                System.out.println("Invalid choice!");

                break;

        }

        scanner.close();

    }

    public static double celsiusToFahrenheit(double celsius) {

        return (celsius * 9/5) + 32;

    }

    public static double fahrenheitToCelsius(double fahrenheit) {

        return (fahrenheit - 32) * 5/9;

    }

}