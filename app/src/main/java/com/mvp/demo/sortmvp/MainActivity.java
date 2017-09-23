package com.mvp.demo.sortmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mvp.demo.sortmvp.di.App;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements SortMethodView {
    private RadioGroup rgSortMethods;
    private RadioButton radioMethod;
    private EditText txtInputValues;
    private Button btnSort;
    private ProgressBar progressBar;
    private TextView tvResultLabel, tvResult;
    @Inject
    SortMethodPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getComponent().inject(this);
        // Bind UI components
        rgSortMethods = (RadioGroup)findViewById(R.id.rgSortMethods);
        txtInputValues = (EditText) findViewById(R.id.txtInputValues);
        btnSort = (Button) findViewById(R.id.btnSort);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvResultLabel = (TextView) findViewById(R.id.tvResultLabel);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValues();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        hideResultLabel();
        hideProgressBar();
    }

    @Override
    public void getValues() {
        try {
            // Get sort method selected
            int selectedId = rgSortMethods.getCheckedRadioButtonId();
            radioMethod = (RadioButton)findViewById(selectedId);
            // Get input values
            String method = radioMethod.getText().toString();
            String values = txtInputValues.getText().toString();
            presenter.sortValues(method, values);
        } catch (Exception e) {
            Toast.makeText(this, "Introduce los parámetros requeridos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showResultLabel() {
        tvResultLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResultLabel() {
        tvResultLabel.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showResult(String result) {
        if (result != null && result != "") {
            rgSortMethods.check(0);
            txtInputValues.setText("");
            tvResult.setText(result);
        }
    }
}
