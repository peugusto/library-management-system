package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Locale;

import db.DB;
import model.entities.Usuario;


public class Program {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int op =  0;
		
		do {
			System.out.println("1. Cadastrar usuários");
			System.out.println("2. Listar todos os usuários;");
			System.out.println("3- Cadastrar livros");
			System.out.println("4- Listar todos os livros");
			System.out.println("5- Realizar empréstimos");
			System.out.println("6- Renovar / Encerrar emprestimos");
		}while(op <= 0 || op > 7);
		
		switch (op) {
		case 1: 
			System.out.println("---CADASTRO DE USUÁRIOS---");

			try {
				System.out.print("Nome: ");
				String nome = reader.readLine();
				
				System.out.print("Email: ");
				String email = reader.readLine();
				
				Usuario user = new Usuario(nome,email);
				
				
				
			} catch (IOException e) {
				System.out.println("ERRO: " + e.getMessage());
			}
		}
		
	}
}
