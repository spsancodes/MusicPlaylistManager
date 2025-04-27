import java.util.ArrayList;
import java.util.List;

public class PlaylistManager {
    private List<Playlist> playlists;

    public PlaylistManager() {
        playlists = new ArrayList<>();
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlists;
    }

    public Playlist getPlaylistByName(String name) {
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
}
