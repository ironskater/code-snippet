package com.prototype.json_format_checker;

import com.prototype.state_machine.FiniteStateMachine;
import com.prototype.state_machine.State;
import com.prototype.state_machine.impl.StateImpl;
import com.prototype.state_machine.impl.TransitionImpl;

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

        // {\"key\":\"value\"}

        State initialState = new StateImpl();
        State second = new StateImpl();
        State third = new StateImpl();
        State fourth = new StateImpl();
        State fifth = new StateImpl();
        State sixth = new StateImpl();
        State seventh = new StateImpl();
        State eighth = new StateImpl(true);

        initialState.with(new TransitionImpl("{", second));
        second.with(new TransitionImpl("\"", third));

        //Add transitions with chars 0-9 and a-z
        for (int i = 0; i < 26; i++) {
            if (i < 10) {
                third = third.with(new TransitionImpl(String.valueOf(i), third));
                sixth = sixth.with(new TransitionImpl(String.valueOf(i), sixth));
            }
            third = third.with(new TransitionImpl(String.valueOf((char) ('a' + i)), third));
            sixth = sixth.with(new TransitionImpl(String.valueOf((char) ('a' + i)), sixth));
        }

        third.with(new TransitionImpl("\"", fourth));
        fourth.with(new TransitionImpl(":", fifth));
        fifth.with(new TransitionImpl("\"", sixth));
        sixth.with(new TransitionImpl("\"", seventh));
        seventh.with(new TransitionImpl(",", second));
        seventh.with(new TransitionImpl("}", eighth));

        return new JsonFormatChecker(initialState);
    }
}
