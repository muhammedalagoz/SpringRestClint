package com.example.muhammed.springrestclint;

import android.app.Fragment;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import model.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button a = (Button) findViewById(R.id.button);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpRequestTask().execute();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);

            return rootView;
        }
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Student> {
        @Override
        protected Student doInBackground(Void... params) {
            try {
                final String url = "http://192.168.1.90:8080/springWS_JPA/add";
              //  RestTemplate restTemplate = new RestTemplate();
               // restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
              //  Student greeting = restTemplate.getForObject(url, Student.class);

                //Post object to entity Spring
                Student u = new Student();
                u.setStudentName("Johnathan M Smith");
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Student greeting = restTemplate.postForObject(url, u, Student.class);

                // Get object to entity Spring
               // final String url = "http://192.168.1.90:8080/springWS_JPA/getPerson";
               // RestTemplate restTemplate = new RestTemplate();
               // restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
               // Student greeting = restTemplate.getForObject(url, Student.class);


                // TextView greetingIdText = (TextView) findViewById(R.id.id_value);
              //  TextView greetingContentText = (TextView) findViewById(R.id.content_value);
               // greetingIdText.setText((int) greeting.getStudentId());
              //  greetingContentText.setText(greeting.getStudentName());

                return greeting;

            } catch (Exception e) {
                System.out.println("ssssssssssssssssss");
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Student greeting) {
            TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            TextView greetingContentText = (TextView) findViewById(R.id.content_value);
            greetingIdText.setText(String.valueOf(greeting.getStudentId()));
            greetingContentText.setText(greeting.getStudentName());
        }

    }
}
