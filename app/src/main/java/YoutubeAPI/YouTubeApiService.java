package YoutubeAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouTubeApiService {

    @GET("videos")
        //YouTubeResponse is a class on step 4
    Call<YouTubeResponse> getVideoDetails(
            @Query("part") String part,      // Data type you want (snippet)
            @Query("id") String videoId,     // YouTube video ID
            @Query("key") String apiKey      // Your API key
    );
}