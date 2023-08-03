package com.prototype.state_machine.impl;

import com.prototype.state_machine.State;
import com.prototype.state_machine.Transition;
import java.util.ArrayList;
import java.util.List;

public class StateImpl implements State {

    private List<Transition> transitions;
    private boolean isFinal;

    public StateImpl() {
        this(false);
    }

    public StateImpl(boolean isFinal) {
        this.transitions = new ArrayList<>();
        this.isFinal = isFinal;
    }

    @Override
    public State with(Transition tr) {
        this.transitions.add(tr);
        return this;
    }

    @Override
    public State transit(CharSequence input) {
        return this.transitions.stream()
            .filter(tr -> tr.isPossible(input))
            .map(Transition::nextState)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Input not accepted:" + input));
    }

    @Override
    public boolean isFinal() {
        return this.isFinal;
    }
}
