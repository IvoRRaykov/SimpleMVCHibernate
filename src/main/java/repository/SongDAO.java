package repository;

import model.Song;

import java.util.List;

/**
 * Created by Ivo Raykov on 27.10.2016 г..
 */
public interface SongDAO {

    void createSong(Song song);
    List<Song> findSongs(String code);

}
