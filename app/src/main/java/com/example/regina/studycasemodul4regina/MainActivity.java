package com.example.regina.studycasemodul4regina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void yes (View view){
//                                                      lihat iddbutton
        int id = view.getId();

//        perpindahan task dari button menggunakan switch
        switch (id){
            case R.id.btn_carigambar:
                startActivity(new Intent(this, gambar.class));
                break;
            case R.id.btn_listmahasiswa:
                startActivity(new Intent(this, mhs.class));
                break;
        }

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


    }

}
