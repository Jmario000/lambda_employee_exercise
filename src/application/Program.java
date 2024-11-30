package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import model.entities.Employee;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		String path = JOptionPane.showInputDialog("Enter full file path:");

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> list = new LinkedList<>();

			double baseSalary = Double.parseDouble(JOptionPane.showInputDialog("Enter base salary:"));
			String line;

			while ((line = br.readLine()) != null) {
				String fields[] = line.split(",");
				String name = fields[0];
				String email = fields[1];
				double salary = Double.parseDouble(fields[2]);

				list.add(new Employee(name, email, salary));
			}

			List<String> emailList = list.stream().filter(e -> e.getSalary() > baseSalary).map(Employee::getEmail)
					.sorted().collect(Collectors.toList());

			double startingNameWithMsalarySum = list.stream().filter(e -> e.getName().charAt(0) == 'M')
					.mapToDouble(e -> e.getSalary()).sum();

			StringBuilder sb = new StringBuilder(
					"Email of people whose salary is greater than $ " + String.format("%.2f", baseSalary) + ":\n");

			for (String s : emailList) {
				sb.append(s).append("\n");
			}

			sb.substring(0, sb.length() - 1);
			sb.append("Sum of salary of people whose name starts with 'M': $ "
					+ String.format("%.2f", startingNameWithMsalarySum));

			JOptionPane.showMessageDialog(null, sb);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
