package com.yangyang.javaPerformce;

import java.util.function.BinaryOperator;
import java.util.function.Supplier;

enum StrategyEnum{
    ADD(() -> (x,y) -> x + y),
    MULTI(()->(x,y) ->x * y)
    ;
    private Supplier<BinaryOperator<Integer>> operator;

    StrategyEnum(Supplier<BinaryOperator<Integer>> operator) {
        this.operator = operator;
    }
    BinaryOperator get(){return operator.get();}

}
class ContxetFp{
    private StrategyEnum strategy_enum;

    public ContxetFp(StrategyEnum strategy_enum) {
        this.strategy_enum = strategy_enum;
    }
    public void calculate(Integer first,Integer second){
        System.out.println(strategy_enum.get().apply(first, second));
    }
}
public class Strategy {

    public static void main(String[] args) {

        new ContxetFp(StrategyEnum.ADD).calculate(3,2);
        new ContxetFp(StrategyEnum.MULTI).calculate(3,2);

    }
}
