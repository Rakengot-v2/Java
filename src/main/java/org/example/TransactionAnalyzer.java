package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Optional;

abstract class TransactionAnalyzer {
    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(String monthYear,List<Transaction> transactions) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(),  DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }
    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }

    public static Optional<Transaction> findLargestExpense(List<Transaction> transactions, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Transaction largestExpense = null;

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            boolean isInRange = date.isAfter(start.minusDays(1)) && date.isBefore(end.plusDays(1));
            boolean isExpense = transaction.getAmount() < 0;

            if (isInRange && isExpense) {
                if (largestExpense == null || transaction.getAmount() < largestExpense.getAmount()) {
                    largestExpense = transaction;
                }
            }
        }
        return Optional.ofNullable(largestExpense);
    }

    public static Optional<Transaction> findSmallestExpense(List<Transaction> transactions, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        Transaction smallestExpense = null;

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            boolean isInRange = date.isAfter(start.minusDays(1)) && date.isBefore(end.plusDays(1));
            boolean isExpense = transaction.getAmount() < 0;

            if (isInRange && isExpense) {
                if (smallestExpense == null || transaction.getAmount() > smallestExpense.getAmount()) {
                    smallestExpense = transaction;
                }
            }
        }
        return Optional.ofNullable(smallestExpense);
    }

}

