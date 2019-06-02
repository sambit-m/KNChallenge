package m.sambit.kisannetworkchallenge.util;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.button.MaterialButton;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import m.sambit.kisannetworkchallenge.R;

public class Utility {

    public static void showDialog(String message, Context mContext) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog alert = builder.create();
        View view = alert.getLayoutInflater().inflate(R.layout.show_dialog, null);
        TextView title =  view.findViewById(R.id.title);
        MaterialButton ok =  view.findViewById(R.id.Ok);
        title.setText(message);
        alert.setCustomTitle(view);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        alert.show();
    }

    public static String getCurrentDateTime(){
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm, dd/MM/YY", Locale.UK);
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static boolean checkInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
