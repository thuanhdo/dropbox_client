package com.example.andjm.dropbox_client;

import android.os.AsyncTask;
import android.text.PrecomputedText;
import android.widget.TextView;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

public class createDropboxClient extends AsyncTask<Void, Void, FullAccount> {

    private String ACCESS_TOKEN = "GfOGCUWNMXAAAAAAAAAAH9-FZoEA8yfSQ1HoEwXrcrSvrOCi9RLMG2w4rWxcxvVt";
    private Exception mException;

    createDropboxClient(String access_token){
        ACCESS_TOKEN = access_token;
    }


    @Override
    protected FullAccount doInBackground(Void... voids) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        try {
            FullAccount account = client.users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(FullAccount account){
        super.onPostExecute(account);

    }

}
