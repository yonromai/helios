/*
 * Copyright (c) 2016 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.helios.servicescommon;

import com.google.cloud.pubsub.Message;
import com.google.cloud.pubsub.PubSub;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class GooglePubSubSenderTest {
  private PubSub pubsub;
  private String prefix = "prefix.";
  private GooglePubSubSender sender;

  @Before
  public void setUp() {
    pubsub = Mockito.mock(PubSub.class);
    sender = new GooglePubSubSender(pubsub, prefix);
  }

  @Test
  public void testSend() throws Exception {
    final String topic = "Event";
    sender.send(topic, new byte['x']);

    verify(pubsub).publishAsync(eq(prefix + topic), any(Message.class));
  }
}
