package com.prototype.json_format_checker;

import com.prototype.state_machine.FiniteStateMachine;

/**
 * Refer to https://www.baeldung.com/java-finite-automata
 * Validating Input with Finite Automata in Java
 */
public class Application
{
    public static void main(String[] args) {

        String json = "{\"key\":\"value\"}";

        FiniteStateMachine machine = JsonFormatChecker.buildJsonFormatChecker();

        for (int ix = 0; ix < json.length(); ix++) {
            machine = machine.switchState(String.valueOf(json.charAt(ix)));
        }

        if (machine.canStop()) {
            System.out.println("The json format is right");
        } else {
            System.out.println("The json format is wrong");
        }
    }
}
