package com.prototype.json_format_checker;

import com.prototype.state_machine.FiniteStateMachine;
import com.prototype.state_machine.State;

public class JsonFormatChecker implements FiniteStateMachine {

    private State currentState;

    public JsonFormatChecker(State initial) {
        this.currentState = initial;
    }

    @Override
    public FiniteStateMachine switchState(CharSequence input) {
        return new JsonFormatChecker(this.currentState.transit(input));
    }

    @Override
    public boolean canStop() {
        return this.currentState.isFinal();
    }

    public static FiniteStateMachine buildJsonFormatChecker() {
        return null;
    }
}
