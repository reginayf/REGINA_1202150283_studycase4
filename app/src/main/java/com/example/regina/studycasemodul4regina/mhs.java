package com.example.regina.studycasemodul4regina;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;




public class mhs extends AppCompatActivity {
    ListView namamhs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs);


        namamhs = findViewById(R.id.list_namamahasiswa); // mendefinisikan list mahasiswa dengan mencari id nya
    }

//    Method untuk melakukan load data maha siswa menggunakan Asynctask
    public void loadmahasiswa(View view) {
        new getData(namamhs).execute();       // mulai melakukan Asynctask
    }

    //Sub-class Asynctask
    class getData extends AsyncTask<String, Integer, String>{
        ListView namamhs;
        ArrayAdapter adapter;
        ArrayList<String> listItems;
        ProgressDialog dialog;

        //Constructor ketika AsyncTask diinisialisasi
        public getData(ListView namamahasiswa) {
            this.namamhs = namamahasiswa;
            dialog = new ProgressDialog(mhs.this);
            listItems = new ArrayList<>();
        }

        //Method sebelum AsyncTask dimulai
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Menampilkan ProgressDialog
            dialog.setTitle("Loading Nama");
            dialog.setIndeterminate(false);
            dialog.setCancelable(true);

            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.dismiss();
                    getData.this.cancel(true);
                }
            });
            dialog.setMax(100);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setProgress(0);
            dialog.show();
        }

        //Method ketika sedang dilakukan AsynTask dibalik layar
        @Override
        protected String doInBackground(String... strings) {
            //Membuat adapter
            adapter = new ArrayAdapter<>(mhs.this, android.R.layout.simple_list_item_1, listItems);
            //Menyimpan array pada variabel
            String [] mahasiswa = getResources().getStringArray(R.array.mahasiswa);
            //Menyimpan array ke dalam
            for(int i=0; i<mahasiswa.length;i++){
                final long percentage = 100L*i/mahasiswa.length;
                final String namanya = mahasiswa[i];
                try{
                    Runnable changingmessage = new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMessage((int)percentage+"% - Adding "+namanya);
                        }
                    };
                    runOnUiThread(changingmessage);
                    Thread.sleep(250);
                    listItems.add(mahasiswa[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            namamhs.setAdapter(adapter);
            dialog.dismiss();
        }

    }
}
