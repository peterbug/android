package com.example.wangpingjun.multitable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpingjun on 2016/4/10.
 */
public interface IControl {
    public Control.Data generateNewQuestion();
    public boolean calculateResult(String result);
    public String getEclipseTime();
    public int getTotalCout();
    public ArrayList<String> getErrorResult();
}


