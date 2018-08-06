package vn.com.example.soundclound.screen.offline;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.com.example.soundclound.R;
import vn.com.example.soundclound.data.model.Constants;
import vn.com.example.soundclound.data.model.source.SongDataSource.ActionWithSongs;
import vn.com.example.soundclound.data.model.source.remote.LoadSongsAsyncTask;
import vn.com.example.soundclound.data.model.utils.SongsOffline;

public class OfflineFragment extends Fragment implements SongOfflineAdapter.InterfaceSong {

    private RecyclerView mRecyclerviewSong;
    private Spinner mSpinnerTypeSong;
    private List<SongsOffline> mSongs;
    public static SongOfflineAdapter songAdapter;
//    private String[] mPermision = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_offline, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
//        checkPermission();
        setRecyclerView();
        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.type_song, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        mSpinnerTypeSong.setAdapter(adapter);
        mSpinnerTypeSong.setOnItemSelectedListener(new SpinnerItemSelected());
    }

    private void initViews(View view) {
        mRecyclerviewSong = view.findViewById(R.id.recycler_song);
        mSpinnerTypeSong = view.findViewById(R.id.spinner_type);
    }

    private void setRecyclerView() {
        mSongs = new ArrayList<>();
        songAdapter = new SongOfflineAdapter(getContext(), this, mSongs);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setStackFromEnd(true);
        mRecyclerviewSong.setLayoutManager(manager);
        new LoadSongsAsyncTask(getContext(), mActionWithSongs).execute();
    }

    private ActionWithSongs mActionWithSongs = new ActionWithSongs() {
        @Override
        public void loadSongs(List<SongsOffline> songs) {
            mSongs = songs;
            songAdapter.setSongs(mSongs);
            mRecyclerviewSong.setAdapter(songAdapter);
            songAdapter.notifyDataSetChanged();
        }

        @Override
        public void updateSeekBar(int progress, int duration) {
        }

        @Override
        public void updateStatus(boolean isPlaying) {

        }

        @Override
        public void updateTime(String time) {
        }
    };

    @Override
    public int getCount() {
        return mSongs == null ? 0 : mSongs.size();
    }

    @Override
    public void onClick(int position) {
    }

}
