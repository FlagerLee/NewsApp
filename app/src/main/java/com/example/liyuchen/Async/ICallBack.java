package com.example.liyuchen.Async;

import java.util.ArrayList;

public interface ICallBack {
    //statement means that whether the operations were success or not
    public void NetworkRequestTextFinish(boolean statement, ArrayList<String> msg, String errMsg);
    public void DownloadFileFinish(boolean statement, String filePath, String errMsg);
    public <T> void DatabaseQueryFinish(boolean statement, ArrayList<T> results, String errMsg);
    public void DatabaseCURDFinish(boolean statement, String errMsg);
}
