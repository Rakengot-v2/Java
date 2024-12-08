package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    private DateTimeFormatter dateFormatter;
    private List<Transaction> transactions;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public double calculateTotalBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public int countTransactionsByMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), dateFormatter);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public double findMaxExpenseAmount(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, dateFormatter);
        LocalDate end = LocalDate.parse(endDate, dateFormatter);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Фільтруємо витрати (від'ємні суми)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
                    return !date.isBefore(start) && !date.isAfter(end); // Фільтруємо по датах
                })
                .max(Comparator.comparing(Transaction::getAmount)) // Знаходимо максимальну витрату
                .map(Transaction::getAmount) // Витягуємо лише суму витрати
                .orElse(0.0); // Якщо не знайшло - повертаємо 0.0
    }

    public double findMinExpenseAmount(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, dateFormatter);
        LocalDate end = LocalDate.parse(endDate, dateFormatter);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Фільтруємо витрати (від'ємні суми)
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), dateFormatter);
                    return !date.isBefore(start) && !date.isAfter(end); // Фільтруємо по датах
                })
                .min(Comparator.comparing(Transaction::getAmount)) // Знаходимо мінімальну витрату
                .map(Transaction::getAmount) // Витягуємо лише суму витрати
                .orElse(0.0); // Якщо не знайшло - повертаємо 0.0
    }
}
