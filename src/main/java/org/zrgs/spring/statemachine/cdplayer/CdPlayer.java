/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zrgs.spring.statemachine.cdplayer;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

@WithStateMachine
public class CdPlayer {

	@Inject
	private StateMachine<Application.States, Application.Events> stateMachine;

	private String cdStatus = "No CD";
	private String trackStatus = "";

	public void load(Cd cd) {
		stateMachine.sendEvent(MessageBuilder.withPayload(Application.Events.LOAD).setHeader(Application.Variables.CD.toString(), cd).build());
	}

	public void play() {
		stateMachine.sendEvent(Application.Events.PLAY);
	}

	public void stop() {
		stateMachine.sendEvent(Application.Events.STOP);
	}

	public void pause() {
		stateMachine.sendEvent(Application.Events.PAUSE);
	}

	public void eject() {
		stateMachine.sendEvent(Application.Events.EJECT);
	}

	public void forward() {
		stateMachine
			.sendEvent(MessageBuilder
					.withPayload(Application.Events.FORWARD)
					.setHeader(Application.Headers.TRACKSHIFT.toString(), 1).build());
	}

	public void back() {
		stateMachine
			.sendEvent(MessageBuilder
					.withPayload(Application.Events.BACK)
					.setHeader(Application.Headers.TRACKSHIFT.toString(), -1).build());
	}

	public String getLdcStatus() {
		return cdStatus + " " + trackStatus;
	}

//tag::snippetA[]
	@OnTransition(target = "BUSY")
	public void busy(ExtendedState extendedState) {
		Object cd = extendedState.getVariables().get(Application.Variables.CD);
		if (cd != null) {
			cdStatus = ((Cd)cd).getName();
		}
	}
//end::snippetA[]

	@Application.StatesOnTransition(target = Application.States.PLAYING)
	public void playing(ExtendedState extendedState) {
		Object elapsed = extendedState.getVariables().get(Application.Variables.ELAPSEDTIME);
		Object cd = extendedState.getVariables().get(Application.Variables.CD);
		Object track = extendedState.getVariables().get(Application.Variables.TRACK);
		if (elapsed instanceof Long && track instanceof Integer && cd instanceof Cd) {
			SimpleDateFormat format = new SimpleDateFormat("mm:ss");
			trackStatus = ((Cd) cd).getTracks()[((Integer) track)]
					+ " " + format.format(new Date((Long) elapsed));
		}
	}

	@Application.StatesOnTransition(target = Application.States.OPEN)
	public void open(ExtendedState extendedState) {
		cdStatus = "Open";
	}

//tag::snippetB[]
	@Application.StatesOnTransition(target = {Application.States.CLOSED, Application.States.IDLE})
	public void closed(ExtendedState extendedState) {
		Object cd = extendedState.getVariables().get(Application.Variables.CD);
		if (cd != null) {
			cdStatus = ((Cd)cd).getName();
		} else {
			cdStatus = "No CD";
		}
		trackStatus = "";
	}
//end::snippetB[]
}
