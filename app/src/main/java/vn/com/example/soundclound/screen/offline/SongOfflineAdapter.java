package vn.com.example.soundclound.screen.offline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.com.example.soundclound.R;
import vn.com.example.soundclound.data.model.Constants;
import vn.com.example.soundclound.data.model.utils.SongsOffline;

public class SongOfflineAdapter extends RecyclerView.Adapter<SongOfflineAdapter.ViewHolderSong>{

    private Context mContext;
    private List<SongsOffline> mSongs;
    private InterfaceSong mInterfaceSong;
    private int mPositionClick = -1;

    public SongOfflineAdapter(Context context, InterfaceSong interfaceSong, List<SongsOffline> songs){
        this.mContext = context;
        this.mInterfaceSong = interfaceSong;
        this.mSongs = songs;
    }

    @NonNull
    @Override
    public ViewHolderSong onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_song, viewGroup, false);
        return new ViewHolderSong(view);
    }

    public void setSongs(List<SongsOffline> songs) {
        this.mSongs = songs;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSong viewHolderSong, final int position) {
        SongsOffline itemSong = mSongs.get(position);
        viewHolderSong.mTextNameSong.setText(itemSong.getNameSong());
        viewHolderSong.mTextNameSinger.setText(itemSong.getNameSinger());
        viewHolderSong.mTextNumber.setText((position+1) + Constants.STRING_EMPTY);
        viewHolderSong.mLinearSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterfaceSong.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInterfaceSong.getCount();
    }

    public class ViewHolderSong extends RecyclerView.ViewHolder{

        private TextView mTextNameSong;
        private TextView mTextNameSinger;
        private TextView mTextNumber;
        private LinearLayout mLinearSong;

        public ViewHolderSong(@NonNull View itemView) {
            super(itemView);
            mTextNameSong = itemView.findViewById(R.id.text_name_song);
            mTextNameSinger = itemView.findViewById(R.id.text_name_singer);
            mTextNumber = itemView.findViewById(R.id.text_number);
            mLinearSong = itemView.findViewById(R.id.linear_song);
        }
    }

    public interface InterfaceSong{
        int getCount();
        void onClick(int position);
    }

    public void setCurrentPosition(int position){
        mPositionClick = position;
    }
}
