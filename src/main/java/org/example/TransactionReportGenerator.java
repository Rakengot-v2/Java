package org.example;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }
    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printCategoryExpenseReport(List<Transaction> transactions) {
        Map<String, Double> expensesByCategory = new HashMap<>();

        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getAmount() < 0) {
                String description = transaction.getDescription();
                double amount = transaction.getAmount();
                if (expensesByCategory.containsKey(description)) {
                    expensesByCategory.put(description, expensesByCategory.get(description) + amount);
                } else {
                    expensesByCategory.put(description, amount);
                }
            }
        }

        System.out.println("Звіт за витратами по категоріях:");
        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) {
            int symbolCount = (int) Math.abs(entry.getValue() / 1000);
            System.out.println(entry.getKey() + ": " + entry.getValue() + " грн " + "*".repeat(symbolCount));
        }
    }

    public static void printMonthlyExpenseReport(List<Transaction> transactions) {
        Map<String, Double> expensesByMonth = new HashMap<>();

        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getAmount() < 0) {
                String dateString = transaction.getDate();
                LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String monthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                double amount = transaction.getAmount();
                if (expensesByMonth.containsKey(monthYear)) {
                    expensesByMonth.put(monthYear, expensesByMonth.get(monthYear) + amount);
                } else {
                    expensesByMonth.put(monthYear, amount);
                }
            }
        }

        System.out.println("Звіт за витратами по місяцях:");
        for (Map.Entry<String, Double> entry : expensesByMonth.entrySet()) {
            int symbolCount = (int) Math.abs(entry.getValue() / 1000);
            System.out.println(entry.getKey() + ": " + entry.getValue() + " грн " + "*".repeat(symbolCount));
        }
    }

}
