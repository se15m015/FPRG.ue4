package fprg.figl.winterhalder;

import java.util.ArrayList;

public class Main {

    interface  ICompare<A>{
        boolean ShouldAddToList(A value1, A value2);
    }

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
        ArrayList<Character> inputCharacters = new ArrayList<Character>() {{
            add('h');
            add('e');
            add('l');
            add('l');
            add('o');
            add(' ');
            add('w');
            add('o');
            add('r');
            add('l');
            add('d');
        }};

        System.out.println("Input1: " + inputInteger);
        System.out.println("Input2: " + inputCharacters);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////                 - STARTING QUICKSORT -                       ///////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

                    result.left = divideListQuicksort((fitem,fpivot) ->  fitem < fpivot,pivot,x);
                    result.right = divideListQuicksort((fitem,fpivot) ->  fitem >= fpivot,pivot,x);

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

                    ArrayList<Integer> newList = new ArrayList<Integer>();
                    newList.addAll(left);
                    newList.addAll(right);
                    return  newList;
                },
                inputInteger);

        System.out.println("result Quicksort: " + quicksorted);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Character> quicksortedChars = divideAndConquer(
                (x) -> (x.size() < 3),
                (x) -> {
                    ArrayList<Character> result = new ArrayList<>();
                    if(x.size()==0)
                    {
                        return result;
                    }

                    if(x.size()==1)
                    {
                        result.add(x.get(0));
                    }
                    else {

                        Character first = x.get(0);
                        Character second = x.get(1);

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

                    Character pivot = x.get(0);
                    Tuple<Character> result = new Tuple<>();
                    result.left = new ArrayList<>();
                    result.right = new ArrayList<>();

                    result.left = divideListQuicksort((fitem,fpivot) ->  fitem < fpivot,pivot,x);
                    result.right = divideListQuicksort((fitem,fpivot) ->  fitem >= fpivot,pivot,x);

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

                    ArrayList<Character> newList = new ArrayList<>();
                    newList.addAll(left);
                    newList.addAll(right);
                    return  newList;
                },
                inputCharacters);

        System.out.println("result Quicksort: " + quicksortedChars);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////                 - STARTING MERGESORT -                       ///////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //https://de.wikipedia.org/wiki/Mergesort
        //https://en.wikipedia.org/wiki/Merge_sort
        ArrayList<Integer> mergesorted = divideAndConquer(
                (x) -> (x.size() == 1),
                (x) -> (x),
                (x) -> {
                    Tuple<Integer> result = new Tuple<Integer>();

                    Integer middle = x.size()/2;

                    result.left =  new ArrayList<Integer>(x.subList(0,middle));
                    result.right = new ArrayList<Integer>(x.subList(middle,x.size()));

                    return result;

                },
                (left, right) -> {
                    return  combineMergeSort((fvalue1,fvalue2) ->  fvalue1 < fvalue2,left,right);
                },
                inputInteger);

        System.out.println("result Mergesort: " + mergesorted);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<Character> mergesortedCharacters = divideAndConquer(
                (x) -> (x.size() == 1),
                (x) -> (x),
                (x) -> {
                    Tuple<Character> result = new Tuple<>();

                    Integer middle = x.size()/2;

                    result.left =  new ArrayList<>(x.subList(0,middle));
                    result.right = new ArrayList<>(x.subList(middle,x.size()));

                    return result;

                },
                (left, right) -> {
                    return combineMergeSort((fvalue1,fvalue2) ->  fvalue1 < fvalue2,left,right);
                },
                inputCharacters);

        System.out.println("result Mergesort: " + mergesortedCharacters);

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

    private  static <A>  ArrayList<A> divideListQuicksort(ICompare<A> func, A pivot, ArrayList<A> input)
    {
        ArrayList<A> output = new ArrayList<A>();
        return divideListQuicksortRec(func,pivot,input,output,0);
    }

    private  static <A>  ArrayList<A> divideListQuicksortRec(ICompare<A> func, A pivot, ArrayList<A> input, ArrayList<A> output, Integer count)
    {
        if(count == input.size())
            return output;

        if(func.ShouldAddToList(input.get(count),pivot))
        {
            output.add(input.get(count));
        }

        return divideListQuicksortRec(func,pivot,input,output,++count);
    }

    private static <A> ArrayList<A> combineMergeSort(ICompare<A> func, ArrayList<A> left, ArrayList<A> right)
    {
        ArrayList<A> output = new ArrayList<A>();
        return combineMergeSortRec(func,left,right,output,0,0);
    }

    private static <A> ArrayList<A> combineMergeSortRec(ICompare<A> func, ArrayList<A> left, ArrayList<A> right, ArrayList<A> output, Integer countLeft, Integer countRight)
    {
        if(countLeft == left.size())
        {
            output.addAll(new ArrayList<A>(right.subList(countRight,right.size())));
            return output;
        }

        if(countRight == right.size())
        {
            output.addAll(new ArrayList<A>(left.subList(countLeft,left.size())));
            return output;
        }

        if(func.ShouldAddToList(left.get(countLeft),right.get(countRight)))
        {
            output.add(left.get(countLeft));
            return combineMergeSortRec(func,left,right,output,++countLeft,countRight);
        }
        else
        {
            output.add(right.get(countRight));
            return combineMergeSortRec(func,left,right,output,countLeft,++countRight);
        }
    }
}

