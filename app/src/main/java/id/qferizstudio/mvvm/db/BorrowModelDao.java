package id.qferizstudio.mvvm.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import java.util.List;

/**
 * Created by Qferiz on 23/05/2017.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface BorrowModelDao {

    @Query("select * from BorrowModel")
    LiveData<List<BorrowModel>> getAllBorrowedItems();

    @Query("select * from BorrowModel where id = :id")
    BorrowModel getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addBorrow(BorrowModel borrowModel);

    @Delete
    void deleteBorrow(BorrowModel borrowModel);

}
