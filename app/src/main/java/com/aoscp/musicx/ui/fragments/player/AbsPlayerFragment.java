package com.aoscp.musicx.ui.fragments.player;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.aoscp.musicx.dialogs.AddToPlaylistDialog;
import com.aoscp.musicx.dialogs.CreatePlaylistDialog;
import com.aoscp.musicx.dialogs.SleepTimerDialog;
import com.aoscp.musicx.dialogs.SongDetailDialog;
import com.aoscp.musicx.dialogs.SongShareDialog;
import com.aoscp.musicx.helper.MusicPlayerRemote;
import com.aoscp.musicx.interfaces.PaletteColorHolder;
import com.aoscp.musicx.model.Song;
import com.aoscp.musicx.ui.activities.tageditor.AbsTagEditorActivity;
import com.aoscp.musicx.ui.activities.tageditor.SongTagEditorActivity;
import com.aoscp.musicx.ui.fragments.AbsMusicServiceFragment;
import com.aoscp.musicx.util.MusicUtil;
import com.aoscp.musicx.util.NavigationUtil;

import org.omnirom.gramophone.R;

public abstract class AbsPlayerFragment extends AbsMusicServiceFragment implements Toolbar.OnMenuItemClickListener, PaletteColorHolder {
    public static final String TAG = AbsPlayerFragment.class.getSimpleName();

    private Callbacks callbacks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callbacks = (Callbacks) context;
        } catch (ClassCastException e) {
            throw new RuntimeException(context.getClass().getSimpleName() + " must implement " + Callbacks.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        final Song song = MusicPlayerRemote.getCurrentSong();
        switch (item.getItemId()) {
            case R.id.action_sleep_timer:
                new SleepTimerDialog().show(getFragmentManager(), "SET_SLEEP_TIMER");
                return true;
            case R.id.action_toggle_favorite:
                toggleFavorite(song);
                return true;
            case R.id.action_share:
                SongShareDialog.create(song).show(getFragmentManager(), "SHARE_SONG");
                return true;
            case R.id.action_equalizer:
                NavigationUtil.openEqualizer(getActivity());
                return true;
            case R.id.action_add_to_playlist:
                AddToPlaylistDialog.create(song).show(getFragmentManager(), "ADD_PLAYLIST");
                return true;
            case R.id.action_clear_playing_queue:
                MusicPlayerRemote.clearQueue();
                return true;
            case R.id.action_save_playing_queue:
                CreatePlaylistDialog.create(MusicPlayerRemote.getPlayingQueue()).show(getActivity().getSupportFragmentManager(), "ADD_TO_PLAYLIST");
                return true;
            case R.id.action_tag_editor:
                Intent intent = new Intent(getActivity(), SongTagEditorActivity.class);
                intent.putExtra(AbsTagEditorActivity.EXTRA_ID, song.id);
                startActivity(intent);
                return true;
            case R.id.action_details:
                SongDetailDialog.create(song).show(getFragmentManager(), "SONG_DETAIL");
                return true;
            case R.id.action_go_to_album:
                NavigationUtil.goToAlbum(getActivity(), song.albumId);
                return true;
            case R.id.action_go_to_artist:
                NavigationUtil.goToArtist(getActivity(), song.artistId);
                return true;
        }
        return false;
    }

    protected void toggleFavorite(Song song) {
        MusicUtil.toggleFavorite(getActivity(), song);
    }

    protected String getUpNextAndQueueTime() {
        return getResources().getString(R.string.up_next) + "  •  " + MusicUtil.getReadableDurationString(MusicPlayerRemote.getQueueDurationMillis(MusicPlayerRemote.getPosition()));
    }

    public abstract void onShow();

    public abstract void onHide();

    public abstract boolean onBackPressed();

    public Callbacks getCallbacks() {
        return callbacks;
    }

    public interface Callbacks {
        void onPaletteColorChanged();
    }
}
