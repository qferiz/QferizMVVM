package id.qferizstudio.mvvm.addItems;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import id.qferizstudio.mvvm.db.AppDatabase;
import id.qferizstudio.mvvm.db.BorrowModel;

/**
 * Created by Qferiz on 24/05/2017.
 */

public class AddBorrowViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddBorrowViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());
    }

    public void addBorrow(final BorrowModel borrowModel) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.itemAndPersonModel().addBorrow(borrowModel);
            }
        });
        thread.start();
    }
}
