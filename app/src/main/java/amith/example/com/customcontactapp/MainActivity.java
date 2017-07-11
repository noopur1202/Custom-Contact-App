package amith.example.com.customcontactapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> photo, number;
    List<String> name = new ArrayList<>();
    CustomCellAdapter customCell;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button addContact = (Button)findViewById(R.id.addContact);
        gridView = (GridView)findViewById(R.id.gridList);
        name.addAll(Arrays.asList("a","b","c","d","e","f"));
        customCell = new CustomCellAdapter(getApplicationContext(),name,number);
        gridView.setAdapter(customCell);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewAddFragment rootFragment = new NewAddFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.mainContactView,rootFragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toggle_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_grid) {
            gridView.setNumColumns(2);
        }
        if (id == R.id.action_list) {
            gridView.setNumColumns(1);
        }
        return super.onOptionsItemSelected(item);
    }
}
