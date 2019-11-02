package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Employee> employeeList;
    Employee employee;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    EmployeesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.viewEmployeesRecycler);

        progressBar.setVisibility(View.VISIBLE);

        ApiService api = RetrofitClient.getApiService();
        Call<EmployeeList> call = api.getJSON();

        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    employeeList = response.body().getEmployee();
                    adapter = new EmployeesAdapter(MainActivity.this, employeeList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
