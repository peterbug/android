package com.example.wangpingjun.multitable;

import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by wangpingjun on 2016/4/10.
 */
public class Control implements IControl {
    final static int MAX_DUPLICATE_COUNT = 1;
    final static int NUM_MIN = 2;
    final static int NUM_MAX = 9;
    LinkedHashMap<Integer, Integer> allData = new LinkedHashMap<>();
    ArrayList<Data> list = new ArrayList<>();
    ArrayList<String> errorList = new ArrayList<>();
    int index = 0;
    long eclipseTimes = 0;

//    static Control control = new Control();

    public Control() {
        init();
    }

    private void init() {
        list.clear();
        errorList.clear();
        allData.clear();
        int i = (NUM_MAX - NUM_MIN + 1);
        while (i > 0) {
            index += i;
//            Log.e("XXX", Thread.currentThread().getName() + " init: " + "-" + i);
            i--;
        }
        index *= MAX_DUPLICATE_COUNT;
        Random random1 = new Random();

        int x, y;
        long currentTime = System.currentTimeMillis();
        while (true) {
            x = (int) (Math.random() * 0xfff) % 10;
//            currentTime >>>=1;
//            if(currentTime<100)
//                currentTime = System.currentTimeMillis()/2;
//            x = random1.nextInt(10);
            if (x < NUM_MIN) {
                x = NUM_MIN;
            }
//            Log.e("XXX", Thread.currentThread().getName() + " init: " + x);
            if (allData.get(x) == null) {
                allData.put(x, x);
            }
            if (allData.size() >= (NUM_MAX - NUM_MIN + 1)) {
                break;
            }
        }
        int[] temp1 = new int[allData.size()];
        Iterator it = allData.entrySet().iterator();
        index = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
//            Log.e("XXX", Thread.currentThread().getName() + " init: it:" + entry.getKey() + " " + entry.getValue());
            temp1[index] = ((Integer) entry.getValue()).intValue();
            index++;
        }

        Object[] m1 = allData.keySet().toArray();
//        int[] temp1 = new int[m1.length];
//        index = 0;
//        for (Object o : m1) {
//            if (o instanceof Integer) {
//                temp1[index] = ((Integer) o).intValue();
//                index++;
//            }
//        }
        Log.e("XXX", Thread.currentThread().getName() + " initxxx: " + Arrays.toString(temp1));
        index = 0;
        while (index < temp1.length) {
            int x1 = 0;
            while (x1 < temp1.length) {
                list.add(new Data(temp1[index], temp1[x1]));
                x1++;
            }
            index++;
        }

        while (true) {
            x = random1.nextInt(list.size());
            if (allData.get(x) == null) {
                allData.put(x, 1);
            }
            if (allData.size() >= list.size()) {
                break;
            }
        }


        ArrayList<Data> list2 = new ArrayList<>();
        m1 = allData.keySet().toArray();
        index = 0;
        for (Object o : m1) {
            if (o instanceof Integer) {
                list2.add(list.get(((Integer) o).intValue()));
            }
        }
        list = list2;
        index = 0;//list2.size() - 4;
        eclipseTimes = System.currentTimeMillis();
    }

    @Override
    public Data generateNewQuestion() {
//        Log.e("XXX", Thread.currentThread().getName() + " generateNewQuestion: " + list.size() + " " + index);
        if (index >= list.size()) {
            int count = 0;
            for (Data d : list) {
                if (d.getRightResult().equals(d.getResult())) {
                    count++;
                } else {
                    errorList.add(new String(d.getM1() + " x " + d.getM2() + " = " + d.getResult()));
                }
            }
            Data r = new Data(count, list.size());
            r.setRightResult("");
            eclipseTimes = (System.currentTimeMillis() - eclipseTimes)/1000;
            return r;
        } else {
            return list.get(index++);
        }
    }

    @Override
    public boolean calculateResult(String result) {
//        Log.e("XXX", Thread.currentThread().getName() + " calculateResult: "+index);
        if (TextUtils.isEmpty(result)) {
            return false;
        }
        if (index > list.size()) {
            return false;
        }
        Data curr = list.get(index - 1);
        curr.setResult(result);
        if (curr.getRightResult().equals(result)) {
            return true;
        } else {
//            curr.setRightResult("*");
        }
        return false;
    }

    @Override
    public int getTotalCout() {
        return list.size();
    }

    @Override
    public String getEclipseTime() {
        return String.format( "%02d:%02d",eclipseTimes/60,eclipseTimes%60);
    }

    @Override
    public ArrayList<String> getErrorResult() {
        return errorList;
    }

    static class Data implements Comparable<Data> {
        public int getM1() {
            return m1;
        }

        public void setM1(int m1) {
            this.m1 = m1;
        }

        public int getM2() {
            return m2;
        }

        public void setM2(int m2) {
            this.m2 = m2;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public void setRightResult(String rightResult) {
            this.rightResult = rightResult;
        }

        public String getRightResult() {
            return rightResult;
        }

        int m1;
        int m2;
        String result="?";
        String rightResult;

        Data(int m1, int m2) {
            this.m1 = m1;
            this.m2 = m2;
            rightResult = "" + m1 * m2;
        }

        @Override
        public String toString() {
            return "[Data " + m1 + "-" + m2 + "]";
        }

        @Override
        public int compareTo(Data rhs) {
            return (this.getM1() + "-" + this.getM2()).compareTo(rhs.getM1() + "-" + rhs.getM2());
        }

    }
}
