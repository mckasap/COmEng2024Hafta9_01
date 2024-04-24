package com.example.comeng2024hafta9_01;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvX;
    TextView tvY;
    TextView tvZ;
    ImageView iv;


SensorManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




    iv= (ImageView)findViewById(R.id.imageView);



        String uri = "@drawable/one";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());


        Drawable res = getResources().getDrawable(imageResource);
        iv.setImageDrawable(res);



        tvX=(TextView) findViewById(R.id.textView);
        tvY=(TextView) findViewById(R.id.textView2);
        tvZ=(TextView) findViewById(R.id.textView3);
        sm= (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate=System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        sm.registerListener(this,
                sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

         super.onResume();
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }




long lastUpdate;


boolean color=true;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x=sensorEvent.values[0];
        float y=sensorEvent.values[1];
        float z=sensorEvent.values[2];

        tvX.setText("X: "+x);
        tvY.setText("Y: "+y);
        tvZ.setText("Z: "+z);



        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = sensorEvent.timestamp;
        if (accelationSquareRoot >= 3) //
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            Toast.makeText(this, "Device was shuffed", Toast.LENGTH_SHORT)
                    .show();

            Random rnd= new Random();
            int sayi= rnd.nextInt(6)+1;
            String uri="";
            switch (sayi){
                case 1:   uri = "@drawable/one"; break;
                case 2:  uri = "@drawable/two"; break;
                case 3:   uri = "@drawable/three"; break;
                case 4:  uri = "@drawable/four"; break;
                case 5:   uri = "@drawable/five"; break;
                case 6:  uri = "@drawable/six"; break;
            }

             // where myresource (without the extension) is the file

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());


            Drawable res = getResources().getDrawable(imageResource);
            iv.setImageDrawable(res);


            if (color) {

                tvX.setBackgroundColor(Color.GREEN);
            } else {
                tvX.setBackgroundColor(Color.RED);
            }
            color = !color;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}