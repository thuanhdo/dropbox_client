package com.example.andjm.dropbox_client;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FloatingActionButton fab;
    private LinearLayout linear_content;
    private final String ACCESS_TOKEN = "GfOGCUWNMXAAAAAAAAAAH9-FZoEA8yfSQ1HoEwXrcrSvrOCi9RLMG2w4rWxcxvVt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "FAB clicked!!", Toast.LENGTH_LONG).show();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        linear_content = findViewById(R.id.linear_content);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_25dp);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:

                                Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.nav_files:
                                Toast.makeText(MainActivity.this, "Files selected", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.nav_notifications:
                                Toast.makeText(MainActivity.this, "Notifications selected", Toast.LENGTH_LONG).show();
                                break;
                        }
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class createDropboxClient extends AsyncTask<String, Void, FullAccount>{

        private String this_access_token;

        @Override
        protected FullAccount doInBackground(String... access_token) {
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
            DbxClientV2 client = new DbxClientV2(config, access_token[0]);
            try {
                FullAccount account = client.users().getCurrentAccount();
                return account;
            } catch (DbxException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(FullAccount account){
            super.onPostExecute(account);
            TextView email_view     = findViewById(R.id.email_view);
            TextView name_view      = findViewById(R.id.name_view);
            TextView storage_view   = findViewById(R.id.storage_view);
            CircleImageView avatar  = findViewById(R.id.profile_image);
            Picasso.with(MainActivity.this).load(account.getProfilePhotoUrl()).into(avatar);
            email_view.setText(account.getEmail());
            name_view.setText(account.getName().getDisplayName());
            avatar.setImageDrawable(getResources().getDrawable(R.drawable.profile_avatar));


        }

    }

    createDropboxClient ahi = (createDropboxClient) new createDropboxClient().execute(ACCESS_TOKEN);



}
