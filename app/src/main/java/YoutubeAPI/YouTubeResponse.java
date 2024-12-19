package YoutubeAPI;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class YouTubeResponse {
    @SerializedName("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public static class Item {
        @SerializedName("snippet")
        private Snippet snippet;

        @SerializedName("contentDetails")
        private ContentDetails contentDetails;

        public ContentDetails getContentDetails() {
            return contentDetails;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public static class Snippet {
            @SerializedName("title")
            private String title;

            @SerializedName("description")
            private String description;
            @SerializedName("channelTitle")
            private String artist;

            public String getArtist() {
                return artist;
            }


            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }
        }

        public static class ContentDetails{
            @SerializedName("duration")
            private String duration;

            public StringBuilder getDuration() {
                StringBuilder actualDuration = new StringBuilder();
                String x = duration;
                char[] c = x.toCharArray();
                String min = "", sec="";
                int j=0;
                for(int i=0; i<c.length; i++){
                    if(c[i]=='M'){
                        min=String.valueOf(c[j-2])+String.valueOf(c[j-1]);
                        if(Integer.parseInt(min.replaceAll("[^0-9]", ""))<10){
                            min="0"+String.valueOf(c[j-1]);
                        }
                    }
                    if(c[i]=='S'){
                        sec=String.valueOf(c[j-2])+String.valueOf(c[j-1]);
                        if(Integer.parseInt(sec.replaceAll("[^0-9]", ""))<10){
                            sec="0"+String.valueOf(c[j-1]);
                        }
                    }
                    j++;
                }
                if (sec.isEmpty()){
                    sec = "00";
                }
                actualDuration.append(min).append(":").append(sec);
                return actualDuration;
            }

        }
    }
}