package com.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть перше число: ");
            double num1 = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть друге число: ");
            double num2 = Double.parseDouble(scanner.nextLine());

            System.out.print("Оберіть операцію (+, -, *, /): ");
            String operation = scanner.nextLine();

            double result = switch (operation) {
                case "+" -> calculator.add(num1, num2);
                case "-" -> calculator.subtract(num1, num2);
                case "*" -> calculator.multiply(num1, num2);
                case "/" -> calculator.divide(num1, num2);
                default -> throw new InvalidInputException("Невідома операція.");
            };
            /*
            double result;
            switch (operation) {
                case "+":
                    result = calculator.add(num1, num2);
                    break;
                case "-":
                    result = calculator.subtract(num1, num2);
                    break;
                case "*":
                    result = calculator.multiply(num1, num2);
                    break;
                case "/":
                    result = calculator.divide(num1, num2);
                    break;
                default:
                    throw new InvalidInputException("Невідома операція.");
            }
            */

            System.out.println("Результат: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введено некоректне число.");
        } catch (ArithmeticException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Помилка: " + e.getMessage());
        } finally {
            System.out.println("Обробка запиту завершена.");
        }
    }
}
