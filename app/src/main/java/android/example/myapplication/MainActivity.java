package android.example.myapplication;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private EditText email;
    private Button button_register;
    private Button button_linkToLogin;

    private EditText login_name;
    private EditText login_password;
    private Button button_login;
    private Button button_linkToRegister;

    private Button button_logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        email=findViewById(R.id.password);
        button_register=findViewById(R.id.btnRegister);
        button_linkToLogin=findViewById(R.id.btnLinkToLoginScreen);



        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTask task=new registerTask();
                task.execute();
            }
        });

        button_linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.layout_login);
                button_login=findViewById(R.id.btnLogin);
            }
        });



    }

    class registerTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
            editor.putString("name",name.getText().toString());
            editor.putString("password",password.getText().toString());
            editor.putString("email",email.getText().toString());
            editor.apply();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this, "Sign up success!", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.layout_login);
            login_name=findViewById(R.id.email);
            login_password=findViewById(R.id.password);
            button_login=findViewById(R.id.btnLogin);
            button_linkToRegister=findViewById(R.id.btnLinkToRegisterScreen);
            button_linkToRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setContentView(R.layout.layout_register);
                }
            });

            button_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginTask task=new loginTask();
                    task.execute();
                }
            });
        }
    }

    class loginTask extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
            String name_store=pref.getString("name","");
            String password_store=pref.getString("password","");
            if(login_name.getText().toString().equals(name_store)){
                if(login_password.getText().toString().equals(password_store)){
                    return true;
                }
            }else{
                return false;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean==true){
                Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_main);
                button_logout=findViewById(R.id.btnLogout);
                button_logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.layout_login);
                    }
                });
            }else{
            Toast.makeText(MainActivity.this, "Name not exist!", Toast.LENGTH_SHORT).show();
        }
        }
    }



}


