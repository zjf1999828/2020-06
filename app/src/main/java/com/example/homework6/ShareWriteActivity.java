package com.example.homework6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.homework6.Util.DateUtil;

public class ShareWriteActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mShared; // 声明一个共享参数对象
    private EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_write);

        et_name = findViewById(R.id.et_name);
        findViewById(R.id.btn_save).setOnClickListener(this);

        // 从share.xml中获取共享参数对象
        mShared = getSharedPreferences("share", MODE_PRIVATE);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String name = et_name.getText().toString();
            if (TextUtils.isEmpty(name)) {
                showToast("请填写姓名");
                return;
            }

            SharedPreferences.Editor editor = mShared.edit(); // 获得编辑器的对象
            editor.putString("name", name); // 添加一个名叫name的字符串参数
            editor.putString("update_time", DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
            editor.commit(); // 提交编辑器中的修改

            Intent intent = new Intent(this, ShareReadActivity.class);
            startActivity(intent);
        }
    }

    private void showToast(String desc) {
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }
}
