import com.example.Transaction;
import com.example.TransactionCSVReader;
import com.example.TransactionReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionCSVReaderTest {

    @Test
    void testReadTransactions() throws IOException {
        // 1. Створюємо тимчасовий файл CSV
        File tempFile = File.createTempFile("test-transactions", ".csv");
        tempFile.deleteOnExit(); // Видалити файл після завершення тесту

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("01-01-2023,-5000.0,Food\n");
            writer.write("02-01-2023,1000.0,Salary\n");
            writer.write("03-01-2023,-150.0,Transport\n");
        }

        // 2. Читаємо дані з CSV через TransactionCSVReader
        TransactionReader csvReader = new TransactionCSVReader();
        List<Transaction> transactions = csvReader.readTransactions(tempFile.toURI().toString());

        // 3. Перевіряємо, що транзакції прочитані коректно
        assertNotNull(transactions, "Транзакції не повинні бути null");
        assertEquals(3, transactions.size(), "Повинно бути 3 транзакції");

        // 4. Перевіряємо деталі першої транзакції
        Transaction transaction1 = transactions.get(0);
        assertEquals("01-01-2023", transaction1.getDate());
        assertEquals(-5000.0, transaction1.getAmount());
        assertEquals("Food", transaction1.getDescription());
    }
}
