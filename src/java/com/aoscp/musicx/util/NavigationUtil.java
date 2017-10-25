package com.aoscp.musicx.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.aoscp.musicx.R;
import com.aoscp.musicx.helper.MusicPlayerRemote;
import com.aoscp.musicx.model.Playlist;
import com.aoscp.musicx.ui.activities.AlbumDetailActivity;
import com.aoscp.musicx.ui.activities.ArtistDetailActivity;
import com.aoscp.musicx.ui.activities.PlaylistDetailActivity;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public class NavigationUtil {

    public static void goToArtist(@NonNull final Activity activity, final int artistId, @Nullable Pair... sharedElements) {
        final Intent intent = new Intent(activity, ArtistDetailActivity.class);
        intent.putExtra(ArtistDetailActivity.EXTRA_ARTIST_ID, artistId);

        //noinspection unchecked
        activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedElements).toBundle());
    }

    public static void goToAlbum(@NonNull final Activity activity, final int albumId, @Nullable Pair... sharedElements) {
        final Intent intent = new Intent(activity, AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.EXTRA_ALBUM_ID, albumId);

        //noinspection unchecked
        activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedElements).toBundle());
    }

    public static void goToPlaylist(@NonNull final Activity activity, final Playlist playlist, @Nullable Pair... sharedElements) {
        final Intent intent = new Intent(activity, PlaylistDetailActivity.class);
        intent.putExtra(PlaylistDetailActivity.EXTRA_PLAYLIST, playlist);

        activity.startActivity(intent);
    }

    public static void openEqualizer(@NonNull final Activity activity) {
        final int sessionId = MusicPlayerRemote.getAudioSessionId();
        if (sessionId == AudioEffect.ERROR_BAD_VALUE) {
            Toast.makeText(activity, activity.getResources().getString(R.string.no_audio_ID), Toast.LENGTH_LONG).show();
        } else {
            try {
                final Intent effects = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
                effects.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, sessionId);
                effects.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC);
                activity.startActivityForResult(effects, 0);
            } catch (@NonNull final ActivityNotFoundException notFound) {
                Toast.makeText(activity, activity.getResources().getString(R.string.no_equalizer), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
