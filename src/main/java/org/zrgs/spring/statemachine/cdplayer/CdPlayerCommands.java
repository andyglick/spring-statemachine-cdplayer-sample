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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"unused", "SpringAutowiredFieldsWarningInspection"})
@Component
public class CdPlayerCommands implements CommandMarker {

  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private CdPlayer cdPlayer;

	private MusicLibrary musicLibrary;

	@Inject
	public CdPlayerCommands(CdPlayer cdPlayer, MusicLibrary musicLibrary) {
	  this.cdPlayer = cdPlayer;
	  this.musicLibrary = musicLibrary;
  }

	@CliCommand(value = "cd lcd", help = "Prints CD player lcd info")
	public String lcd() {
		return cdPlayer.getLdcStatus();
	}

	@CliCommand(value = "cd musicLibrary", help = "List user CD musicLibrary")
	public String library() {
		SimpleDateFormat format = new SimpleDateFormat("mm:ss");
		StringBuilder buf = new StringBuilder();
		int i1 = 0;
		for (Cd cd : musicLibrary.getCollection()) {
			buf.append(i1++).append(": ")
				.append(cd.getName())
				.append("\n");
			int i2 = 0;
			for (Track track : cd.getTracks()) {
				buf.append("  ")
					.append(i2++)
					.append(": ")
					.append(track.getName())
					.append("  ")
					.append(format.format(new Date(track.getLength() * 1000)))
					.append("\n");
			}
		}
		return buf.toString();
	}

	@CliCommand(value = "cd load", help = "Load CD into player")
	public String load(@CliOption(key = {"", "index"}) int index) {
		StringBuilder buf = new StringBuilder();
		try {
			Cd cd = musicLibrary.getCollection().get(index);
			cdPlayer.load(cd);
			buf.append("Loading cd ")
				.append(cd);
		} catch (Exception e) {
			buf.append("Cd with index ")
				.append(index)
				.append(" not found, check musicLibrary");
		}
		return buf.toString();
	}

	@CliCommand(value = "cd play", help = "Press player play button")
	public void play() {
		cdPlayer.play();
	}

	@CliCommand(value = "cd stop", help = "Press player stop button")
	public void stop() {
		cdPlayer.stop();
	}

	@CliCommand(value = "cd pause", help = "Press player pause button")
	public void pause() {
		cdPlayer.pause();
	}

	@CliCommand(value = "cd eject", help = "Press player eject button")
	public void eject() {
		cdPlayer.eject();
	}

	@CliCommand(value = "cd forward", help = "Press player forward button")
	public void forward() {
		cdPlayer.forward();
	}

	@CliCommand(value = "cd back", help = "Press player back button")
	public void back() {
		cdPlayer.back();
	}
}
