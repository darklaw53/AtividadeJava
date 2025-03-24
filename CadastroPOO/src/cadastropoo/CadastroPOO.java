package cadastropoo;

import java.io.IOException;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO 
{
    public static void main(String[] args) 
    {
        try (Scanner scanner = new Scanner(System.in)) 
        {
            PessoaFisicaRepo repoPF = new PessoaFisicaRepo();
            PessoaJuridicaRepo repoPJ = new PessoaJuridicaRepo();
            
            while (true) 
            {
                System.out.println("=================================");
                System.out.println("1 - Incluir Pessoa");
                System.out.println("2 - Alterar Pessoa");
                System.out.println("3 - Excluir Pessoa");
                System.out.println("4 - Buscar pelo Id");
                System.out.println("5 - Exibir Todos");
                System.out.println("6 - Persistir Dados");
                System.out.println("7 - Recuperar Dados");
                System.out.println("0 - Finalizar Programa");
                System.out.println("=================================");
                 
                int opcao = scanner.nextInt();
                scanner.nextLine();
                
                if (opcao == 0) break;
                
                char tipo = 'x';
                if (opcao != 6 && opcao != 7)
                {
                    do 
                    {
                        System.out.print("F - Pessoa Física | J - Pessoa Jurídica\n");
                        tipo = scanner.next().toUpperCase().charAt(0);
                        scanner.nextLine(); 
                    } 
                    while (tipo != 'F' && tipo != 'J');
                }

                switch (opcao) 
                {
                    case 1 -> 
                    {
                        System.out.print("Digite o id da pessoa:\n");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Insira os dados...\n");
                        System.out.print("Nome:\n");
                        String nome = scanner.nextLine();
                        
                        if (tipo == 'F') 
                        {
                            System.out.print("CPF:\n");
                            String cpf = scanner.nextLine();
                            System.out.print("Idade:\n");
                            int idade = scanner.nextInt();
                            scanner.nextLine();
                            repoPF.inserir(new PessoaFisica(id, nome, cpf, idade));
                        } 
                        else 
                        {
                            System.out.print("CNPJ:\n");
                            String cnpj = scanner.nextLine();
                            repoPJ.inserir(new PessoaJuridica(id, nome, cnpj));
                        }
                    }
                    case 2 -> 
                    {
                        System.out.print("ID:\n");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 'F') 
                        {
                            PessoaFisica pf = repoPF.obter(id);
                            if (pf != null) 
                            {
                                System.out.println("Dados atuais:");
                                System.out.println("Nome: " + pf.getNome());
                                System.out.println("CPF: " + pf.getCpf());
                                System.out.println("Idade: " + pf.getIdade());
                                System.out.print("Novo nome:\n");
                                pf.setNome(scanner.nextLine());
                                System.out.print("Novo CPF:\n");
                                pf.setCpf(scanner.nextLine());
                                System.out.print("Nova idade:\n");
                                pf.setIdade(scanner.nextInt());
                                scanner.nextLine();
                            }
                        } 
                        else 
                        {
                            PessoaJuridica pj = repoPJ.obter(id);
                            if (pj != null) 
                            {
                                System.out.println("Dados atuais:");
                                System.out.println("Nome: " + pj.getNome());
                                System.out.println("CNPJ: " + pj.getCnpj());
                                System.out.print("Novo nome:\n");
                                pj.setNome(scanner.nextLine());
                                System.out.print("Novo CNPJ:\n");
                                pj.setCnpj(scanner.nextLine());
                            }
                        }
                    }
                    case 3 -> 
                    {
                        System.out.print("ID:\n");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 'F') repoPF.excluir(id);
                        else repoPJ.excluir(id);
                    }
                    case 4 -> 
                    {
                        System.out.print("ID:\n");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 'F') {
                            PessoaFisica pf = repoPF.obter(id);
                            if (pf != null) {
                                System.out.println("Nome: " + pf.getNome());
                                System.out.println("CPF: " + pf.getCpf());
                                System.out.println("Idade: " + pf.getIdade());
                            }
                        } else {
                            PessoaJuridica pj = repoPJ.obter(id);
                            if (pj != null) {
                                System.out.println("Nome: " + pj.getNome());
                                System.out.println("CNPJ: " + pj.getCnpj());
                            }
                        }
                    }
                    case 5 ->
                    {
                        if (tipo == 'F') repoPF.obterTodos().forEach(pf -> {
                            System.out.println("---------------------------------");
                            System.out.println("Nome: " + pf.getNome());
                            System.out.println("CPF: " + pf.getCpf());
                            System.out.println("Idade: " + pf.getIdade());
                        });
                        else repoPJ.obterTodos().forEach(pj -> {
                            System.out.println("---------------------------------");
                            System.out.println("Nome: " + pj.getNome());
                            System.out.println("CNPJ: " + pj.getCnpj());
                        });
                    }
                    case 6 -> 
                    {
                        try 
                        {
                            System.out.print("Prefixo do arquivo:\n");
                            String prefixo = scanner.nextLine();
                            repoPF.persistir(prefixo + ".fisica.bin");
                            repoPJ.persistir(prefixo + ".juridica.bin");
                        } 
                        catch (IOException e) 
                        {
                            System.err.println("Erro ao salvar dados: " + e.getMessage());
                        }
                    }
                    case 7 -> 
                    {
                        try 
                        {
                            System.out.print("Prefixo do arquivo:\n");
                            String prefixo = scanner.nextLine();
                            repoPF.recuperar(prefixo + ".fisica.bin");
                            repoPJ.recuperar(prefixo + ".juridica.bin");
                        } 
                        catch (IOException | ClassNotFoundException e) {
                            System.err.println("Erro ao recuperar dados: " + e.getMessage());
                        }
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }
        }
    }
}