package com.yakuzaintokyo.nationalizeassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.search.SearchView;
import com.yakuzaintokyo.nationalizeassignment.model.Country;
import com.yakuzaintokyo.nationalizeassignment.model.National;
import com.yakuzaintokyo.nationalizeassignment.services.NationalityService;
import com.yakuzaintokyo.nationalizeassignment.services.RetrofitInstance;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText searchName;
    private Button submit;
    private TextView result;
    private ArrayList<Country> listOfPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchName = findViewById(R.id.searchView);
        submit = findViewById(R.id.submitButton);
        result = findViewById(R.id.resultTextView);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = searchName.getText().toString().trim();
                result.setText("");
                displayResult(name);
            }
        });

    }

    private void displayResult(String name) {
        NationalityService nationalityService = RetrofitInstance.getService();
        Call<National> call = nationalityService.getNationality(name);

        call.enqueue(new Callback<National>() {
            @Override
            public void onResponse(Call<National> call, Response<National> response) {
                National national = response.body();
                if (national != null && national.getCountry() != null) {

                    listOfPerson = (ArrayList<Country>) national.getCountry();

                    for (Country country : listOfPerson) {

                        String results = "";
                        results += "Country : " + country.getCountryId() + "\n";
                        results += "Probability : " + country.getProbability() + "\n\n";

                        result.append(results);
                    }
                }
            }

            @Override
            public void onFailure(Call<National> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Oops! unable to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}