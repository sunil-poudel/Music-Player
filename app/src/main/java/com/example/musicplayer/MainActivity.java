package com.example.musicplayer;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.SongsAdapter;
import Model.SongsList;
import YoutubeAPI.VideoId;
import YoutubeAPI.YouTubeApiClient;
import YoutubeAPI.YouTubeApiService;
import YoutubeAPI.YouTubeResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private RecyclerView songsRecycler;
    private Adapter.SongsAdapter songsAdapter;
    private List<SongsList> songsList;

    private static final String API_KEY = "AIzaSyDEUpouE2iN0FOB5btRODySxxZgvXH5veo";
    private String VIDEO_ID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songsRecycler = findViewById(R.id.songs_recycler);
        songsRecycler.setHasFixedSize(true);
        songsRecycler.setLayoutManager(new LinearLayoutManager(this));

        songsList = new ArrayList<>();

//        for(int i=0; i<10; i++){
//            SongsList list = new SongsList(i+"let it be", "The Beatles", 4400);
//            songsList.add(list);
//        }

        songsAdapter = new SongsAdapter(this, songsList);

        songsRecycler.setAdapter(songsAdapter);
        for(int i=0; i<34; i++){
            VIDEO_ID = VideoId.ids[i];
            // Create Retrofit client
            YouTubeApiService apiService = YouTubeApiClient.getClient().create(YouTubeApiService.class);

            // Make the API call
            Call<YouTubeResponse> call = apiService.getVideoDetails("snippet,contentDetails", VIDEO_ID, API_KEY);

            call.enqueue(new Callback<YouTubeResponse>() {
                @Override
                public void onResponse(@NonNull Call<YouTubeResponse> call, @NonNull Response<YouTubeResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        YouTubeResponse.Item.Snippet snippet = response.body().getItems().get(0).getSnippet();
                        YouTubeResponse.Item.ContentDetails contentDetails = response.body().getItems().get(0).getContentDetails();

                        SongsList list = new SongsList(snippet.getTitle(), snippet.getArtist(), contentDetails.getDuration());
                        songsList.add(list);

                        // Notify adapter of data change
                        runOnUiThread(() -> songsAdapter.notifyDataSetChanged());

//                    SongsList list = new SongsList(snippet.getTitle(), snippet.getTitle(), 1010);
//                    songsList.add(list);

//                    // Update UI with video details
//                    titleTextView.setText(snippet.getTitle());
//                    descriptionTextView.setText(snippet.getDescription());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<YouTubeResponse> call, @NonNull Throwable t) {
                    Log.e("API Error", "Error: " + t.getMessage());
                }
            });
        }


    }
}