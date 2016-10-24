package com.yangyang.designParttern;

abstract class BasicEnginering{
    private void math(){
        System.out.println("mathmatics ...");
    }
    private void softSkills(){
        System.out.println("softSkills");
    }
    public void pagers(){
        math();
        softSkills();
        special();
    }
    abstract void special();
}
class ComputeScience extends BasicEnginering{
    @Override
    void special() {
        System.out.println("basic science ...");
    }
}
class ElectricSicence extends BasicEnginering{
    @Override
    void special() {
        System.out.println("electric science ...");
    }
}

public class TemplateMethod {
    public static void main(String[] args) {
        ComputeScience cs = new ComputeScience();
        cs.pagers();

        ElectricSicence es = new ElectricSicence();
        es.pagers();

    }
}
