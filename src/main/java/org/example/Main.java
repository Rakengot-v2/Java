package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        if (transactions == null || transactions.isEmpty()) {
            System.out.println("Не вдалося зчитати транзакції або файл порожній.");
            return;
        }

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "02-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(monthYear, transactions);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        TransactionReportGenerator.printCategoryExpenseReport(transactions);

        TransactionReportGenerator.printMonthlyExpenseReport(transactions);
    }
}