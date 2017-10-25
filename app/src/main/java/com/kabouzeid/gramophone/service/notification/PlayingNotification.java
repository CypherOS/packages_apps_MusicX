package com.aoscp.musicx.service.notification;

import com.aoscp.musicx.service.MusicService;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */

public interface PlayingNotification {
    int NOTIFICATION_ID = 1;

    void init(MusicService service);

    void update();

    void stop();
}
