package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("01-01-2023", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("02-01-2023", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("03-01-2023", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth("02-2023", transactions);
        int countMar = TransactionAnalyzer.countTransactionsByMonth("03-2023", transactions);
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-01-2023", -5000, "Rent"),
                new Transaction("02-01-2023", -2000, "Groceries"),
                new Transaction("03-01-2023", -1500, "Utilities"),
                new Transaction("04-01-2023", 1000, "Income"),
                new Transaction("05-01-2023", -100, "Coffee"),
                new Transaction("06-01-2023", -2500, "Transport"),
                new Transaction("07-01-2023", -3000, "Shopping"),
                new Transaction("08-01-2023", -800, "Snacks"),
                new Transaction("09-01-2023", -600, "Parking"),
                new Transaction("10-01-2023", -50, "Water"),
                new Transaction("11-01-2023", -450, "Fitness"),
                new Transaction("12-01-2023", -500, "Books")
        );


        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(10, topExpenses.size(), "Список топ витрат повинен містити 10 транзакцій");

        Assertions.assertEquals(-5000, topExpenses.get(0).getAmount(), "Неправильна найбільша витрата");
    }
}