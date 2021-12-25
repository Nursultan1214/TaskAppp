package space.abdilazov.taskappp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import space.abdilazov.taskappp.models.News;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Update
    void update(News news);

}
