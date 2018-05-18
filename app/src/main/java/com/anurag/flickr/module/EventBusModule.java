package com.anurag.flickr.module;

import de.greenrobot.event.EventBus;

/**
 * Library to facilitate communication between application components.
 */

public class EventBusModule {
    private static EventBus sEventBus = EventBus.builder().throwSubscriberException(true).build();

    public static EventBus eventBus() {
        return sEventBus;
    }
}
