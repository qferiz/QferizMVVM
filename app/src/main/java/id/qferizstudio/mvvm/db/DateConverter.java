package id.qferizstudio.mvvm.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Qferiz on 23/05/2017.
 */

public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
