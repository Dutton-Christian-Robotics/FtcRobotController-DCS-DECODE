package org.firstinspires.ftc.teamcode.dcs15815.DefenderStateMachine;

import java.util.ArrayList;

public class DefenderState {
	protected String label;
	public DefenderState nextState;
	protected boolean isFinished;
	protected boolean isStarted;
	protected DefenderStateMachine stateMachine;
	protected String stateLabel;

	public DefenderState() {
		isFinished = false;
		isStarted = false;
	}

	public DefenderState(String label) {
		isFinished = false;
		isStarted = false;
		setStateLabel(label);
	}

	public DefenderState(DefenderStateMachine sm) {
		isFinished = false;
		isStarted = false;
		setStateMachine(sm);
	}

	public DefenderState(DefenderStateMachine sm, String label) {
		isFinished = false;
		isStarted = false;
		setStateLabel(label);
		setStateMachine(sm);
	}

	public DefenderStateMachine getStateMachine() {
		return stateMachine;
	}

	public void setStateMachine(DefenderStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	public boolean hasStateMachine() {
		return stateMachine != null;
	}

	public String getStateLabel() {
		return stateLabel;
	}

	public void setStateLabel(String l) {
		stateLabel = l;
	}


	public void beforeStart() { }

	public void beforeStop() {  }

	public void run() { }

	public boolean isFinished() {
		return isFinished;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setIsFinished(boolean b) {
		isFinished = b;
	}

	public void setIsStarted(boolean b) {
		isStarted = b;
	}

	public boolean hasNextState() {
		return nextState != null;
	}

	public void setNextState(DefenderState ns) {
		nextState = ns;
	}


	public void andThen(DefenderState ns) {
		nextState = ns;
	}

	public void andThenAfterPause(long wait, DefenderState ns) {
		WaitState waitState = new WaitState(wait);
		andThen(waitState);
		waitState.andThen(ns);
	}

//	public AndState and(DefenderState s) {
//		AndState newAndState = new AndState();
//		newAndState.addState(this);
//		newAndState.addState(s);
//		return newAndState;
//	}
//
//	public OrState or(DefenderState s) {
//		OrState newOrState = new OrState();
//		newOrState.addState(this);
//		newOrState.addState(s);
//		return newOrState;
//	}

	public void follows(DefenderState ... states) {
		for (DefenderState s : states) {
			s.andThen(this);
		}
	}

	public void followsAfterPause(long wait, DefenderState ... states) {
		for (DefenderState s : states) {
			s.andThenAfterPause(wait, this);
		}
	}

}
