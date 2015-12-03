package fprg.figl.winterhalder;

import java.util.ArrayList;

public class Main {

    interface Itivial<A> {
        boolean apply(ArrayList<A> input);
    }

    interface Isolve<A,B> {
        B apply(ArrayList<A> input);
    }

    interface Idivide<A> {
        Tupel<A> apply(ArrayList<A> input);
    }

    interface Icombine<B> {
        B apply(B input1, B input2);
    }

    public static void main(String[] args) {
        //Integer
        ArrayList<Integer> inputInteger = new ArrayList<Integer>(){{
            add(34);
            add(7);
            add(5);
            add(44);
        }};

        ArrayList<Integer> quicksorted = divideAndConquer(  (x) -> {(x.size() < 3)},
                                                            (x,y)->{
                                                                ArrayList<Integer> result = new ArrayList<Integer>();
                                                                if(x > y){
                                                                    result.add(y);
                                                                    result.add(x);
                                                                }else{
                                                                    result.add(x);
                                                                    result.add(y);
                                                                }
                                                                return result;
                                                            },
                                                            inputInteger)
    }

    private static  <A,B> B divideAndConquer(Itivial<A> tivial, Isolve<A,B> solve,Idivide<A> divide,Icombine<B> combine, ArrayList<A> input){

        return divideAndConquerRec(tivial, solve, divide, combine, input);

    }

    private static  <A,B> B divideAndConquerRec(Itivial<A> tivial, Isolve<A,B> solve,Idivide<A> divide,Icombine<B> combine, ArrayList<A> input) {
        if(tivial.apply(input)) {
            return solve.apply(input);
        }else{
            B left = divideAndConquerRec(tivial, solve, divide, combine, divide.apply(input).left);
            B right = divideAndConquerRec(tivial, solve, divide, combine, divide.apply(input).right);
            return combine.apply(left, right);
        }
    }

        public class Tupel<A> {
            public ArrayList<A> left;
            public ArrayList<A> right;
    }
}

