package vn.com.example.soundclound.data.source.remote;

import vn.com.example.soundclound.data.model.entity.Song;

public class SongRemoteDataSource implements DataSource.RemoteDataSource {
    private static SongRemoteDataSource sSongRemoteDataSource;

    public static SongRemoteDataSource getInstance() {
        if (null == sSongRemoteDataSource) {
            sSongRemoteDataSource = new SongRemoteDataSource();
        }
        return sSongRemoteDataSource;
    }

    @Override
    public void getSongRemote(DataSource.OnFetchDataListener<Song> listener, String url) {
        new GetDataApiSoundCloundAsynTask(listener).execute(url);
    }
}
