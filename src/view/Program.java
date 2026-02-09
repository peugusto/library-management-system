package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import db.DB;
import model.entities.Livro;
import model.entities.Usuario;
import model.services.BusinessException;
import model.services.EmprestimoService;
import model.services.LivroService;
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
					System.out.println("2. Listar usuário.");
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
							
							System.out.print("Digite o id do usuario para editar o email: ");
							int id = Integer.parseInt(reader.readLine());
							
							System.out.print("Digite o novo email: ");
							String email = reader.readLine();
							
							service.editarEmail(id, email);
							
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
						default:
							System.out.println("Comando inválido");
							break;
						
					}

				
				}while(opUsuario != 4);
			case 2:
				int opLivro = 0;
				do {
					System.out.println("\n---LIVROS---");
					System.out.println("1. Cadastrar livro");
					System.out.println("2. Listar os livros");
					System.out.println("3. Excluir um livro");
					System.out.println("4. Sair");
					
					System.out.print("Opção: ");
					opLivro = Integer.parseInt(reader.readLine());
					switch(opLivro) {
					case 1:
						
						try {
							LivroService service = new LivroService();
							System.out.print("Digite o autor do livro: ");
							String autor = reader.readLine();
							System.out.print("Digite o titulo do livro: ");
							String titulo = reader.readLine();
							System.out.print("Digite o insira o ano de publicação do livro: ");
							String data = reader.readLine();
							
				            Livro obj = new Livro(autor,titulo,data);
				            service.adicionarLivro(obj);
				            
				            System.out.println("Livro inserido no banco de dados com sucesso!");
				            
							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
				            
						}catch(IOException e) {
							System.out.println("ERROR: " + e.getMessage());
						}catch(BusinessException e) {
							System.out.println("ERROR: " + e.getMessage());
						}
			            
						break;
					case 2:

						try {
							
							LivroService service = new LivroService();
							
							List<Livro> livros = new ArrayList<>();
							livros = service.retonarLivros();
							
							for (Livro livro : livros) {
								
								System.out.println("ID: " + livro.getId());
								System.out.println("Titulo: " + livro.getTitulo());
								System.out.println("Autor: " + livro.getAutor());
								System.out.println("Ano de publicação: " + livro.getAnoPublicacao());
								System.out.println("Disponivel: " + livro.getDisponivel());
								System.out.println("------");
							}
							
							
							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
						}catch(BusinessException e) {
							System.out.println("ERROR: " + e.getMessage());
						}
						
						break;
					case 3:
						
						try {
							LivroService service = new LivroService();
							System.out.println("Digite o id do livro: ");
							int id = Integer.parseInt(reader.readLine());
							
							service.deletarLivro(id);
							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
						}catch(IOException e) {
							System.out.println("ERROR: " + e.getMessage());
						}catch(BusinessException e) {
							System.out.println("ERROR: " + e.getMessage());
						}

						break;
					default:
						System.out.println("Comando inválido");
						break;
					}
					
				}while(opLivro != 4);
				break;
			case 3:
				
				EmprestimoService service = new EmprestimoService();
				
				try {
					System.out.println("Digite o id do usuário: ");
					int idUsuario = Integer.parseInt(reader.readLine());
					System.out.println("Digite o id do livro: ");
					int idLivro = Integer.parseInt(reader.readLine());
					
					service.salvarEmprestimo(idLivro, idUsuario);
				
					System.out.println("Pressione qualquer tecla para voltar ao menu.");
					reader.readLine();
				}catch(IOException e) {
					System.out.println("ERROR: " + e.getMessage());
				}catch(BusinessException e) {
					System.out.println("ERROR: " + e.getMessage());
				}
				

				break;
			case 4:
				System.out.println("");
				break;
			}

				
		}while(op != 5);
		
		DB.closeConnection();
	}

}
