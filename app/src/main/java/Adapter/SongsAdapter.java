package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;

import java.time.temporal.Temporal;
import java.util.List;

import Model.SongsList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder>{

    private Context context;
    private List<SongsList> songsList;

    public SongsAdapter(Context context, List<SongsList> songsList) {
        this.context = context;
        this.songsList = songsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_songs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            SongsList list = songsList.get(position);
            if(list.getTitle().contains("-")) {
                String[] songTitle = list.getTitle().split("-");
                String[] finalTitle;
                if (songTitle[1].contains(
                        "(Official Music Video)") || songTitle[1].contains("(Official Lyric Video)") || songTitle[1].contains("(Official Lyrics Video)")
                        || songTitle[1].contains("(Vertical Music Video)") || songTitle[1].contains("Official Video)") || songTitle[1].contains("(audio)")
                ) {
                    finalTitle = songTitle[1].split("\\(");
                    holder.title.setText(finalTitle[0]);
                } else {
                    finalTitle = songTitle;
                    holder.title.setText(finalTitle[1]);
                }
            } else{
                holder.title.setText(list.getTitle());
            }

            holder.leadArtist.setText(list.getLeadArtist());
            holder.duration.setText(String.valueOf(list.getDuration()));

    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView leadArtist;
        private TextView duration;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.song_title);
            leadArtist = itemView.findViewById(R.id.song_lead_artist);
            duration = itemView.findViewById(R.id.song_duration);
        }
    }
}
