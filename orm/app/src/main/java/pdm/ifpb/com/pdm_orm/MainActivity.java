package pdm.ifpb.com.pdm_orm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private Button btSalvar;
    private EditText idade;
    private ListView lista;
    private Button btDeletar;
    private EditText codigo;
    private Button btAtualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nome = findViewById(R.id.nome);
        idade = findViewById(R.id.idade);
        lista = findViewById(R.id.cadastrados);
        codigo = findViewById(R.id.codigo);
        btSalvar = findViewById(R.id.btSalvar);
        btDeletar = findViewById(R.id.btDeletar);
        btAtualizar = findViewById(R.id.btAtualizar);

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btSalvar.getText().equals("SALVAR")){
                    Usuario user = new Usuario(nome.getText().toString(),
                            Integer.parseInt(idade.getText().toString()));

                    user.save();
                    Toast.makeText(MainActivity.this, "Usuário cadastrado",
                            Toast.LENGTH_SHORT).show();

                }else{
                    Usuario user = Usuario.findById(Usuario.class,
                            Long.parseLong(codigo.getText().toString()));
                    user.setNome(nome.getText().toString());
                    user.setIdade(Integer.parseInt(idade.getText().toString()));
                    user.save();
                    Toast.makeText(MainActivity.this, "Usuário atualizado",
                            Toast.LENGTH_SHORT).show();
                    codigo.setText("");
                    btSalvar.setText("SALVAR");
                }
                nome.setText("");
                idade.setText("");
                atualizaListView();

            }
        });

        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = Usuario.findById(Usuario.class,
                        Long.parseLong(codigo.getText().toString()));
                nome.setText(user.getNome());
                idade.setText(user.getIdade()+"");

                btSalvar.setText("ATUALIZAR");

            }
        });

        btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = Usuario.findById(Usuario.class,
                        Long.parseLong(codigo.getText().toString()));
                user.delete();

                Toast.makeText(MainActivity.this, "Usuário deletado",
                        Toast.LENGTH_SHORT).show();

                codigo.setText("");
                atualizaListView();
            }
        });

        atualizaListView();
    }

    private void atualizaListView() {

        List<Usuario> todos = Usuario.listAll(Usuario.class);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, toArray(todos));

        lista.setAdapter(arrayAdapter);

    }

    private String[] toArray(List<Usuario> lista){

        String[] array = new String[lista.size()];

        for(int k =0; k < lista.size(); k ++){
            array[k] = lista.get(k).getNome() + " - " + lista.get(k).getIdade() + " anos - "
            + " código: "+lista.get(k).getId();
        }
        return array;
    }

}
