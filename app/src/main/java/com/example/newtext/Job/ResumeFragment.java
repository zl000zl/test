package com.example.newtext.Job;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.newtext.Job.Jobbean.Jianli;
import com.example.newtext.Network;
import com.example.newtext.OkResult;
import com.example.newtext.R;
import com.example.newtext.StringBean.User;
import com.google.gson.Gson;

import org.json.JSONObject;

public class ResumeFragment extends Fragment {


    private ImageView resumeTx;
    private TextView resumeUser;
    private TextView resumeName;
    private RadioButton resumeBoy;
    private RadioButton resumeGirl;
    private TextView resumeNumber;
    private TextView resumeMail;
    private TextView workJinyan;
    private TextView xueli;
    private TextView juzhu;
    private TextView zhiwei;
    private TextView money;
    private TextView miaoshu;

    public static int nameid;
    private Button btnXiugai;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resume, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        iniData();
        initDots();
        initClick();
    }

    private void initClick() {
        btnXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initDots() {
        Network.doGet("/prod-api/api/job/resume/queryResumeByUserId/1", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                Jianli jianlis = new Gson().fromJson(jsonObject.optString("data"), Jianli.class);
                workJinyan.setText("" + jianlis.getExperience());
                xueli.setText("" + jianlis.getMostEducation());
                juzhu.setText("" + jianlis.getAddress());
                zhiwei.setText("" + jianlis.getPositionName());
                money.setText("" + jianlis.getMoney() + "元");
                miaoshu.setText("" + jianlis.getIndividualResume());

            }
        });
    }

    private void iniData() {
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                User user = new Gson().fromJson(jsonObject.optString("user"), User.class);
                Glide.with(resumeTx).load("https://c-ssl.duitang.com/uploads/blog/202107/09/20210709105552_7c4aa.thumb.1000_0.jpeg")
                        .into(resumeTx);
                resumeUser.setText("" + user.getUserName());
                resumeName.setText("" + user.getNickName());
                if (user.getSex().equals("0")) {
                    resumeBoy.setChecked(true);
                } else {
                    resumeGirl.setChecked(true);
                }
                resumeNumber.setText("" + user.getPhonenumber());
                resumeMail.setText("" + user.getEmail());
                nameid = user.getUserId();
            }
        });
    }

    private void initView(View getView) {
        resumeTx = (ImageView) getView.findViewById(R.id.resume_tx);
        resumeUser = (TextView) getView.findViewById(R.id.resume_user);
        resumeName = (TextView) getView.findViewById(R.id.resume_name);
        resumeBoy = (RadioButton) getView.findViewById(R.id.resume_boy);
        resumeGirl = (RadioButton) getView.findViewById(R.id.resume_girl);
        resumeNumber = (TextView) getView.findViewById(R.id.resume_number);
        resumeMail = (TextView) getView.findViewById(R.id.resume_mail);
        workJinyan = (TextView) getView.findViewById(R.id.work_jinyan);
        xueli = (TextView) getView.findViewById(R.id.xueli);
        juzhu = (TextView) getView.findViewById(R.id.juzhu);
        zhiwei = (TextView) getView.findViewById(R.id.zhiwei);
        money = (TextView) getView.findViewById(R.id.money);
        miaoshu = (TextView) getView.findViewById(R.id.miaoshu);
        btnXiugai = (Button) getView.findViewById(R.id.btn_xiugai);
    }
}