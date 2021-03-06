package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosDevolucao;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosLivro;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;

import java.time.LocalDate;
import java.util.Set;

public class Solucao {

    /**
     * Você precisa implementar o código para executar o fluxo
     * o completo de empréstimo e devoluções a partir dos dados
     * que chegam como argumento.
     *
     * Caso você queira pode adicionar coisas nas classes que já existem,
     * mas não pode alterar nada.
     */

    /**
     * @param dadosDeMultiplosLivros            dados necessários dos dadosDeMultiplosLivros
     * @param estoqueExamplares                 tipos de estoqueExamplares para cada livro
     * @param dadosUnicoUsuario                 tipos de dadosUnicoUsuario
     * @param solicitacoesEmprestimo            informações de pedidos de empréstimos
     * @param devolucoes                        informações de devoluções, caso exista.
     * @param dataParaSerConsideradaNaExpiracao aqui é a data que deve ser utilizada para verificar expiração
     * @return
     */
    public static Set<EmprestimoConcedido> executa(Set<DadosLivro> dadosDeMultiplosLivros,
                                                   Set<DadosExemplar> estoqueExamplares,
                                                   Set<DadosUsuario> dadosUnicoUsuario,
                                                   Set<DadosEmprestimo> solicitacoesEmprestimo,
                                                   Set<DadosDevolucao> devolucoes,
                                                   LocalDate dataParaSerConsideradaNaExpiracao) {

        final EmprestimoManager emprestimoManager = new EmprestimoManager(dadosUnicoUsuario.iterator().next(), solicitacoesEmprestimo);

        return emprestimoManager.processarSolicitacoesDadoEstoqueExemplares(estoqueExamplares);
    }

}
