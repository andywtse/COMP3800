package mam.dama.activity;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import mam.dama.Fragment.PlaylistFragment;
import mam.dama.R;

public class HubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        TextView eventName = (TextView) findViewById(R.id.event_name);
//        TextView eventName = (TextView) findViewById(R.id.event_name);

        Bundle prevBundle = getIntent().getExtras();

        String nameText = prevBundle.getString("event_name");
        if (nameText != null) {
            nameText = "Event: " + nameText;
        } else {
            nameText = "Event: UNKOWN";
        }
        eventName.setText(nameText);

        //TODO: get currently playing through API

        if(savedInstanceState==null){
            FragmentManager fm = getFragmentManager();
            android.app.Fragment fragment = new PlaylistFragment();
            fragment.setArguments(prevBundle);
            fm.beginTransaction().replace(R.id.content_hub, fragment).commit();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                return true;
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
