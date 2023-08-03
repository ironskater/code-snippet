package com.prototype.state_machine.impl;

import com.prototype.state_machine.State;
import com.prototype.state_machine.Transition;

public class TransitionImpl implements Transition {

    private String rule;
    private State nextState;

    public TransitionImpl(String rule, State nextState) {
        this.rule = rule;
        this.nextState = nextState;
    }

    @Override
    public boolean isPossible(CharSequence input) {
        return this.rule.equalsIgnoreCase(String.valueOf(input));
    }

    @Override
    public State nextState() {
        return this.nextState;
    }
}
