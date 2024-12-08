package com.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        TransactionReader reader = new TransactionCSVReader();
        List<Transaction> transactions = reader.readTransactions(filePath);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();

        double totalBalance = analyzer.calculateTotalBalance();
        reportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = analyzer.countTransactionsByMonth(monthYear);
        reportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = analyzer.findTopExpenses();
        reportGenerator.printTopExpensesReport(topExpenses);

        double maxExpense = analyzer.findMaxExpenseAmount("01-01-2023", "31-01-2024");
        double minExpense = analyzer.findMinExpenseAmount("01-01-2023", "31-01-2024");
        System.out.println("Найбільші витрати: " + maxExpense);
        System.out.println("Найменші витрати: " + minExpense);

        reportGenerator.printCategoryAndMonthlyReport(transactions);
    }
}
