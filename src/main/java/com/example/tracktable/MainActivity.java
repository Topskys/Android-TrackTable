package com.example.tracktable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tracktable.bean.User;
import com.example.tracktable.util.ToastUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername,edtPassword;
    Button loginBtn;
    TextView tvReset,tvReg;
    private MySQLiteOpenHelper SQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //页面初始化
        initView();
        //数据库操作对象
        SQL=new MySQLiteOpenHelper(this);
    }

    private void initView() {
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        loginBtn=findViewById(R.id.loginBtn);
        tvReset=findViewById(R.id.tvReset);
        tvReg=findViewById(R.id.tvReg);
    }

    //登录
    public void loginClick(View view) {
        //获取账号密码//去掉特殊字符，like 换行、空格
        String uname=edtUsername.getText().toString().trim();
        String pwd=edtPassword.getText().toString().trim();

        System.out.println("输入的数据\n用户："+uname+"密码："+pwd);

        if(uname.equals("")||pwd.equals("")){
            ToastUtil.toastShort(this,"账户名或密码為空！");
            return;//中断程序，停止运行
        }

        //清空输入框
        edtUsername.setText("");
        edtPassword.setText("");

        //数据库查询
        List<User> users=SQL.queryUsers();

        for (User u:users) {
           if(u.getUsername().equals(uname)&&u.getPassword().equals(pwd)){
               //登录成功跳转index主页
               Intent intent=new Intent(this,IndexActivity.class);

               //传值username和phone==》IndexActivity（getMineData()）==》MineFragment
               intent.putExtra("username",uname);
               intent.putExtra("phone",u.getPhone());

               startActivity(intent);

               //打印
               System.out.println("输出的数据\n用户："+u.getUsername()+"号码："+u.getPhone()+"\n功能正常");

               ToastUtil.toastShort(this,"登录成功");
               return;
           }
        }
        ToastUtil.toastShort(this,"账户名或密码错误！");
    }

    //跳转注册
    public void regClick(View view){
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    //重置密码
    public void resetUserPwdClick(View view) {
        //跳转重置密码页面
        Intent intent=new Intent(this, ResetPasswordActivity.class);
        startActivity(intent);
    }


}