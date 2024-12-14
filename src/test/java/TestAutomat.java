import org.example.Automat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAutomat {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testAutomat(String input, Automat.State expectedState) {
        Automat automaton = new Automat();
        assertEquals(expectedState, automaton.run(input));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("TESTT", Automat.State.F),         // Перевірка додаткового символу після TEST
                Arguments.of("abcTESTabc", Automat.State.F),    // TEST у середині рядка
                Arguments.of("abcTES", Automat.State.THREE),    // Частковий збіг до стану THREE
                Arguments.of("randomString", Automat.State.S),  // Випадковий рядок, немає збігу
                Arguments.of("", Automat.State.S),              // Порожній рядок
                Arguments.of("TESTTEST", Automat.State.F),      // Повторюваний шаблон
                Arguments.of("T", Automat.State.ONE),           // Лише перший символ "T"
                Arguments.of("TE", Automat.State.TWO),          // Лише перші два символи "TE"
                Arguments.of("TES", Automat.State.THREE),       // Лише перші три символи "TES"
                Arguments.of("TETEST", Automat.State.F),        // Вкладені шаблони
                Arguments.of("TTEST", Automat.State.F),         // Додатковий символ перед TEST
                Arguments.of("a", Automat.State.S),             // Невідповідний перший символ
                Arguments.of("TA", Automat.State.S),            // Неправильна послідовність після "T"
                Arguments.of("TEA", Automat.State.S),           // Неправильна послідовність після "TE"
                Arguments.of("TESA", Automat.State.S)           // Неправильна послідовність після "TES"
        );
    }
}

