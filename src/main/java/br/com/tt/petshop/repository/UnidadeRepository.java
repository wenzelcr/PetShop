package br.com.tt.petshop.repository;
import br.com.tt.petshop.model.Unidade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UnidadeRepository {

    private List<Unidade> db = new ArrayList<>();

    public Unidade save(Unidade unidade){
        db.add(unidade);
        return unidade;
    }

    public void limpar(Unidade unidade){
        for (Unidade u : db ) {
            db.remove(u);
        }
    }

    public List<Unidade> findAll(){
        return db;
    }

}
