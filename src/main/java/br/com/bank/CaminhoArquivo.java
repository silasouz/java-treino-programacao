package br.com.bank;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

public class CaminhoArquivo {

    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        if (id == null || id < 0)
            throw new IllegalArgumentException();
        Integer dir = ((id - 1) / 1000) + 1;
        String pathDir = MessageFormat.format("/tmp/{0}/", dir.toString());
        String pathFile = MessageFormat.format("/tmp/{0}/{1}", dir.toString(), id.toString());
        return new CaminhoArquivo(Paths.get(pathDir), Paths.get(pathFile));
    }

}
