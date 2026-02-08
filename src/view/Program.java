package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import db.DB;
import model.entities.Usuario;
import model.services.BusinessException;
import model.services.UsuarioService;

public class Program {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Locale.setDefault(Locale.US);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int op =  0;
		
		do {
			System.out.println("\n--- Sistema de biblioteca ---");
			System.out.println("1. Gerenciar usuários");
			System.out.println("2. Gerenciar livros");
			System.out.println("3. Realizar empréstimos");
			System.out.println("4. Renovar / Encerrar emprestimos");
			System.out.println("5. Sair.");
			System.out.print("Opção: ");
			op = Integer.parseInt(reader.readLine());
			
			switch (op) {
			case 1: 
				
				int opUsuario = 0;

				do {
					System.out.println("\n--- USUÁRIOS ---");
					System.out.println("1. Cadastrar usuário");
					System.out.println("2. Editar email do usuário.");
					System.out.println("3. Excluir usuário");
					System.out.println("4. Sair");
					System.out.print("Opção: ");
					opUsuario = Integer.parseInt(reader.readLine());
					
					switch(opUsuario) {
					case 1:
						try {
					        UsuarioService service = new UsuarioService();
							System.out.print("Nome: ");
							String nome = reader.readLine();
							
							System.out.print("Email: ");
							String email = reader.readLine();

					        service.validarCampos(new Usuario(nome,email));
					        
							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
						} catch (IOException e) {
							System.out.println("ERRO: " + e.getMessage());
						} catch (BusinessException e) {
							System.out.println("ERRO: " + e.getMessage());
						}
						break;
					case 2:
						
						try {
							UsuarioService service = new UsuarioService();
							List<Usuario> users = new ArrayList<>();
							users = service.retornarUsuarios();
							
							for (Usuario u : users) {
								System.out.println("");
								System.out.println("ID: " + u.getId());
								System.out.println("Nome: " + u.getNome());
								System.out.println("Email: " + u.getEmail());
								System.out.println("Ativo: " + u.getAtivo());
								System.out.println("------");
							}
							
							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
							
						}catch(BusinessException e) {
							System.out.println("ERRO: " + e.getMessage());
						}
						
						break;
					case 3:
						
						try {
					        UsuarioService service = new UsuarioService();
							System.out.println("Digite o id do usuário para deletar: ");
							int id = Integer.parseInt(reader.readLine());
							
							service.validarID(id);
							
							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
						}catch(BusinessException e) {
							System.out.println("ERRO: " + e.getMessage());
						}
						break;
						
					}
				
				}while(opUsuario != 4);

			}
			
			
		}while(op != 4);
		

		DB.closeConnection();
	}

}
