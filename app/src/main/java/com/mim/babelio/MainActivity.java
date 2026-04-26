package com.mim.babelio;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import com.mim.babelio.ui.MainScreenKt;

public class MainActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainScreenKt.setMainScreenContent(this);
    }
}
