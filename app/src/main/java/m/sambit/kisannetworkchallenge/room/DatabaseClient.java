package m.sambit.kisannetworkchallenge.room;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private MessageDatabase messageDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the message database with Room database builder
        //MessageHistory is the name of the database
        messageDatabase = Room.databaseBuilder(mCtx, MessageDatabase.class, "MessageHistory").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public MessageDatabase getAppDatabase() {
        return messageDatabase;
    }
}