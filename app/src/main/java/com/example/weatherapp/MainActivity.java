package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.Retrofit.ApiClient;
import com.example.weatherapp.Retrofit.ApiInterface;
import com.example.weatherapp.Retrofit.Example;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageButton search;
    TextView temperature, humidity, feels_like, clothes;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        temperature = findViewById(R.id.temperature);
        feels_like = findViewById(R.id.feels_like);
        humidity = findViewById(R.id.humidity);
        textField = findViewById(R.id.textField);
        clothes = findViewById(R.id.clothes);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getWeatherDate(textField.getText().toString().trim());



            }
        });

    }

    private void getWeatherDate(String name){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);


        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {


                temperature.setText("온도 :" + "" + response.body().getMain().getTemp() + " C");
                feels_like.setText("체감 온도 :" + "" + response.body().getMain().getFeels_like());
                humidity.setText("습도 :" + "" + response.body().getMain().getHumidity());
                ArrayList<Integer> temp = new ArrayList<>();
                {
                    temp.add(0, 4);
                    temp.add(1, 8);
                    temp.add(2, 11);
                    temp.add(3, 16);
                    temp.add(4, 19);
                    temp.add(5, 22);
                    temp.add(6, 27);
                    temp.add(7, 28);
                }
                ArrayList<String> clth = new ArrayList<>();
                {
                    clth.add(0,"패딩, 두꺼운 코트, 목도리, 기모제품");
                    clth.add(1, "코트, 가죽자켓, 히트텍, 니트, 레깅스");
                    clth.add(2, "코트, 가죽자켓, 히트텍, 니트, 레깅스");
                    clth.add(3, "자켓, 가디건, 야상, 스타킹, 청바지, 면바지");
                    clth.add(4, "얇은 니트, 맨투맨, 가디건, 청바지");
                    clth.add(5, "얇은 가디건, 긴팔, 면바지, 청바지");
                    clth.add(6, "반팔, 얇은 셔츠, 반바지, 청바지");
                    clth.add(7, "민소매, 반팔, 반바지, 원피스");
                }

                try {
                    int num = Integer.parseInt(response.body().getMain().getTemp());
                    for (int i=0; i<temp.size()-1; i++) {
                        if ((temp.get(i) < num) && (num <= temp.get(i+1))) {
                            for (int j = 0; j < clth.size() - 1; j++) {
                                TextView tv = findViewById(getResources().getIdentifier("clothes", "id", getPackageName()));
                                tv.setText(clth.get(j + 1));
                                break;
                            }
                        }else if(num<=temp.get(i)){
                            TextView tv= findViewById(getResources().getIdentifier("clothes", "id", getPackageName()));
                            tv.setText(clth.get(i));
                            break;
                        }
                        }



                } catch (NumberFormatException e) {
                    double num1 = Double.parseDouble(response.body().getMain().getTemp());
                    int num = Integer.parseInt(String.valueOf(Math.round(num1)));
                    for(int i=0; i<temp.size()-1; i++){
                        if ((temp.get(i) < num) && (num <= temp.get(i+1))) {
                            for (int j = 0; j < clth.size() - 1; j++) {
                                TextView tv = findViewById(getResources().getIdentifier("clothes", "id", getPackageName()));
                                tv.setText(clth.get(j + 1));
                                break;
                            }
                        }else if(num<=temp.get(i)){
                            TextView tv= findViewById(getResources().getIdentifier("clothes", "id", getPackageName()));
                            tv.setText(clth.get(i));
                            break;
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();
            }
        });

        }
}