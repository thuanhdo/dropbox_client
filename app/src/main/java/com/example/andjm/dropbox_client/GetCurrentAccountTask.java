package com.example.andjm.dropbox_client;

import android.os.AsyncTask;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

import javax.security.auth.callback.Callback;

public class GetCurrentAccountTask extends AsyncTask<Void, Void, FullAccount> {

    private final DbxClientV2 mDbxClient;
    private final Callback mCallback;
    private Exception mException;
    private final String ACCESS_TOKEN = "GfOGCUWNMXAAAAAAAAAAH9-FZoEA8yfSQ1HoEwXrcrSvrOCi9RLMG2w4rWxcxvVt";


    public interface Callback {
        void onComplete(FullAccount result);
        void onError(Exception e);
    }

    GetCurrentAccountTask(DbxClientV2 dbxClient, Callback callback) {
        mDbxClient = dbxClient;
        mCallback = callback;
    }

    @Override
    protected void onPostExecute(FullAccount account) {
        super.onPostExecute(account);
        if (mException != null) {
            mCallback.onError(mException);
        } else {
            mCallback.onComplete(account);
        }
    }

    @Override
    protected FullAccount doInBackground(Void... params) {

        try {
            return mDbxClient.users().getCurrentAccount();

        } catch (DbxException e) {
            mException = e;
        }

        return null;
    }
}
