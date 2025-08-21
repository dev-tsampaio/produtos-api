package aplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import entities.Dados;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Dados> list = new ArrayList<>();


		Integer tentivas = 3;
		String senhaCorreta = "acessoSistema2025";

		while(tentivas>0){
			System.out.println("##########################################");
			System.out.println("Painel Administrativo [Sampaio Tecnologia]");
			System.out.println("##########################################");
			System.out.println();

			System.out.print("Digite a senha de acesso ao painel: ");
			String senhaDigitada  = sc.nextLine();
			System.out.println();
				if (senhaDigitada.equals(senhaCorreta)){

					// --- Código para formatação da data/hora ---
					LocalDateTime accessTime = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					String formattedAccessTime = accessTime.format(formatter);
					// ------------------------------------------

					System.out.println("#########################################");
					System.out.println("Acesso realizado em: " + formattedAccessTime);
					System.out.println("#########################################");
					System.out.println();

							System.out.print("Quantos Funcionarios deseja registrar  na plataforma: ? ");
							int n = sc.nextInt();

								for (int i = 0; i < n; i++) {
									System.out.println("-----------------------");
									System.out.println("Funcionario #" + (i + 1) + ":");
									System.out.print("Id: ");
									Integer id = sc.nextInt();
									System.out.print("Nome: ");
									sc.nextLine();
									String name = sc.nextLine();
									System.out.print("Salario: ");
									Double salary = sc.nextDouble();

									Dados emp = new Dados(id, name, salary);

									list.add(emp);
								}

								System.out.println();
								System.out.print("Digite o ID do funcionário que você deseja aumentar o salário: ");
								Integer idSalary = sc.nextInt();
								Integer pos = position(list, idSalary);
								if (pos == null) {
									System.out.println("ERRO: O ID não existe.");
									break;
								} else {
									System.out.print("Digite a porcentagem a ser aumentada: ");
									double percente = sc.nextDouble();
									list.get(pos).increaseSalary(percente);
								}

								System.out.println("-------------------------------");
								System.out.println("Lista de funcionários:");
								System.out.println("-------------------------------");
								for (Dados emp : list) {
									System.out.println(emp);
								}


				
				} else {
					tentivas--;
					System.out.println("ERRO: Senha incorreta!");
					System.out.println("Você tem  (" +  tentivas + ") tentativas restantes.");
				}

				if (tentivas == 0) {
					System.out.println("ERRO: Acesso bloqueado, tentativas esgotadas.");
					break;
				}
		}

		sc.close();
	}

	public static Integer position(List<Dados> list, int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return null;
	}

}
