package vn.com.example.soundclound.data.model.source.remote;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import vn.com.example.soundclound.data.model.Constants;
import vn.com.example.soundclound.data.model.source.SongDataSource.ActionWithSongs;
import vn.com.example.soundclound.data.model.utils.SongsOffline;

public class LoadSongsAsyncTask extends AsyncTask<Void, Void, List<SongsOffline>> {

    private Context mContext;
    private ActionWithSongs mLoadSongs;
    private List<SongsOffline> mSongs = new ArrayList<>();

    public LoadSongsAsyncTask(Context context, ActionWithSongs loadSongs){
        this.mContext = context;
        this.mLoadSongs = loadSongs;
    }


    @Override
    protected List<SongsOffline> doInBackground(Void... voids) {
        Uri uri = MediaStore.Files.getContentUri(Constants.EXTERNAL);
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        mSongs.clear();
        int idxPath = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
        int idxSong = cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String path = cursor.getString(idxPath);
            if (path.contains(Constants.MP3)) {
                if (path.contains(Constants.SPACE)) {
                    String informSong = cursor.getString(idxSong);
                    String[] name = informSong.split(Constants.SPACE);
                    String nameSong = name[Constants.ZERO];
                    String nameSinger = Constants.STRING_EMPTY;
                    if (name.length == Constants.TWO) {
                        nameSinger += name[Constants.ONE];
                    }
                    mSongs.add(new SongsOffline(nameSong, nameSinger, path));
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        return mSongs;
    }

    @Override
    protected void onPostExecute(List<SongsOffline> songs) {
        super.onPostExecute(songs);
        mLoadSongs.loadSongs(mSongs);
    }
}
