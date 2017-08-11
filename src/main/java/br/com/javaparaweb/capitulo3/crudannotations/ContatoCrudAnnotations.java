package br.com.javaparaweb.capitulo3.crudannotations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.javaparaweb.capitulo3.conexao.HibernateUtil;
import br.com.javaparaweb.capitulo3.pojo.Contato;

public class ContatoCrudAnnotations
{
	private Session sessao;
	
	public ContatoCrudAnnotations(Session sessao)
	{
		this.sessao = sessao;
	}
	
	public void salvar(Contato contato)
	{
		sessao.save(contato);
	}
	
	public void atualizar(Contato contato)
	{
		sessao.update(contato);
	}
	
	public List<Contato> listaContatos()
	{
		return sessao.createCriteria(Contato.class).list();
	}
	
	public static void listarContatos()
	{
		List<Contato> listaContatos = new ArrayList<Contato>();
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = sessao.beginTransaction();
		ContatoCrudAnnotations contatoCrud = new ContatoCrudAnnotations(sessao);
		listaContatos = contatoCrud.listaContatos();
		for (Contato contato : listaContatos)
		{
			System.out.println(contato.getNome());
		}
	}
	
	public static void inserirRegistro()
	{
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = sessao.beginTransaction();
		ContatoCrudAnnotations contatoCrud = new ContatoCrudAnnotations(sessao);
		
		Contato contato1 = new Contato();
		contato1.setNome("Ricardo");
		contato1.setEmail("rmgimenez@gmail.com");
		contato1.setCelular("123456");
		
		contatoCrud.salvar(contato1);
		
		transacao.commit();
		
		transacao.begin();
		contato1.setNome("Ricardo Gimenez");
		contatoCrud.atualizar(contato1);
		transacao.commit();
	}
	
	public static void main(String[]args)
	{
		/*
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = sessao.beginTransaction();
		ContatoCrudAnnotations contatoCrud = new ContatoCrudAnnotations(sessao);
		
		Contato contato1 = new Contato();
		contato1.setNome("Ricardo");
		contato1.setEmail("rmgimenez@gmail.com");
		contato1.setCelular("123456");
		
		contatoCrud.salvar(contato1);
		
		transacao.commit();
		
		transacao.begin();
		contato1.setNome("Ricardo Gimenez");
		contatoCrud.atualizar(contato1);
		transacao.commit();
		*/
		ContatoCrudAnnotations.listarContatos();
		
		
	}

}
