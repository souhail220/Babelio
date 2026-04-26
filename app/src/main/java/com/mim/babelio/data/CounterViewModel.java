package com.mim.babelio.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private final MutableLiveData<Integer> count = new MutableLiveData<>(0);

    public LiveData<Integer> getCount(){
        return count;
    }

    public void increment(){
        Integer value = count.getValue();
        count.setValue(value != null ? value + 1 : 1);
    }

    public void decrement(){
        Integer value = count.getValue();
        count.setValue(value != null ? value - 1 : 1);
    }
}
