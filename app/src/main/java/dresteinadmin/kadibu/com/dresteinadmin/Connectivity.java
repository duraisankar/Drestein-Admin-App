package dresteinadmin.kadibu.com.dresteinadmin;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by keshav on 19/9/17.
 */

public class Connectivity {
    Context context;

    public Connectivity(Context context) {
        this.context = context;
    }
    public boolean isConnected(){
        ConnectivityManager connectivity= (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(connectivity!=null)
        {
            NetworkInfo info=connectivity.getActiveNetworkInfo();
            if(info != null)
            {
                if(info.getState()==NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
            else
            {

            }
        }return  false;
    }
}
