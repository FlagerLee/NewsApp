package com.example.liyuchen.Async;

import java.util.ArrayList;

public interface DatabaseQuery {
    public <T> void DatabaseQueryFinish(boolean statement, ArrayList<T> results, String errMsg);
}
