package br.com.treinaweb.jpa.ui;

import java.util.List;
import java.util.Scanner;

import br.com.treinaweb.jpa.models.Pessoa;
import br.com.treinaweb.jpa.services.impl.PessoaService;
import br.com.treinaweb.jpa.services.interfaces.CrudService;

public class Main {

	public static void main(String[] args) {
		listarPessoas();
		int opcao = 0;
		while (opcao != 6) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("\n Escolha uma opção: ");
			System.out.println("1. Listar pessoas");
			System.out.println("2. Inserir pessoa");
			System.out.println("3. Atualizar pessoa");
			System.out.println("4. Excluir pessoa");
			System.out.println("5. Pesquisar pessoa por nome");
			System.out.println("6. Sair");
			System.out.print("\n Sua opção: ");
			opcao = scanner.nextInt();
			switch (opcao) {
			case 1:
				listarPessoas();
				break;
			case 2:
				inserirPessoa();
				break;
			default:
				System.out.println(" ** Opção inválida! **");
				break;
			}

		}
	}

	private static void inserirPessoa() {
		System.out.println("\n ** Inclusão de pessoa **");
		try (Scanner scanner = new Scanner(System.in)) {
			Pessoa novaPessoa = new Pessoa();
			System.out.print("Nome: ");
			novaPessoa.setNome(scanner.nextLine());
			System.out.print("Sobrenome: ");
			novaPessoa.setSobrenome(scanner.nextLine());
			System.out.print("Idade: ");
			novaPessoa.setIdade(scanner.nextInt());
			CrudService<Pessoa, Integer> pessoaService = new PessoaService();
			pessoaService.insert(novaPessoa);
			System.out.println(" - Pessoa inserida com sucesso!");
		}
	}

	private static void listarPessoas() {
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();
		System.out.println("**** GERENCIAMENTO DE PESSOAS ****");
		System.out.println("> Lista de pessoas cadastradas: \n");
		try {
			List<Pessoa> pessoas = pessoaService.all();
			pessoas.forEach(pessoa -> {
				System.out.println(String.format(" - (%d) %s %s - %d anos", pessoa.getId(), pessoa.getNome(),
						pessoa.getSobrenome(), pessoa.getIdade()));
			});
			if (pessoas.isEmpty()) {
				System.out.println(" - Não existem pessoas cadastradas.");
			}
		} catch (Exception e) {
			System.out.println("Houve um erro ao utilizar a JPA: " + e.getMessage());
		}
	}

}
