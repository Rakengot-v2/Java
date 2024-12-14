package org.example;

public class Automat {
    public enum State {
        S, ONE, TWO, THREE, F
    }

    private State currentState;

    public Automat() {
        this.currentState = State.S;
    }

    public void process(char input) {
        switch (currentState) {
            case S -> currentState = (input == 'T') ? State.ONE : State.S;
            case ONE -> currentState = (input == 'E') ? State.TWO : (input == 'T') ? State.ONE : State.S;
            case TWO -> currentState = (input == 'S') ? State.THREE : (input == 'T') ? State.ONE : State.S;
            case THREE -> currentState = input == 'T' ? State.F : State.S;

        }
    }


    public State run(String input) {
        for (char c : input.toCharArray()) {
            process(c);
        }
        return currentState;
    }

}
