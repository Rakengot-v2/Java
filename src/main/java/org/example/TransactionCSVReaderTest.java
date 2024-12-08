package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

class TransactionCSVReaderTest {

    @Test
    public void testReadTransactions() {
        String mockCSVData = "01-01-2023,100.0,Income\n" +
                "02-01-2023,-200.0,Groceries\n" +
                "03-01-2023,-50.0,Coffee";

        try (BufferedReader br = new BufferedReader(new StringReader(mockCSVData))) {
            List<Transaction> transactions = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Transaction transaction = new Transaction(values[0], Double.parseDouble(values[1]), values[2]);
                transactions.add(transaction);
            }

            Assertions.assertNotNull(transactions, "Список транзакцій не повинен бути null");
            Assertions.assertTrue(transactions.size() > 0, "Список транзакцій не повинен бути порожнім");

            Transaction firstTransaction = transactions.get(0);
            Assertions.assertEquals("01-01-2023", firstTransaction.getDate(), "Неправильна дата");
            Assertions.assertEquals(100.0, firstTransaction.getAmount(), "Неправильна сума");
            Assertions.assertEquals("Income", firstTransaction.getDescription(), "Неправильний опис");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}