package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaRepo 
{
    private List<PessoaFisica> pessoas = new ArrayList<>();

    public void inserir(PessoaFisica pessoa) 
    {
        pessoas.add(pessoa);
    }

    public void alterar(PessoaFisica pessoa) 
    {
        for (int i = 0; i < pessoas.size(); i++) 
        {
            if (pessoas.get(i).getId() == pessoa.getId()) 
            {
                pessoas.set(i, pessoa);
                return;
            }
        }
    }

    public void excluir(int id) 
    {
        pessoas.removeIf(p -> p.getId() == id);
    }

    public PessoaFisica obter(int id) 
    {
        return pessoas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<PessoaFisica> obterTodos() 
    {
        return new ArrayList<>(pessoas);
    }

    public void persistir(String arquivo) throws IOException 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) 
        {
            oos.writeObject(pessoas);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) 
        {
            pessoas = (List<PessoaFisica>) ois.readObject();
        }
    }
}