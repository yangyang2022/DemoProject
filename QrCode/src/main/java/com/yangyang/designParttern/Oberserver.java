package com.yangyang.designParttern;


import java.util.ArrayList;
import java.util.List;

interface IObserver{
    void update(int i);
}
interface ISubject{
    void register(IObserver observer);
    void remove(IObserver observer);
    void notifyAllOberver();
}
class Observer implements IObserver{
    @Override
    public void update(int i) {
        System.out.println("observer cahnged ... "+i);
    }
}
class Subject implements ISubject{
    private List<IObserver> obs = new ArrayList<>();
    private int _flag;

    public int get_flag() {
        return _flag;
    }

    public void set_flag(int _flag) {
        this._flag = _flag;
        notifyAllOberver();
    }

    @Override
    public void register(IObserver observer) {
        obs.add(observer);
    }

    @Override
    public void remove(IObserver observer) {
        obs.remove(observer);
    }

    @Override
    public void notifyAllOberver() {
        obs.forEach(e->e.update(_flag));
    }
}
public class Oberserver {

    public static void main(String[] args) {

        IObserver o1 = new Observer();
        IObserver o2 = new Observer();
        IObserver o3 = new Observer();
        IObserver o4 = new Observer();

        Subject subject = new Subject();
        subject.register(o1);
        subject.register(o2);
        subject.register(o3);

        subject.set_flag(5);

        System.out.println("====================");
        subject.remove(o2);
        subject.remove(o3);
        subject.register(o4);

        subject.set_flag(10);


    }
}
