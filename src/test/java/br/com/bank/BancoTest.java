package br.com.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

class BancoTest {

    private Banco banco;

    @BeforeEach
    public void setup() {
        banco = new Banco("Meu Banco");
    }

    @Test
    public void deve_adicionar_conta(){
        banco.adicionarConta(new Conta("111", 101));

        Conta contaEncontrada = banco.pesquisarContaDoCliente("111");
        assertNotNull(contaEncontrada);
        assertEquals(contaEncontrada.getSaldo(), 101.0);
    }

    @Test
    public void deve_pesquisar_conta_cliente(){
        banco.adicionarConta(new Conta("111", 101));
        banco.adicionarConta(new Conta("222", 202));

        Conta contaEncontrada = banco.pesquisarContaDoCliente("222");
        assertEquals(contaEncontrada.getSaldo(), 202.0);
    }

    @Test
    public void deve_listar_contas_alta_renda(){

        banco.adicionarConta(new Conta("111", 101));
        banco.adicionarConta(new Conta("222", 202));
        banco.adicionarConta(new Conta("333", 30000));
        banco.adicionarConta(new Conta("444", 40000));

        List<Conta> contasAltaRenda = banco.listarContasAltaRenda();

        assertTrue(() -> contasAltaRenda.stream().allMatch(c -> c.getSaldo() >= 10000));
    }
    
}
