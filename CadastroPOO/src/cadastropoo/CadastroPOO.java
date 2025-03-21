package cadastropoo;

import java.io.IOException;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO 
{
    public static void main(String[] args) 
    {
        try 
        {
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            repo1.inserir(new PessoaFisica(1, "Ana", "11111111111", 25));
            repo1.inserir(new PessoaFisica(2, "Carlos", "22222222222", 52));

            String arquivoPF = "pessoas_fisicas.dat";
            repo1.persistir(arquivoPF);
            System.out.println("Dados de Pessoa Física Armazenados:");

            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            repo2.recuperar(arquivoPF);
            System.out.println("Dados de Pessoa Física Recuperados:");

            for (PessoaFisica pf : repo2.obterTodos()) 
            {
                System.out.println("Id: " + pf.getId());
                System.out.println("Nome: " + pf.getNome());
                System.out.println("CPF: " + pf.getCpf());
                System.out.println("Idade: " + pf.getIdade());
            }

            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            repo3.inserir(new PessoaJuridica(3, "XPTO Sales", "33333333333333"));
            repo3.inserir(new PessoaJuridica(4, "XPTO Solutions", "44444444444444"));

            String arquivoPJ = "pessoas_juridicas.dat";
            repo3.persistir(arquivoPJ);
            System.out.println("Dados de Pessoa Jurídica Armazenados:");

            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            repo4.recuperar(arquivoPJ);
            System.out.println("Dados de Pessoa Jurídica Recuperados:");

            for (PessoaJuridica pj : repo4.obterTodos()) 
            {
                System.out.println("Id: " + pj.getId());
                System.out.println("Nome: " + pj.getNome());
                System.out.println("CNPJ: " + pj.getCnpj());
            }
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.err.println("Erro ao manipular arquivos: " + e.getMessage());
        }
    }
}