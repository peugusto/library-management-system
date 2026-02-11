package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import db.DB;
import db.DBException;
import model.entities.Emprestimo;
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

		int op = 0;

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
					System.out.println("4. Desativar usuário");
					System.out.println("5. Sair");
					System.out.print("Opção: ");
					opUsuario = Integer.parseInt(reader.readLine());

					switch (opUsuario) {
					case 1:
						try {
							UsuarioService service = new UsuarioService();
							System.out.print("Nome: ");
							String nome = reader.readLine();

							System.out.print("Email: ");
							String email = reader.readLine();

							service.validarCampos(new Usuario(nome, email));

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
								System.out.println("Ativo: " + u.getAtivo().toString());
								System.out.println("------");
							}
							int opEdit = 0;
							do {
								System.out.println("1.Editar email de um usuário.");
								System.out.println("2. Sair.");

								if (opEdit == 1) {

									System.out.print("Digite o id do usuario para editar o email: ");
									int id = Integer.parseInt(reader.readLine());

									System.out.print("Digite o novo email: ");
									String email = reader.readLine();

									service.editarEmail(id, email);
								}
							} while (opEdit != 2);

						} catch (BusinessException e) {
							System.out.println("ERRO: " + e.getMessage());
						} catch (DBException e) {
							System.out.println("ERRO: " + e.getMessage());
						} catch (IOException e) {
							System.out.println("ERROR: " + e.getMessage());
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
						} catch (BusinessException e) {
							System.out.println("ERRO: " + e.getMessage());
						} catch (DBException e) {
							System.out.println("ERRO: " + e.getMessage());
						} catch (IOException e) {
							System.out.println("ERROR: " + e.getMessage());
						}
						break;
					case 4:

						try {
							UsuarioService service = new UsuarioService();
							System.out.println("Digite o id do usuário para deletar: ");
							int id = Integer.parseInt(reader.readLine());

							service.desativarUsuário(id);

							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
						} catch (BusinessException e) {
							System.out.println("ERRO: " + e.getMessage());
						} catch (DBException e) {
							System.out.println("ERRO: " + e.getMessage());
						} catch (IOException e) {
							System.out.println("ERROR: " + e.getMessage());
						}
						break;
					}

				} while (opUsuario != 5);
				break;
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
					switch (opLivro) {
					case 1:

						try {
							LivroService service = new LivroService();
							System.out.print("Digite o autor do livro: ");
							String autor = reader.readLine();
							System.out.print("Digite o titulo do livro: ");
							String titulo = reader.readLine();
							System.out.print("Digite o insira o ano de publicação do livro: ");
							String data = reader.readLine();

							Livro obj = new Livro(titulo, autor, data);
							service.adicionarLivro(obj);

							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();

						} catch (IOException e) {
							System.out.println("ERROR: " + e.getMessage());
						} catch (BusinessException e) {
							System.out.println("ERROR: " + e.getMessage());
						} catch (DBException e) {
							System.out.println("ERRO: " + e.getMessage());
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
								System.out.println("Disponivel: " + livro.getDisponivel().toString());
								System.out.println("------");
							}

							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
						} catch (BusinessException e) {
							System.out.println("ERROR: " + e.getMessage());
						} catch (DBException e) {
							System.out.println("ERRO: " + e.getMessage());
						} catch (IOException e) {
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
						} catch (IOException e) {
							System.out.println("ERROR: " + e.getMessage());
						} catch (BusinessException e) {
							System.out.println("ERROR: " + e.getMessage());
						} catch (DBException e) {
							System.out.println("ERRO: " + e.getMessage());
						}
						break;

					}
				} while (opLivro != 4);
				break;
			case 3:

				try {

					EmprestimoService service = new EmprestimoService();

					System.out.print("Digite o id do usuário: ");
					int idUsuario = Integer.parseInt(reader.readLine());
					System.out.print("Digite o id do livro: ");
					int idLivro = Integer.parseInt(reader.readLine());

					service.salvarEmprestimo(idLivro, idUsuario);

					System.out.println("Pressione qualquer tecla para voltar ao menu.");
					reader.readLine();
				} catch (IOException e) {
					System.out.println("ERROR: " + e.getMessage());
				} catch (BusinessException e) {
					System.out.println("ERROR: " + e.getMessage());
				} catch (DBException e) {
					System.out.println("ERRO: " + e.getMessage());
				}

				break;
			case 4:

				try {
					EmprestimoService service = new EmprestimoService();
					LivroService serviceLivro = new LivroService();
					UsuarioService serviceUsuario = new UsuarioService();

					System.out.println("Digite o id do usuario: ");
					int id = Integer.parseInt(reader.readLine());

					Emprestimo emp = service.retornarEmprestimo(id);

					Livro livro = serviceLivro.retornar(emp.getId_livro());
					Usuario user = serviceUsuario.retornar(emp.getId_usuario());

					System.out.println("Empréstimo em ativo: ");
					System.out.println("ID: " + emp.getId());
					System.out.println("Usuario: " + user.getNome());
					System.out.println("Titulo: " + livro.getTitulo());
					System.out.println("Acaba em: " + emp.getDataFim());
					System.out.println("-----");

					int opEmprestimo = 0;

					do {
						System.out.println("1. Encerrar o empréstimo");
						System.out.println("2. Prolongar o empréstimo");
						System.out.println("3. Sair");
						opEmprestimo = Integer.parseInt(reader.readLine());
						switch (opEmprestimo) {
						case 1:
							System.out.println("Pressione qualquer tecla para confirmar.");
							reader.readLine();
							emp.encerrar();
							service.cancelarEmprestimo(emp);

							System.out.println("Pressione qualquer tecla para voltar ao menu.");
							reader.readLine();
							op = 3;
							break;
						case 2:
							System.out.println("A Renovação adicionará 7 dias no empréstimo.");
							System.out.println("Pressione qualquer tecla para confirmar.");
							reader.readLine();

							emp.renovar();
							service.atualizarEmprestimo(emp);
							op = 3;
							break;

						}
					} while (op != 3);

				} catch (IOException e) {
					System.out.println("ERROR: " + e.getMessage());
				} catch (BusinessException e) {
					System.out.println("ERROR: " + e.getMessage());
				} catch (DBException e) {
					System.out.println("ERRO: " + e.getMessage());
				}

				break;
			}

		} while (op != 5);

		DB.closeConnection();
	}

}
