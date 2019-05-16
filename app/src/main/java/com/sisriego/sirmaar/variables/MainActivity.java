package com.sisriego.sirmaar.variables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements  OnclickImageListener{


    RecyclerView recyclerView;
    imageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);

        confyAdapter();
        confyRecycler();
        addImage();


    }

    String name [] = {"imangen uno", "imagen 2", "Imagen3", "Imagen4"};
    String url [] = {
            "https://lh3.googleusercontent.com/tmoaFARKtCw6nhOGxMNYvMImoyLmam3QgaIlB5cmEmRpJvkCy6pGSz4AR3mFcrGbRyksxOrhxYuri9wVeJ_CQ4Mizkd7f4zchC7Lua4Xsud0m1ensK1ztA_1r6_XZYTaZIF9skFzmlW_TM0mrn43iL_Rg1V-udBJNHNJBqoxAVoLNnq_zssBBMdM7iG3HNbardCnS5Gql-osRqQmjMCNfj9xgvApa_HDrAYB30n309fW2WQ3acEc6koOeAqJjdtu-8Metbr4RLb2S0kEYwM9YmjBjRyTFIUbtGNrnYFYxBD_7Qa7Fr-P7YqYTMHUAb5bawjAMirabt2v8pLDDqn-mj9o6UtHU3ZYVMxQHQiHJ2VJ0IOMgjX6kzvb13f_vYSO-4g1fxBXPPZAaTJehsUUIOlK5Yc5GIUpujUwfUlQQU-LvvEG8lMJUB9izEbxPhuDK9RrMKRBIpvI-eMQJyt4pFl0yRUDRMoV-tRUhg2onX0vxXaiU51dd9PABJU3obiJU6EMJ1yJrR_SAOen5xCP00RtF8q3g0JmKdWF8SWChp92kZIvBuOQ_SU7nR_Gln6vnCsrwCgrUOEx5lZom-GlFs4b0e9_g6kPdyWiNhQm1gq3Mzhq5rZNiuAs1lfkiVrQsDx0Zq2rR5ptisoG8Y9-KOXkrL319SZb07-VyEeDgvsWNjJWkWFIDYQdB8BrU_DaNgLeww9yzqfrIYWiF-ndbx9kZA=w740-h657-no",
            "https://lh3.googleusercontent.com/Xib5BAPwl9rbXpiEYkr5V-2wuOTx1w3qIwAl9byBb1ryYm0E6ZTA4PfWqBWuAU0yCcYBpSfEgb8CcWkd6ut30ttPKEHzlWsu5eMm5B0HhYzr6JSPH1KrIB78WweCnnFJJUAl-6VtoR6AdWoLtLTJtRYzGwF9rK9BXNU5FTYyYEFODG6qSMLJnYNjceyqej_2uur72sVVrnj3ewzcgqlQX6QVz3fhbpzkTVDfAh3U0yQTSPynVXPDgi5yb39tGPocLmUb3RZyaZQwiV_q58ZxLEz0R1UmnK_kiJ4qUP-_8s0H1S1Aw5EBStxg5nGzjVUHmCRST-JtwNSXQ4_25D8EhoF00Zro65JzpGDk9w1gue9aOjx_QQ9hSDrPpYIBtiCOUtMpLhdjAoSkNXRkZOeLlRfejKBVXy40e76Bs0gqr7c-dtuTSxOMhq94fbqotYNufgJ4bf6m40wUXTWfjpI67jZb7wMFonXOihLiU-IjDGJ9tm7HforjGBEYm4eZqxAouUK2F9GL54KNYoI6Q4kv8kAJdaLKUxigDcE399x-b0jrblMrFDkd7zM8v6mjX_bfiGzSnlN9Z4EaTP7801w-Q-kXi1hw3PN3E4gQhWZFOEXYmsgq5o1VDVoDby9WmyDw1zsyYG4WLX6AQXZ4dF7KvslZYf8h66iKxjptXe8vGpi9AyDeV5OLIrPYC412frxiTGofi_9RJlpmjtbXaM3_a8IGwg=w413-h657-no",
            "https://lh3.googleusercontent.com/Xib5BAPwl9rbXpiEYkr5V-2wuOTx1w3qIwAl9byBb1ryYm0E6ZTA4PfWqBWuAU0yCcYBpSfEgb8CcWkd6ut30ttPKEHzlWsu5eMm5B0HhYzr6JSPH1KrIB78WweCnnFJJUAl-6VtoR6AdWoLtLTJtRYzGwF9rK9BXNU5FTYyYEFODG6qSMLJnYNjceyqej_2uur72sVVrnj3ewzcgqlQX6QVz3fhbpzkTVDfAh3U0yQTSPynVXPDgi5yb39tGPocLmUb3RZyaZQwiV_q58ZxLEz0R1UmnK_kiJ4qUP-_8s0H1S1Aw5EBStxg5nGzjVUHmCRST-JtwNSXQ4_25D8EhoF00Zro65JzpGDk9w1gue9aOjx_QQ9hSDrPpYIBtiCOUtMpLhdjAoSkNXRkZOeLlRfejKBVXy40e76Bs0gqr7c-dtuTSxOMhq94fbqotYNufgJ4bf6m40wUXTWfjpI67jZb7wMFonXOihLiU-IjDGJ9tm7HforjGBEYm4eZqxAouUK2F9GL54KNYoI6Q4kv8kAJdaLKUxigDcE399x-b0jrblMrFDkd7zM8v6mjX_bfiGzSnlN9Z4EaTP7801w-Q-kXi1hw3PN3E4gQhWZFOEXYmsgq5o1VDVoDby9WmyDw1zsyYG4WLX6AQXZ4dF7KvslZYf8h66iKxjptXe8vGpi9AyDeV5OLIrPYC412frxiTGofi_9RJlpmjtbXaM3_a8IGwg=w413-h657-no",
            "https://lh3.googleusercontent.com/6yybinukTsDWeg6Y1DAyGRXEGipJkoi1E31CcyvI9wK7t57OW5Q0-TZM8ZGmI5zNAzzsBb7xWlS_f0fEIS4cKKqZM9CRLlhAGRk-0laEo6BwQg9vOCNIn_4bqEZdRisEnNRJDpsFlfGSpUBlFbsUGOYvAqDQt9aBUiA6cQsdpg5dlLPUuK99hUM1huWPke7Pkpmm_rWm8ETzCwHyo_JVF6nzo8NcIyAQX75sdXO-c8kgoEE0XMpXwc3hV92PTgJNGdzSulO1Cpfz5gsmPzLvCqIGSEVe0yOufGxq3OSlTpQ2Izr6urvgx010nY1HaAbmq0dJG6iR2-RtCeoK3l9ViwIgPxkPyqdlnRzfmo5MR-lgAte-grnVA3XCU2juzLrVoRbs2_7TL1bCJkM0Hym_crzX_m4Ey5KeD1iULcgP8tZ5-OnA8D38FALBz_u6zsHF_io82TUhNvTmDr7MhJSXxJqK6PkEv0ajHRCy2i7Qpx93QR7xFdej3SAx8n9jmmi_OM3rqFCSpvilNrmA3KysyeS_kNMhNzOD3nhVFQOCeCXMs1hChD8VXOXjyG7VIoFN9Oek6kjSm0qpjcXHxYQJndfFkDyRfqX2SJbPk3jP2iRw6MIIivrjxPeax90tGKSNxtHUveOJpys432eb_s-F03nfOeoAL0Ff6LnAVUKj4Y06kpypYXXDwcc1COEiPcv-zTe6pTFKEzV7KZsyfQt5E9xqIw=w319-h306-no"};


    private void addImage(){
        //segun el numero de elemntos que tengas debes de poner el maximo al que llega i
        for(int i =0; i<4; i++){
            Image imagen = new Image(i+1,name[i],url[i]);
            adapter.addView(imagen);
        }

    }
    private void confyAdapter(){
        adapter = new imageAdapter(new ArrayList<Image>(),this);
    }
    private void confyRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClickImageListener(Image imagen) {
        // add the download code

        String urlImage = "la Imagen seleccionada es " +imagen.getName() + " el url es: " + imagen.urlImage;
        Toast.makeText(this, urlImage, Toast.LENGTH_SHORT).show();

    }

}
