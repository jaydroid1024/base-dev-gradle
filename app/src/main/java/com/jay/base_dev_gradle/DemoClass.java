package com.jay.base_dev_gradle;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jay.router_annotations.Destination;

/**
 * @author jaydroid
 * @version 1.0
 * @date 5/25/21
 */
@Destination(url = "router:page_b", description = "page_b ")
public class DemoClass extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
