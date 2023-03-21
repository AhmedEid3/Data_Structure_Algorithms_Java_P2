import ds.Heap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var tree = new Heap(3);
//
//        tree.insert(15);
//        tree.insert(10);
//        tree.insert(3);
//        tree.insert(8);
//        tree.insert(12);
//        tree.insert(9);
//        tree.insert(4);
//        tree.insert(1);
//        tree.insert(24);
//
//        System.out.println(tree);
//        System.out.println(tree.remove());
//        System.out.println(tree);

        int[] numbers = {5, 1, 3, 9, 6, 10};
        for (var number: numbers
             ) {
            tree.insert(number);
        }

        for (int i = numbers.length - 1; i >=0 ; i--) {
            numbers[i] = tree.remove();
        }

        System.out.println(Arrays.toString(numbers));
    }
}