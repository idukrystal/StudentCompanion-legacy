package com.crysapp.student.models.db;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.crysapp.student.models.Class;
import java.util.List;

@Dao
public interface ClassDao {
    @Query("SELECT * FROM class WHERE courseId = : cid")
    LiveData<List<Class>> selectFrom(final long cid);
    
      @Insert long insert(Class course);
    @Delete void delete(Class course);
    @Update void update(Class course);
}