package org.zrgs.spring.statemachine.cdplayer;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author glick
 */
@SuppressWarnings("WeakerAccess")
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
public class CdPlayerCommandsTest
{
  @Test
  public void dummyTest()
  {
  }

  CdPlayerCommands cdPlayerCommands;

  CdPlayer cdPlayer;
  Track track;
  Track[] tracks = new Track[1];

  Cd cd;

  @Before
  public void setUp()
  {
    cdPlayer = new CdPlayer();

    assertThat(cdPlayer).isNotNull();

    track = new Track("Supreme Love", 2000);
    tracks[0] = track;

    cd = new Cd("A Love Supreme", tracks);

    cdPlayerCommands = new CdPlayerCommands(cdPlayer, MusicLibrary.buildSampleLibrary());
  }

  @Test
  public void lcdTest() {
    assertThat(cdPlayerCommands).isNotNull();
  }

  @Test
  public void libraryTest() {
    cdPlayer = new CdPlayer();
  }

  @Test
  public void load() {
    assertThat(cdPlayer).isNotNull();

    assertThat(cd).isNotNull();

    cdPlayer.load(cd);
  }
//
//  @Test
//  public void play() throws Exception
//  {
//
//  }
//
//  @Test
//  public void stop() throws Exception
//  {
//
//  }
//
//  @Test
//  public void pause() throws Exception
//  {
//
//  }
//
//  @Test
//  public void eject() throws Exception
//  {
//
//  }
//
//  @Test
//  public void forward() throws Exception
//  {
//
//  }
//
//  @Test
//  public void back() throws Exception
//  {
//
//  }
}
