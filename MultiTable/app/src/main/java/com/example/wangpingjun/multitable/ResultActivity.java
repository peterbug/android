package com.example.wangpingjun.multitable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangpingjun on 2016/4/11.
 */
public class ResultActivity extends Activity implements IView.IViewResult {
    IView.IViewResult view = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result);
        ArrayList<String> data = getIntent().getStringArrayListExtra("data_private");
        view.showGrade((getIntent().getIntExtra("count_private", 0) - data.size()) + "/" + getIntent().getIntExtra("count_private", 0));
        view.showTimes(getIntent().getStringExtra("times_private"));
        showDetail(data);
    }

    @Override
    public void showDetail(final ArrayList<String> data) {
        ListView list = (ListView) findViewById(R.id.list_item);
        DetailAdapter adapter = new DetailAdapter(this, data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = ((TextView) view.findViewById(R.id.item_m1)).getText().toString();
                showRightResult(text);
            }
        });
    }

    private void showRightResult(String text) {
        String temp = text;//.replaceAll(" ", "");
        Pattern p = Pattern.compile("^\\s*(\\d+)\\s*[xX]\\s*(\\d+)\\s*=\\s*.*");
        Matcher m = p.matcher(temp);


        Log.e("XXX", " init: " + temp);
        if (m.matches()) {
            int count = m.groupCount();
            int i = 1;
            int[] multi = new int[m.groupCount()];
            while (count >= i) {
                Log.e("XXX", " init: count:" + i + " " + m.group(i));
                multi[i - 1] = Integer.parseInt(m.group(i));
                i++;
            }
            i = 1;
            while (i < multi.length) {
                multi[0] *= multi[i];
                i++;
            }

            Pattern p2 = Pattern.compile("^(\\s*\\d+\\s*[xX]\\s*\\d+\\s*=\\s*).*");
            Matcher m2 = p2.matcher(temp);
            if(m2.matches()) {
                Toast.makeText(this, m2.group(m2.groupCount()) + multi[0], Toast.LENGTH_SHORT).show();
//                new AlertDialog.Builder(this).setMessage(m2.group(m2.groupCount()) + multi[0]).show();
            }
        }
    }

    @Override
    public void showGrade(String obj) {
        ((TextView) findViewById(R.id.grade_text)).setText(obj);
    }

    @Override
    public void showTimes(String obj) {
        ((TextView) findViewById(R.id.eclipse_text)).setText(obj);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.dispatchKeyEvent(event);
    }

    static class DetailAdapter extends BaseAdapter {
        ArrayList<String> data;
        Context context;

        public DetailAdapter(Context context, ArrayList<String> data) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = View.inflate(context, R.layout.layout_list_item, null);
            }
            Object itemdata = getItem(position);
            if (itemdata instanceof String) {
                ((TextView) convertView.findViewById(R.id.item_num)).setText("<" + (position + 1) + ">");
                ((TextView) convertView.findViewById(R.id.item_m1)).setText((String) itemdata);
            }

            return convertView;
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
