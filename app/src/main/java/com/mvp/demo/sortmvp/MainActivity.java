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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SortMethodView {
    @BindView(R.id.rgSortMethods) RadioGroup rgSortMethods;
    @BindView(R.id.txtInputValues) EditText txtInputValues;
    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.tvResult) TextView tvResult;
    @BindView(R.id.btnClear) Button btnClear;
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
        hideProgressBar();
        hideBtnClear();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.setView(null);
    }

    @OnClick(R.id.btnSort)
    @Override
    public void getValues() {
        try {
            // Get sort method selected
            int selectedId = rgSortMethods.getCheckedRadioButtonId();
            RadioButton radioMethod = (RadioButton) findViewById(selectedId);
            // Get input values
            String method = radioMethod.getText().toString();
            presenter.setSortMethod(method);
            String values = txtInputValues.getText().toString();
            presenter.sortValues(values);
        } catch (Exception e) {
            Toast.makeText(this, "Introduce los parámetros solicitados en el formato correcto", Toast.LENGTH_SHORT).show();
        }
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
    public void showBtnClear() {
        btnClear.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBtnClear() {
        btnClear.setVisibility(View.GONE);
    }

    @Override
    public void showResult(String result) {
        tvResult.setText(result);
    }

    @OnClick(R.id.btnClear)
    public void clearValues() {
        resetValues();
        hideBtnClear();
    }

    @Override
    public void resetValues() {
        rgSortMethods.check(0);
        txtInputValues.setText("");
        tvResult.setText("");
    }
}
