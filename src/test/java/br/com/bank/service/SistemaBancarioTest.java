package br.com.bank.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import br.com.bank.exceptions.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

    @InjectMocks
    private SistemaBancario sistemaBancarioMock;

    @Mock
    private Bacen bacenMock;

    @Test
    public void deve_cadastrar_banco_stub() {
        BacenStub bacenStub = new BacenStub();
        SistemaBancario sistemaBancarioStub = new SistemaBancario(bacenStub);

        long retorno = sistemaBancarioStub.registrarBanco(new Banco("BB"));

        assertTrue(() -> retorno > 0);
    }

    @Test
    public void deve_cadastrar_banco_fake() {
        BacenFake bacenStub = new BacenFake();
        SistemaBancario sistemaBancarioStub = new SistemaBancario(bacenStub);

        long retorno = sistemaBancarioStub.registrarBanco(new Banco("BB"));

        assertTrue(() -> retorno > 0);
    }

    @Test
    public void deve_cadastrar_banco_mock() {
        when(bacenMock.cadastrarBanco(any(Banco.class))).thenReturn(1L);

        long retorno = sistemaBancarioMock.registrarBanco(new Banco("BB"));

        assertTrue(() -> retorno > 0);
    }

    @Test
    public void nao_deve_cadastrar_banco_mock() {
        when(bacenMock.cadastrarBanco(any(Banco.class))).thenThrow(RuntimeException.class);

        assertThrows(BancoNaoCadastradoException.class, 
            () -> sistemaBancarioMock.registrarBanco(new Banco("BB")));
    }

        


}