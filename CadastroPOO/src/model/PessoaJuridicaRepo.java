package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo 
{
    private List<PessoaJuridica> empresas = new ArrayList<>();

    public void inserir(PessoaJuridica empresa) 
    {
        empresas.add(empresa);
    }

    public void alterar(PessoaJuridica empresa) 
    {
        for (int i = 0; i < empresas.size(); i++) 
        {
            if (empresas.get(i).getId() == empresa.getId()) 
            {
                empresas.set(i, empresa);
                return;
            }
        }
    }

    public void excluir(int id) 
    {
        empresas.removeIf(e -> e.getId() == id);
    }

    public PessoaJuridica obter(int id) 
    {
        return empresas.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public List<PessoaJuridica> obterTodos() 
    {
        return new ArrayList<>(empresas);
    }

    public void persistir(String arquivo) throws IOException 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) 
        {
            oos.writeObject(empresas);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) 
        {
            empresas = (List<PessoaJuridica>) ois.readObject();
        }
    }
}