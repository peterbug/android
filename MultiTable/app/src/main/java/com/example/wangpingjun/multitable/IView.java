package com.example.wangpingjun.multitable;

import java.util.ArrayList;

/**
 * Created by wangpingjun on 2016/4/10.
 */
public interface IView {
    public void showM1(Object obj);

    public void showM2(Object obj);

    public void showBeginTip(String obj);

    public void showGameOver(String result);

    public interface IViewResult {
        public void showTimes(String obj);

        public void showGrade(String obj);

        public void showDetail(ArrayList<String> data);

    }

}
