package com.example.ex1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import android.text.TextWatcher;
import android.text.Editable;

import com.google.android.material.internal.TextWatcherAdapter;

public class MainActivity extends AppCompatActivity {

    private EditText nome, sobrenome;
    private ImageView imagem;
    private Button like;
    private ProgressBar progresso;
    private LikesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(LikesViewModel.class);

        nome = findViewById(R.id.nome);
        sobrenome = findViewById(R.id.sobrenome);
        imagem = findViewById(R.id.imagem);
        like = findViewById(R.id.like);
        progresso = findViewById(R.id.progresso);

        viewModel.getLikes().observe(this, likes -> {progresso.setProgress(likes);});
        viewModel.getImagem().observe(this, img -> {imagem.setImageResource(img);});

        nome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setNome(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        sobrenome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setSobrenome(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.getNome().getValue().isEmpty() || viewModel.getSobrenome().getValue().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Campo Nome e/ou Sobrenome vazios, preencha para prosseguir", Toast.LENGTH_SHORT).show();
                }else{
                    viewModel.darLike();
                }
            }
        });
    }
}