package com.example.newtext.Life.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newtext.Base.BaseActivity;
import com.example.newtext.R;

public class UserActivity extends BaseActivity {

    private LinearLayout userLlt;
    private TextView userHuhao;
    private Spinner userSpinner;
    private LinearLayout userClick;
    private Button userGo;
    private LinearLayout tvType;
    private TextView userHuhao1;
    private TextView typeTitle;
    private Spinner userType;
    private EditText huhaoNumber;


    private String[] userlist = {"我家", "父母", "房东", "朋友", "自定义"};
    private String[] type = {"手机话费","水费","电费","燃气费"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("户号管理");
        initView();
        initSpinner();
        initDots();
    }

    private void initDots() {
        for (int m=0;m<type.length;m++){
            ArrayAdapter ad = new ArrayAdapter<String>(this,R.layout.choice_type,type);
            userType.setAdapter(ad);
            userType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    typeTitle.setText(""+type[i]);
                    typeTitle.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void initSpinner() {
        for (int i = 0; i < userlist.length; i++) {
            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.choice_user, userlist);
            userSpinner.setAdapter(adapter);
            userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    userHuhao.setText("" + userlist[i]);
                    userHuhao.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void initView() {
        userLlt = (LinearLayout) findViewById(R.id.user_llt);
        userHuhao = (TextView) findViewById(R.id.user_huhao);
        userSpinner = (Spinner) findViewById(R.id.user_spinner);
        userClick = (LinearLayout) findViewById(R.id.user_click);
        tvType = (LinearLayout) findViewById(R.id.tv_type);
        userHuhao1 = (TextView) findViewById(R.id.user_huhao1);
        typeTitle = (TextView) findViewById(R.id.type_title);
        userType = (Spinner) findViewById(R.id.user_type);
        huhaoNumber = (EditText) findViewById(R.id.huhao_number);

        userLlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    userClick.setVisibility(View.VISIBLE);
            }
        });
        userGo = (Button) findViewById(R.id.user_go);
        userGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userClick.setVisibility(View.GONE);
                tvType.setVisibility(View.VISIBLE);

                String username = userHuhao.getText().toString().trim();
                userHuhao1.setText("缴费单位："+username);
            }
        });
    }
}