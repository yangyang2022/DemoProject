package com.yangyang.effectiveJava;

public class ReflectorDemo {

    static abstract class EmplyeeType{

        public static final int ENGINEER = 0;
        public static final int SALESMAN = 1;
        public static final int MANAGER = 2;

        abstract int getTypeCode();
        abstract int payAmount(Employee employee);

    }
    static class Employee{

        private int monthSalary = 10;
        private int commission = 100;
        private int bonus = 1000;

        public int getMonthSalary() {
            return monthSalary;
        }

        public int getCommission() {
            return commission;
        }

        public int getBonus() {
            return bonus;
        }

        private EmplyeeType type;

        public Employee(EmplyeeType type) {
            this.type = type;
        }

        int payAmount(){

            return type.payAmount(this);

            //switch (getType()){
            //    case EmplyeeType.ENGINEER :return monthSalary;
            //    case EmplyeeType.MANAGER:return monthSalary+bonus;
            //    case EmplyeeType.SALESMAN:return monthSalary+commission;
            //    default:throw new RuntimeException("unkon exception!");
            //}
        }
    }

    static class Enginner extends EmplyeeType{
        @Override
        int getTypeCode() {
            return ENGINEER;
        }

        @Override
        int payAmount(Employee employee) {
            return employee.getMonthSalary();
        }
    }
    static class SaleMan extends EmplyeeType{
        @Override
        int getTypeCode() {
            return SALESMAN;
        }

        @Override
        int payAmount(Employee employee) {
            return employee.getMonthSalary()+employee.getCommission();
        }
    }
    static class Manager extends EmplyeeType{
        @Override
        int getTypeCode() {
            return MANAGER;
        }

        @Override
        int payAmount(Employee employee) {
            return employee.getMonthSalary()+employee.getBonus();
        }
    }

    public static void main(String[] args) {

        Employee e = new Employee(new Manager());
        System.out.println(e.payAmount());

    }
}
