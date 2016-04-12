package com.example.wangpingjun.multitable;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends Activity implements IView, View.OnClickListener {
    private IView view;
    private TextView resultTextView;
    private TextView nextTextView;
    private Control control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        findViewById(R.id.next).setOnClickListener(this);
        findViewById(R.id.next2).setOnClickListener(this);
        findViewById(R.id.digit_0).setOnClickListener(this);
        findViewById(R.id.digit_1).setOnClickListener(this);
        findViewById(R.id.digit_2).setOnClickListener(this);
        findViewById(R.id.digit_3).setOnClickListener(this);
        findViewById(R.id.digit_4).setOnClickListener(this);
        findViewById(R.id.digit_5).setOnClickListener(this);
        findViewById(R.id.digit_6).setOnClickListener(this);
        findViewById(R.id.digit_7).setOnClickListener(this);
        findViewById(R.id.digit_8).setOnClickListener(this);
        findViewById(R.id.digit_9).setOnClickListener(this);
        findViewById(R.id.digit_del).setOnClickListener(this);
        findViewById(R.id.digit_5).setOnClickListener(this);
        resultTextView = (TextView) findViewById(R.id.result);
        nextTextView = (TextView) findViewById(R.id.next2);
        view = this;
        view.showBeginTip("开始");
//        control = new Control();
//        generateNewQuestion();
    }

    private void saveResult(String result) {
        if (control.calculateResult(result)) {
//            resultTextView.setBackgroundColor();
        }
    }

    private void generateNewQuestion() {
        Control.Data data = control.generateNewQuestion();
        if ("".equals(data.getRightResult())) {
            showBeginTip("再玩一次");
            view.showGameOver("再玩一次");
            return;
        }
        resultTextView.setText("");
        view.showM1(data.getM1());
        view.showM2(data.getM2());
    }

    private View.OnClickListener digitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            generateNewQuestion();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
            case R.id.next2:
                if ("开始".equals(nextTextView.getText().toString())) {
                    control = new Control();
                    generateNewQuestion();
                    nextTextView.setText("下一题");
                    findViewById(R.id.begin_tip).setVisibility(View.GONE);
                } else if ("下一题".equals(nextTextView.getText().toString())) {
                    saveResult(resultTextView.getText().toString());
                    generateNewQuestion();
                } else {
                    nextTextView.setText("开始");
                    findViewById(R.id.begin_tip).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.digit_0:
            case R.id.digit_1:
            case R.id.digit_2:
            case R.id.digit_3:
            case R.id.digit_4:
            case R.id.digit_5:
            case R.id.digit_6:
            case R.id.digit_7:
            case R.id.digit_8:
            case R.id.digit_9:
            case R.id.digit_del: {
                if (v instanceof Button) {
                    Button b = (Button) v;
                    String resultString = resultTextView.getText().toString();

                    if (R.id.digit_del != v.getId()) {
                        if (resultString.length() < 2) {
                            resultTextView.setText(resultString + ((Button) v).getText().toString());
                        }
                    } else {
                        if (resultString.length() > 0) {
                            resultTextView.setText(resultString.substring(0, resultString.length() - 1));
                        }
                    }
                }
            }
            break;
        }
    }

    @Override
    public void showM1(Object obj) {
        ((TextView) findViewById(R.id.m1)).setText(obj.toString());
    }

    @Override
    public void showM2(Object obj) {
        ((TextView) findViewById(R.id.m2)).setText(obj.toString());
    }

    @Override
    public void showBeginTip(String obj) {
        nextTextView.setText(obj);
        ((TextView) findViewById(R.id.begin_tip)).setText("请按右下角" + obj);
        findViewById(R.id.begin_tip).setVisibility(View.VISIBLE);
    }

    @Override
    public void showGameOver(String result) {
        showBeginTip(result);
        Intent it = new Intent(this, ResultActivity.class);
        it.putStringArrayListExtra("data_private", control.getErrorResult());
        it.putExtra("count_private", control.getTotalCout());
        it.putExtra("times_private", control.getEclipseTime());
        startActivity(it);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.dispatchKeyEvent(event);
    }
}
