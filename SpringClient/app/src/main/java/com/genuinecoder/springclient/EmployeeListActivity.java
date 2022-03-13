package com.genuinecoder.springclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.genuinecoder.springclient.adapter.EmployeeAdapter;
import com.genuinecoder.springclient.model.Employee;
import com.genuinecoder.springclient.reotrfit.EmployeeApi;
import com.genuinecoder.springclient.reotrfit.RetrofitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListActivity extends AppCompatActivity {

  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_employee_list);

    recyclerView = findViewById(R.id.employeeList_recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    FloatingActionButton floatingActionButton = findViewById(R.id.employeeList_fab);
    floatingActionButton.setOnClickListener(view -> {
      Intent intent = new Intent(this, EmployeeForm.class);
      startActivity(intent);
    });
  }

  private void loadEmployees() {
    RetrofitService retrofitService = new RetrofitService();
    EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);
    employeeApi.getAllEmployees()
        .enqueue(new Callback<List<Employee>>() {
          @Override
          public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
            populateListView(response.body());
          }

          @Override
          public void onFailure(Call<List<Employee>> call, Throwable t) {
            Toast.makeText(EmployeeListActivity.this, "Failed to load employees", Toast.LENGTH_SHORT).show();
          }
        });
  }

  private void populateListView(List<Employee> employeeList) {
    EmployeeAdapter employeeAdapter = new EmployeeAdapter(employeeList);
    recyclerView.setAdapter(employeeAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadEmployees();
  }
}