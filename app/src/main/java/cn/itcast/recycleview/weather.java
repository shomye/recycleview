package cn.itcast.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class weather extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.weather);


        ImageView fanhui = findViewById(R.id.imageReturn);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent =new Intent();
                    intent.setClass(weather.this,ZhujiemianActivity.class);
                    startActivity(intent);
                    finish();
            }
        });

    }

}
