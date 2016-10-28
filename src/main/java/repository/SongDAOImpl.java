package repository;

import model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDAOImpl implements SongDAO {


    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    public void createSong(Song song) {

        this.sessionFactory.getCurrentSession().persist(song);

        logger.info("Song saved successfully, Song Details =" + song.toString());
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Song> findSongs(String code) {

        Session session = this.sessionFactory.getCurrentSession();

        List<Song> songsList = session.createQuery("from Song where product_code=:code")
                .setString("code", code )
                .list();

        logger.info("Songs List obtained successfully");
       /* for (Song song : songsList) {
            logger.info("  song = " + song.toString());
        }*/

        return songsList;
    }
}
