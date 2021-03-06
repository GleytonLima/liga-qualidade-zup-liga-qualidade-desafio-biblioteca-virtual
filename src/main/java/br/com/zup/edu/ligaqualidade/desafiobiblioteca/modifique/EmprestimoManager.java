package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EmprestimoManager {
    Set<DadosEmprestimo> solicitacoesEmprestimo;
    DadosUsuario dadosUsuario;

    public EmprestimoManager(DadosUsuario dadosUsuario,
                             Set<DadosEmprestimo> solicitacoesEmprestimo
    ) {
        this.dadosUsuario = dadosUsuario;
        this.solicitacoesEmprestimo = solicitacoesEmprestimo;
    }

    public Set<EmprestimoConcedido> processarSolicitacoesDadoEstoqueExemplares(Set<DadosExemplar> estoqueExamplares) {
        Set<EmprestimoConcedido> emprestimoConcedidos = new HashSet<>();
        solicitacoesEmprestimo
                .forEach(dadosEmprestimo -> {
                    var dadosExemplar = buscarExemplarDadoIdLivro(dadosEmprestimo.idLivro, dadosEmprestimo, estoqueExamplares);
                    dadosExemplar.ifPresent(exemplar -> emprestimoConcedidos.add(new EmprestimoConcedido(dadosEmprestimo.idUsuario, exemplar.idExemplar, LocalDate.now().plusDays(dadosEmprestimo.tempo))));
                });
        return emprestimoConcedidos;
    }

    public Optional<DadosExemplar> buscarExemplarDadoIdLivro(int idLivro, DadosEmprestimo dadosEmprestimo, Set<DadosExemplar> dadosDeMultiplosExemplares) {
        var dadosExemplarOptional = dadosDeMultiplosExemplares.stream().filter(dadosExemplar -> dadosExemplar.idLivro == idLivro).findFirst();
        var validador = new EmprestimoValidator(dadosUsuario, dadosExemplarOptional.get(), dadosEmprestimo);
        if (validador.executaValidacoes()) {
            return dadosExemplarOptional;
        }
        return Optional.empty();
    }
}
