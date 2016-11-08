package com.yangyang.DesignPattern;

abstract class Group{
    public abstract void find();
    public abstract void add();
    public abstract void delete();
    public abstract void change();
    public abstract void plan();
}
class RequirementGroup extends Group{
    @Override
    public void find() {
        System.out.println("find reuirementGroup ...[requirementGroup]");
    }

    @Override
    public void add() {
        System.out.println("client add a function ...[requirementGroup]");
    }

    @Override
    public void delete() {
        System.out.println("client delete a function ...[requirementGroup]");
    }

    @Override
    public void change() {
        System.out.println("client change a function ...[requirementGroup]");
    }

    @Override
    public void plan() {
        System.out.println("client change plan ...[requirementGroup]");
    }
}

class PageGroup extends Group{
    @Override
    public void find() {
        System.out.println("find PageGroup ...[pageGroup]");
    }

    @Override
    public void add() {
        System.out.println("client add a function ... [pageGroup]");
    }

    @Override
    public void delete() {
        System.out.println("client delete a function ...[pageGroup]");
    }

    @Override
    public void change() {
        System.out.println("client change a function ...[pageGroup]");
    }

    @Override
    public void plan() {
        System.out.println("client change plan ...[pageGroup]");
    }
}

class CodeGroup extends Group{
    @Override
    public void find() {
        System.out.println("find CodeGroup ...[codeGroup]");
    }

    @Override
    public void add() {
        System.out.println("client add a function ... [codeGroup]");
    }

    @Override
    public void delete() {
        System.out.println("client delete a function ...[codeGroup]");
    }

    @Override
    public void change() {
        System.out.println("client change a function ...[codeGroup]");
    }

    @Override
    public void plan() {
        System.out.println("client change plan ...[codeGroup]");
    }
}

abstract class Command{

    protected RequirementGroup rg = new RequirementGroup();
    protected PageGroup pg = new PageGroup();
    protected CodeGroup cg = new CodeGroup();

    public abstract void execute();
}

class AddRequirement extends Command{
    @Override
    public void execute() {
        super.rg.find();
        super.rg.add();
        super.rg.plan();
    }
}
class DeletePageCommand extends Command{
    @Override
    public void execute() {
        super.pg.find();
        super.pg.add();
        super.pg.plan();
    }
}
class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }
    public void action(){
        this.command.execute();
    }
}

abstract class Reciver{
    public abstract void dosomething();
}
class ConcreteReciver extends Reciver{
    @Override
    public void dosomething() {
        System.out.println("concreate reciver ...");
    }
}
public class CommandPattern {

    public static void main(String[] args) {

        Invoker xiaosan = new Invoker();
        xiaosan.setCommand(new AddRequirement());
        xiaosan.action();

        System.out.println("=======================================");
        xiaosan.setCommand(new DeletePageCommand());
        xiaosan.action();

    }
}
