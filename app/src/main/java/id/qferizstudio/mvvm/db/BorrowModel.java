package id.qferizstudio.mvvm.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by Qferiz on 23/05/2017.
 */
@Entity
public class BorrowModel {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String itemName;
    private String personName;
    @TypeConverters(DateConverter.class)
    private Date borrowDate;

    public BorrowModel(int id, String itemName, String personName, Date borrowDate) {
        this.id = id;
        this.itemName = itemName;
        this.personName = personName;
        this.borrowDate = borrowDate;
    }

    public String getPersonName() {
        return personName;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public String getItemName() {
        return itemName;
    }
}
