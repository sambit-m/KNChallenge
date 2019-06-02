package m.sambit.kisannetworkchallenge.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "messagelist")
public class MessageList implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "otp")
    private String otp;

    @ColumnInfo(name = "date_time")
    private String dateTime;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOtp() {
        return otp;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}