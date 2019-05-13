package com.example.boschapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText usuario;
    EditText password;
    TextView estado;
    Connection conexion;

    final String driver = "sqlserver";
    final String host = "192.168.0.23";
    final String server = "RIOSSQLSERVER";
    final String port = "1433";
    final String database = "dbApp";
    final String dbUser = "sa";
    final String dbPass = "F32EC8E5EE8AFE91341C131AC885FA542593BA44";
    final String url =      "jdbc:"+
            driver + "://" +
            host + "\\" +
            server + ":" +
            port + ";databaseName=" +
            database + ";user=" +
            dbUser + ";password=" +
            dbPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText)this.findViewById(R.id.et_user);
        password = (EditText)this.findViewById(R.id.et_password);
        estado = (TextView)this.findViewById(R.id.tv_status);
    }

    public void ingresar(View view){

        try{
            Connection conexion = DriverManager.getConnection("jdbc:sqlserver://192.168.0.23\\RIOSSQLSERVER:1433;databaseName=dbApp;user=sa;password=F32EC8E5EE8AFE91341C131AC885FA542593BA44");
            Statement statement = conexion.createStatement();
            estado.setText("¡Conectado!");
            String query = "SELECT * FROM dbo.Login_Table";
            ResultSet rs = statement.executeQuery(query);
            estado.setText("Ejecutando");
            if(rs.next()){
                estado.setText(rs.getString("users")+":"+rs.getString("pass"));
            }
        }catch(java.sql.SQLException e){
            estado.setText(e.getSQLState());
        }
    }

}