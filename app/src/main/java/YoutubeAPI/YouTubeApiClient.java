package YoutubeAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YouTubeApiClient {
    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)  // YouTube API base URL
                    .addConverterFactory(GsonConverterFactory.create()) // Converts JSON to Java
                    .build();
        }
        return retrofit;
    }
}