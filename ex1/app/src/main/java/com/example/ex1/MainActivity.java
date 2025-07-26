package com.example.ex1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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


    }

    class LikesViewModel extends ViewModel{
        private final MutableLiveData<String> nome = new MutableLiveData<>("");
        private final MutableLiveData<String> sobrenome = new MutableLiveData<>("");
        private final MutableLiveData<Integer> imagem = new MutableLiveData<>(0);
        private final MutableLiveData<Integer> likes = new MutableLiveData<>(0);

        public MutableLiveData<String> getNome() {return nome;}
        public MutableLiveData<String> getSobrenome() {return sobrenome;}
        public MutableLiveData<Integer> getImagem() {return imagem;}
        public MutableLiveData<Integer> getLikes() {return likes;}

        public void setNome(String nome){this.nome.setValue(nome);}
        public void setSobrenome(String sobrenome){this.sobrenome.setValue(sobrenome);}

        public void darLike(){
            Integer like = likes.getValue();
            if(like==null){
                like = 0;
            }
            likes.setValue(like+1);
        }

        public void atualizarImg(Integer likes){
            if(likes <= 3){
                imagem.setValue(R.drawable.like);
            }else if(likes <= 9){
                imagem.setValue(R.drawable.coracao);
            }else{
                imagem.setValue(R.drawable.fogo);
            }
        }
    }
}