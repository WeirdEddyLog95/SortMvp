package com.mvp.demo.sortmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mvp.demo.sortmvp.di.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SortMethodView {
    @BindView(R.id.rgSortMethods) RadioGroup rgSortMethods;
    @BindView(R.id.txtInputValues) EditText txtInputValues;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.tvResultLabel) TextView tvResultLabel;
    @BindView(R.id.tvResult) TextView tvResult;
    @Inject
    SortMethodPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getComponent().inject(this);
        // Bind UI components
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        hideResultLabel();
        hideProgressBar();
    }

    @OnClick(R.id.btnSort)
    @Override
    public void getValues() {
        try {
            // Get sort method selected
            int selectedId = rgSortMethods.getCheckedRadioButtonId();
            RadioButton radioMethod = (RadioButton)findViewById(selectedId);
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
