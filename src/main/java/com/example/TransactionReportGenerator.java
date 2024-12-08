package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionReportGenerator {

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public void printCategoryAndMonthlyReport(List<Transaction> transactions) {
        List<Transaction> expenses = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.toList());

        Map<String, Double> categoryTotals = expenses.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        Map<String, Double> monthlyTotals = expenses.stream()
                .collect(Collectors.groupingBy(
                        t -> {
                            LocalDate date = LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                            return date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                        },
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        System.out.println("Витрати по категоріях:");
        categoryTotals.forEach((category, total) -> {
            System.out.print(category + ": ");
            System.out.println("*".repeat((int) Math.abs(total / 1000)));
        });

        System.out.println("\nВитрати по місяцях:");
        monthlyTotals.forEach((month, total) -> {
            System.out.print(month + ": ");
            System.out.println("*".repeat((int) Math.abs(total / 1000)));
        });
    }
}
