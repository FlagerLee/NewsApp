package com.example.liyuchen.ui.epidemic;

import com.github.mikephil.charting.data.Entry;

import java.util.List;

public interface AsyncFinish {
    public void finish(List<Entry> confirmedEntry, List<Entry> curedEntry, List<Entry> deadEntry);
}
