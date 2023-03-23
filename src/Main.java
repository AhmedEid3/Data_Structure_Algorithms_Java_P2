import ds.*;

public class Main {
    public static void main(String[] args) {
     var trie = new Trie();

     trie.insert("boy");
     trie.insert("boyin");
     trie.insert("boyins");
     trie.insert("boyiter");

//        System.out.println("Done");


//        trie.traverse();

        trie.remove(null);


        System.out.println(trie.contains("boy"));
        System.out.println(trie.contains("boyin"));

        System.out.println(trie.findWords(null));
    }


}