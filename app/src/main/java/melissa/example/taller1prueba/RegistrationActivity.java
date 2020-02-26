package melissa.example.taller1prueba;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    private EditText etn, etp, ete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ete = (EditText)findViewById(R.id.edtUsuario);
        etn = (EditText)findViewById(R.id.edtName);
        etp = (EditText)findViewById(R.id.edtPassword);

    }
    // Metodo del botón
    public void Registrar (View view){

        String email= ete.getText().toString();
        String nombre= etn.getText().toString();
        String password= etp.getText().toString();

        if (nombre.length()==0){
            Toast.makeText(this, "Ingrese Nombre", Toast.LENGTH_SHORT).show();
        }

        if (email.length()==0){
            Toast.makeText(this, "Ingrese Email", Toast.LENGTH_SHORT).show();
        }

        if (password.length()==0){
            Toast.makeText(this, "Ingrese Password", Toast.LENGTH_SHORT).show();
        }

        if (nombre.length()!=0 && email.length()!=0 && password.length()!=0){
            Toast.makeText(this, "Registro Correcto", Toast.LENGTH_SHORT).show();
        }

    }
    //Boton anterior
    public void Anterior(View view){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }

}
