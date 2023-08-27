package br.com.bank.service;


import java.util.ArrayList;
import java.util.List;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenFake extends Bacen {

    private List<Banco> bancos = new ArrayList<Banco>();

    @Override
    public long cadastrarBanco(Banco banco) {
        bancos.add(banco);
        return bancos.size();
    }
    
}
