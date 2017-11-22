package co.aoscp.musicx.helper.menu;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import co.aoscp.musicx.dialogs.AddToPlaylistDialog;
import co.aoscp.musicx.dialogs.DeleteSongsDialog;
import co.aoscp.musicx.helper.MusicPlayerRemote;
import co.aoscp.musicx.model.Song;

import co.aoscp.musicx.R;

import java.util.ArrayList;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public class SongsMenuHelper {
    public static boolean handleMenuClick(@NonNull FragmentActivity activity, @NonNull ArrayList<Song> songs, int menuItemId) {
        switch (menuItemId) {
            case R.id.action_play_next:
                MusicPlayerRemote.playNext(songs);
                return true;
            case R.id.action_add_to_current_playing:
                MusicPlayerRemote.enqueue(songs);
                return true;
            case R.id.action_add_to_playlist:
                AddToPlaylistDialog.create(songs).show(activity.getSupportFragmentManager(), "ADD_PLAYLIST");
                return true;
            case R.id.action_delete_from_device:
                DeleteSongsDialog.create(songs).show(activity.getSupportFragmentManager(), "DELETE_SONGS");
                return true;
        }
        return false;
    }
}
