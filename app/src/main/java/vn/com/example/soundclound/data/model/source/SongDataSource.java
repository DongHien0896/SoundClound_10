package vn.com.example.soundclound.data.model.source;

import java.util.List;

import vn.com.example.soundclound.data.model.utils.SongsOffline;

public interface SongDataSource {

    interface Callback<T> {

        void onStartLoading();

        void onLoaded(T data);

        void onDataNotAvailable(Exception exception);

        void onComplete();
    }

    interface ActionWithSongs {

        void loadSongs(List<SongsOffline> songs);

        void updateSeekBar(int progress, int duration);

        void updateStatus(boolean isPlaying);

        void updateTime(String time);
    }
}
