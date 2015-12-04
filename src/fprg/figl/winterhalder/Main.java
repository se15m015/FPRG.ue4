package fprg.figl.winterhalder;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    interface Itivial<A> {
        boolean apply(ArrayList<A> input);
    }

    interface Isolve<A, B> {
        B apply(ArrayList<A> input);
    }

    interface Idivide<A> {
        Tuple<A> apply(ArrayList<A> input);
    }

    interface Icombine<B> {
        B apply(B input1, B input2);
    }

    public static void main(String[] args) {
        //Integer
        ArrayList<Integer> inputInteger = new ArrayList<Integer>() {{
            add(34);
            add(7);
            add(5);
            add(44);
            add(34);
            add(7);
            add(5);
            add(44);
            add(34);
            add(7);
            add(5);
            add(44);
            add(34);
            add(7);
            add(5);
            add(44);
            add(34);
            add(7);
            add(5);
            add(44);
            add(34);
            add(7);
            add(5);
            add(44);
        }};

        ArrayList<Integer> quicksorted = divideAndConquer(
                (x) -> (x.size() < 3),
                (x) -> {
                    ArrayList<Integer> result = new ArrayList<Integer>();
                    if(x.size()==0)
                    {
                        return result;
                    }

                    if(x.size()==1)
                    {
                        result.add(x.get(0));
                    }
                    else {

                        Integer first = x.get(0);
                        Integer second = x.get(1);

                        if (first > second) {
                            result.add(second);
                            result.add(first);
                        } else {
                            result.add(first);
                            result.add(second);
                        }
                    }
                    return result;
                },
                (x) -> {

                    Integer pivot = x.get(0);
                    Tuple<Integer> result = new Tuple<Integer>();
                    result.left = new ArrayList<Integer>();
                    result.right = new ArrayList<Integer>();

                    //todo write rec func to avoid the loop
                    for (Integer item: x) {
                        if (item < pivot) {
                            result.left.add(item);
                        }
                        else if(item >= pivot) {
                            result.right.add(item);
                        }
                    }


                    if(result.left.size()==0) {
                        result.left.add(result.right.get(0));
                        result.right.remove(0);
                    }
                    else if(result.right.size()==0) {
                        result.right.add(result.left.get(result.left.size()-1));
                        result.left.remove(result.left.size()-1);
                    }

                    return result;

                },
                (left, right) -> {

                    //todo maybe improve concat
                    ArrayList<Integer> newList = new ArrayList<Integer>(left);
                    newList.addAll(right);
                    return  newList;
                },
                inputInteger);

        System.out.println("resultQuicksort: " + quicksorted);

        //todo add second DC-method
    }

    private static <A, B> B divideAndConquer(Itivial<A> tivial, Isolve<A, B> solve, Idivide<A> divide, Icombine<B> combine, ArrayList<A> input) {

        return divideAndConquerRec(tivial, solve, divide, combine, input);

    }

    private static <A, B> B divideAndConquerRec(Itivial<A> tivial, Isolve<A, B> solve, Idivide<A> divide, Icombine<B> combine, ArrayList<A> input) {
        if (tivial.apply(input)) {
            return solve.apply(input);
        } else {
            Tuple<A> tuples = divide.apply(input);
            B left = divideAndConquerRec(tivial, solve, divide, combine, tuples.left);
            B right = divideAndConquerRec(tivial, solve, divide, combine, tuples.right);
            return combine.apply(left, right);
        }
    }


}

