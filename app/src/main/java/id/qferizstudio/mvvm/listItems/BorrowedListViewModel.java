package id.qferizstudio.mvvm.listItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import id.qferizstudio.mvvm.db.AppDatabase;
import id.qferizstudio.mvvm.db.BorrowModel;

/**
 * Created by Qferiz on 23/05/2017.
 */

public class BorrowedListViewModel extends AndroidViewModel {

    private final LiveData<List<BorrowModel>> itemAndPersonList;

    private AppDatabase appDatabase;

    public BorrowedListViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        itemAndPersonList = appDatabase.itemAndPersonModel().getAllBorrowedItems();
    }

    public LiveData<List<BorrowModel>> getItemAndPersonList() {
        return itemAndPersonList;
    }

    public void deleteItem(final int position) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.itemAndPersonModel().deleteBorrow(itemAndPersonList.getValue().get(position));
            }
        });

        thread.start();
    }
}
