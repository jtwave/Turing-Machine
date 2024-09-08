import java.io.*;
import java.util.*;

public class taylor_p1 {
    // Class to represent a state transition
    static class Transition {
        int nextState;
        char writeSymbol;
        char direction;  // 'L', 'R', 'S'

        Transition(int nextState, char writeSymbol, char direction) {
            this.nextState = nextState;
            this.writeSymbol = writeSymbol;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: java Taylor_p1 <machine_definition> <input_string> <max_transitions>");
            return;
        }

        // Reading inputs
        String machineDefinitionFile = args[0];
        String inputString = args[1];
        int maxTransitions = Integer.parseInt(args[2]);

        // Store states and transitions
        Set<Integer> acceptStates = new HashSet<>();
        Set<Integer> rejectStates = new HashSet<>();
        Map<Integer, Map<Character, Transition>> transitions = new HashMap<>();
        int startState = -1;

        // Read machine definition
        try (BufferedReader reader = new BufferedReader(new FileReader(machineDefinitionFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("state")) {
                    // Parse state line
                    String[] parts = line.split("\t");
                    int state = Integer.parseInt(parts[1]);
                    if (parts.length > 2) {
                        switch (parts[2]) {
                            case "start":
                                startState = state;
                                break;
                            case "accept":
                                acceptStates.add(state);
                                break;
                            case "reject":
                                rejectStates.add(state);
                                break;
                        }
                    }
                } else if (line.startsWith("transition")) {
                    // Parse transition line
                    String[] parts = line.split("\t");
                    int currentState = Integer.parseInt(parts[1]);
                    char readSymbol = parts[2].charAt(0);
                    int nextState = Integer.parseInt(parts[3]);
                    char writeSymbol = parts[4].charAt(0);
                    char direction = parts[5].charAt(0);

                    // Add the transition
                    transitions.putIfAbsent(currentState, new HashMap<>());
                    transitions.get(currentState).put(readSymbol, new Transition(nextState, writeSymbol, direction));
                }
            }
        }

        // Initialize Turing machine tape
        List<Character> tape = new ArrayList<>();
        for (char c : inputString.toCharArray()) {
            tape.add(c);
        }

        // Fill tape with blank space (denoted by '_') for further operations
        tape.add('_');

        int currentState = startState;
        int tapeIndex = 0;
        int transitionCount = 0;

        // Run the Turing machine
        while (transitionCount < maxTransitions) {
            if (acceptStates.contains(currentState)) {
                printResult(tape, tapeIndex, "accept");
                return;
            }
            if (rejectStates.contains(currentState)) {
                printResult(tape, tapeIndex, "reject");
                return;
            }

            // Get the current symbol under the head
            char currentSymbol = tape.get(tapeIndex);

            // Check if a transition exists for the current state and symbol
            Map<Character, Transition> stateTransitions = transitions.get(currentState);
            if (stateTransitions == null || !stateTransitions.containsKey(currentSymbol)) {
                printResult(tape, tapeIndex, "reject");
                return;
            }

            // Execute the transition
            Transition transition = stateTransitions.get(currentSymbol);
            tape.set(tapeIndex, transition.writeSymbol);
            currentState = transition.nextState;

            // Move the tape head
            switch (transition.direction) {
                case 'L':
                    tapeIndex--;
                    break;
                case 'R':
                    tapeIndex++;
                    if (tapeIndex == tape.size()) {
                        tape.add('_');  // Extend tape to the right if needed
                    }
                    break;
                case 'S':
                    // Stay in the same position
                    break;
            }

            transitionCount++;
        }

        // If max transitions exceeded
        printResult(tape, tapeIndex, "quit");
    }

    // Helper function to print the final result
    private static void printResult(List<Character> tape, int tapeIndex, String result) {
        // Output all symbols from the current tape position until a blank is encountered
        StringBuilder output = new StringBuilder();
        while (tapeIndex < tape.size() && tape.get(tapeIndex) != '_') {
            output.append(tape.get(tapeIndex));
            tapeIndex++;
        }
        System.out.println(output + " " + result);
    }
}
