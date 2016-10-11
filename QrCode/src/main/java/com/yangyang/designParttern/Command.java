package com.yangyang.designParttern;

interface ICommand{
    void dosome();
}
class Reciver{
    public void performUndo(){
        System.out.println("undo ...");
    }
    public void performRedo(){
        System.out.println("redo ...");
    }
}
class MyRedoCommand implements ICommand{
    private Reciver reciver;

    public MyRedoCommand(Reciver reciver) {
        this.reciver = reciver;
    }

    @Override
    public void dosome() {
        reciver.performRedo();
    }
}
class MyUndoCommand implements ICommand{
    private Reciver reciver;

    public MyUndoCommand(Reciver reciver) {
        this.reciver = reciver;
    }

    @Override
    public void dosome() {
        reciver.performUndo();
    }
}

class Invoke{
    private ICommand command;
    public void ExecuteCommand(ICommand command){
        this.command = command;
        command.dosome();
    }
}
public class Command {

    public static void main(String[] args) {
        Reciver reciver = new Reciver();
        MyRedoCommand redo = new MyRedoCommand(reciver);
        MyUndoCommand undo = new MyUndoCommand(reciver);

        Invoke invoke = new Invoke();
        invoke.ExecuteCommand(redo);
        invoke.ExecuteCommand(undo);
    }
}
