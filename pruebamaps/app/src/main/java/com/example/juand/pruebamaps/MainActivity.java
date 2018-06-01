package com.example.juand.pruebamaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button caldist,borrardatos;
    EditText dir1,dir2;
    TextView resultado;
    String distacia;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caldist=(Button)findViewById(R.id.caldist);
        caldist.setOnClickListener(this);
        borrardatos=(Button)findViewById(R.id.borrardatos);
        borrardatos.setOnClickListener(this);

        dir1=(EditText)findViewById(R.id.dir1);
        dir2=(EditText)findViewById(R.id.dir2);
        resultado=(TextView)findViewById(R.id.resultado);


        Intent datodistancia=getIntent();
        Bundle extras=datodistancia.getExtras();
        if (extras != null){
            distacia=extras.getString("resultado");
        }

        resultado.setText(distacia);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.caldist:
                String direccion1=dir1.getText().toString();
                String direccion2=dir2.getText().toString();
                Intent intent= new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("direccion1",direccion1);
                intent.putExtra("direccion2",direccion2);
                startActivity(intent);
                break;

            case R.id.borrardatos:
                dir1.setText("");
                dir2.setText("");
                resultado.setText("");
                break;
        }
    }
}
