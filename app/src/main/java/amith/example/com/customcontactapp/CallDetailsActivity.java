package amith.example.com.customcontactapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by amith on 7/12/17.
 */

public class CallDetailsActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_details);
    }

    public void previousActivity(View view){
        this.finish();
    }
}
