package com.stevedamien;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import com.stevedamien.activities.DisplayGridActivity;

public class MainActivity extends Activity {

    private TextView sizeX;
    private TextView sizeY;
    private Button runButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sizeX = (TextView) this.findViewById(R.id.textViewMainSizeX);
        this.sizeY = (TextView) this.findViewById(R.id.textViewMainSizeY);
        this.runButton = (Button) this.findViewById(R.id.buttonMainGenerate);

        this.runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayGrid();
            }
        });
    }

    private void displayGrid()
    {
        Intent i = new Intent(this, DisplayGridActivity.class);
        //throw new RuntimeException("DAMIENDEBUG : " + sizeX.getText().toString() + " " + sizeY.getText().toString());
        //System.err.println("DAMIENDEBUG : " + sizeX.getText().toString());
        //System.err.println("DAMIENDEBUG : " + sizeY.getText().toString());
        i.putExtra("SIZEX", sizeX.getText().toString());
        i.putExtra("SIZEY", sizeY.getText().toString());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
