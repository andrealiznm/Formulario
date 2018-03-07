package com.andrealiznm.formulario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText eCorreo, eNombre, ePassword, eRpassword, eOtro;
    Button bAceptar;
    String nombre, correo, data="", genero="", p1="", p2="", p3="", p4="", p5="", date, lugar;
    CheckBox cCine, cNadar, cGym, cMusica;
    TextView result;
    Boolean p;
    RadioButton rMasculino, rFemenino;
    Spinner sLugar;
    String[] departamentos = {"Departamento", "Amazonas", "Antioquia", "Arauca", "Atlántico", "Bogotá", "Bolívar",
            "Boyacá", "Caldas", "Caquetá", "Casanare", "Cauca", "Cesar", "Chocó", "Córdoba",
            "Cundinamarca", "Guainía", "Guaviare", "Huila", "La Guajira", "Magdalena", "Meta",
            "Nariño", "Norte de Santander", "Putumayo", "Quindío", "Risaralda",
            "San Andrés y Providencia", "Santander", "Sucre", "Tolima", "Valle del Cauca", "Vaupés",
            "Vichada"};
    private static final String TAG = "MainActivity";
    private Button bDate;
    private DatePickerDialog.OnDateSetListener DateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eCorreo = findViewById(R.id.eCorreo);
        eNombre = findViewById(R.id.eNombre);
        eOtro = findViewById(R.id.eOtro);
        cCine = findViewById(R.id.cCine);
        cNadar = findViewById(R.id.cNadar);
        cGym = findViewById(R.id.cGym);
        cMusica = findViewById(R.id.cMusica);
        bDate = findViewById(R.id.bdate);
        result= findViewById(R.id.result);
        ePassword = findViewById(R.id.ePassword);
        eRpassword = findViewById(R.id.eRpassword);
        rMasculino = findViewById(R.id.rMasculino);
        rFemenino = findViewById(R.id.rFemenino);
        sLugar = findViewById(R.id.sLugar);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departamentos);
        sLugar.setAdapter(adaptador);

        sLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lugar = departamentos[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, DateListener, year,
                        month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });

        DateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //Jan is 0 and Dec is 11
                month = month +1;
                date = month+"/"+day+"/"+year;
                bDate.setText(date);

            }
        };
    }


    public void aceptar(View view) {
        int id = view.getId();
        result.setText("");
        String n = eNombre.getText().toString();
        String c = eCorreo.getText().toString();
        String pass1 = ePassword.getText().toString();
        String pass2 = eRpassword.getText().toString();
        String birth = bDate.getText().toString();
        String otro = eOtro.getText().toString();
        boolean cine = cCine.isChecked();
        boolean nadar = cNadar.isChecked();
        boolean gym = cGym.isChecked();
        boolean musica = cMusica.isChecked();

        if (cine==true)
            p1="Cine ";
        if (nadar==true)
            p2="Nadar ";
        if (gym==true)
            p3="Gym ";
        if (musica==true)
            p4="Musica ";
        if (otro=="")
            p5="";
        else
            p5=otro;

        if (id == R.id.bAceptar){
            if(n.isEmpty() || c.isEmpty() || pass1.isEmpty() || pass2.isEmpty() || genero.isEmpty()
                    || birth.isEmpty() || lugar == "Departamento" || (gym == false && nadar == false && cine == false && musica == false)){
                Toast.makeText(getApplicationContext(), "Falta un campo", Toast.LENGTH_SHORT).show();
            } else if (pass1.equals(pass2) && pass1.length()>=8){
                    //Toast.makeText(getApplicationContext(), "Las contraseñas coinciden", Toast.LENGTH_SHORT).show();
                    data = n + '\n' + c + '\n' + genero + '\n';
                    data = data + "Birth: " + date + '\n' + "Birthplace: " + lugar + '\n' + "Hobbies: " + p1 + p2 + p3 + p4 + p5;
                    result.setText(data);
                    p1=p2=p3=p4="";
                }else{
                    if (pass1.length()<8)
                        Toast.makeText(getApplicationContext(), "Constraseña minimo 8 caracteres", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        int id = view.getId();
        if(id == R.id.rMasculino){
            genero = "Male";
        } else {
            genero = "Female";
        }
    }

    public void onCheckBoxClicked(View view) {
        int id = view.getId();
    }
}

