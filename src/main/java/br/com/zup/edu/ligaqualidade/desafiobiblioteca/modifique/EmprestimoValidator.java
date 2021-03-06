package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoUsuario;

public class EmprestimoValidator {
    DadosUsuario dadosUsuario;
    DadosExemplar dadosExemplar;
    DadosEmprestimo dadosEmprestimo;

    public EmprestimoValidator(DadosUsuario dadosUsuario, DadosExemplar dadosExemplar, DadosEmprestimo dadosEmprestimo) {
        this.dadosUsuario = dadosUsuario;
        this.dadosExemplar = dadosExemplar;
        this.dadosEmprestimo = dadosEmprestimo;
    }

    public boolean executaValidacoes() {
        if (validaPeriodoMaximoEmprestimo()) return false;

        if (validaAcessoUsuarioPadrao()) return true;

        return validaAcessoPesquisador();
    }

    private boolean validaAcessoPesquisador() {
        return dadosUsuario.padrao.equals(TipoUsuario.PESQUISADOR);
    }

    private boolean validaAcessoUsuarioPadrao() {
        return dadosUsuario.padrao.equals(TipoUsuario.PADRAO) && dadosExemplar.tipo.equals(TipoExemplar.LIVRE);
    }

    private boolean validaPeriodoMaximoEmprestimo() {
        return dadosEmprestimo.tempo > 60;
    }
}
