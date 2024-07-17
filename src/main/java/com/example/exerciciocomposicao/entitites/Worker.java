package com.example.exerciciocomposicao.entitites;

import com.example.exerciciocomposicao.entitites.enums.WorkerLevel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Worker {
    private String name;
    private WorkerLevel level;
    private Double baseSalary;

    //Associação com as outras class, quando relacionamento é de 1X1
    private Departament department;

    //Associação com as outras class, quando relacionamento é de 1XN
    private List<HourContract> contracts = new ArrayList<>();


    public Worker(){
    }

    public Worker(String name, WorkerLevel level, Double baseSalary, Departament department ){
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;
        this.department = department;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public WorkerLevel getLevel(){
        return level;
    }

    public void setLevel(WorkerLevel level){
        this.level = level;
    }

    public Double getBaseSalary(){
        return baseSalary;
    }

    public void setBaseSalary(WorkerLevel level){
        this.level = level;
    }

    public Departament getDepartment(){
        return department;
    }

    public void setDepartment(Departament department){
        this.department = department;
    }

    public List<HourContract> getContract(){
        return contracts;
    }


    //adicionado contratos novos na lista de contratos (fazendo associação).
    public void addContract(HourContract contract){
        contracts.add(contract);
    }

    //Removendo o contrato da lista de contratros (desfazendo associação)
    public void remoeContract(HourContract contract){
        contracts.remove(contract);
    }

    public double income( int year, int month){
        double sum = baseSalary;
        Calendar cal = Calendar.getInstance();
        for( HourContract c : contracts){
            cal.setTime(c.getDate());
            int c_year = cal.get(Calendar.YEAR);
            int c_month =  1 + cal.get(Calendar.MONTH); //  +1, porque o calender começa com 0;
            if(year == c_year && month == c_month){
                sum += c.totalValue();
            }
        }
        return sum;
    }

}
