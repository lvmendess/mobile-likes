package com.example.ex1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LikesViewModel extends ViewModel {
    private final MutableLiveData<String> nome = new MutableLiveData<>("");
    private final MutableLiveData<String> sobrenome = new MutableLiveData<>("");
    private final MutableLiveData<Integer> imagem = new MutableLiveData<>(R.drawable.like);
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
        }else if(like==11){
            like = 0;
        }
        likes.setValue(like+1);
        atualizarImg(likes.getValue());
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
