import com.example.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        assertEquals(5.0, calculator.add(2.0, 3.0), 0.0001);
    }

    @Test
    void testSubtract() {
        assertEquals(1.0, calculator.subtract(3.0, 2.0), 0.0001);
    }

    @Test
    void testMultiply() {
        assertEquals(6.0, calculator.multiply(2.0, 3.0), 0.0001);
    }

    @Test
    void testDivide() {
        assertEquals(2.0, calculator.divide(6.0, 3.0), 0.0001);
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(6.0, 0.0);
        });
        assertEquals("Ділення на нуль неможливе.", exception.getMessage());
    }
}
