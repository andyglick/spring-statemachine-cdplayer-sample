package org.zrgs.spring.statemachine.cdplayer;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author glick
 */
public class CdTest {

  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Test
  public void testCd() {

    Track bellBottomBlues = new Track("Bell Bottom Blues", 6000l);
    Track layla = new Track("Layla", 12000l);

    Cd laylaAlbum = new Cd("Layla and Other Forgotten Love Songs", bellBottomBlues, layla);

    assertThat(laylaAlbum.toString()).isNotBlank();
  }
}
