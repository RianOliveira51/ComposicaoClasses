package com.example.exerciciocomposicao;

import com.example.exerciciocomposicao.entitites.Departament;
import com.example.exerciciocomposicao.entitites.HourContract;
import com.example.exerciciocomposicao.entitites.Worker;
import com.example.exerciciocomposicao.entitites.enums.WorkerLevel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/* Ler os dados de um trabalhador com N contratos (N fornecido pelo usuário). Depois, solicitar
do usuário um mês e mostrar qual foi o salario do funcionario nesse mês e mostrar qual foi o salario
nesse mês, conforme exemplo (proxima página) */

@SpringBootApplication
public class ExercicioComposicaoApplication {

    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enteder department´s name: ");
        String departmentName = sc.nextLine();

        System.out.println(("Enter Worker data: "));
        System.out.print("Name: ");
        String WorkerName = sc.nextLine();
        System.out.println("Level: ");
        String workerLevel = sc.nextLine();
        System.out.println("Base salary: ");
        double baseSalary = sc.nextDouble();
        Worker worker = new Worker(WorkerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Departament(departmentName));
        System.out.println("How many concracts to this worker?");
        int n = sc.nextInt();
        //preenchendo as classes
        for(int i =1; i<= n; i ++){
            System.out.println("Enter contract #" + i + " data: " );
            System.out.print("Date (DD/MM/YYYY): ");
            Date contractDate = sdf.parse(sc.next());
            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duration (hours): ");
            int hours = sc.nextInt();
            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);
        }
        System.out.println();
        System.out.println("enter month and year to calculate income (MM/YYYY)");
        String monthAndYear = sc.next();
        //recordando string
        int month = Integer.parseInt(monthAndYear.substring(0,2));
        int year = Integer.parseInt((monthAndYear.substring(3)));
        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getName());
        System.out.println("Incom for " + monthAndYear + ": " + String.format("%.2f",worker.income(year, month)));
    }

}
