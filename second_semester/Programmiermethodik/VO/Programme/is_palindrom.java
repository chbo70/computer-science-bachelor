public class is_palindrom {
    /*public static boolean isPalindrom(String word){
        return new StringBuilder(word).reverse().toString().equals(word);
    }*/
    public static boolean conv(String word){
        return word.toCharArray();
    }
    public static boolean isPalindrom(char[] word) {
        for (int start = 0, end = word.length -1;start < end;++i) {
            if (word[start] != word[end]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        String word = "hallo";
        System.out.println(isPalindrom(conv(word)));
    }
}
