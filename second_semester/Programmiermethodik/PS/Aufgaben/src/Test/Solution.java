package Test;

import java.lang.StringBuilder;
import java.util.Locale;

class Solution{
    public static String toCamelCase(String s){
        if(s==null){
            throw new NullPointerException();
        }
        if (s.equals("")){
            return s;
        }
        String[] split = s.split("-|_");
        String newString = "";
        for (int i = 0; i < split.length; i++) {
            if (i==0){
                if (split[0].substring(0,1).equals(split[0].toLowerCase().substring(0,1))){
                    newString = split[0];
                    continue;
                }
            }
            char[] chars = split[i].toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            String save = new String(chars);
            newString = newString + save;
        }
        return newString;
    }
    public static void main(String[] args){
        String name = "the-stealth-warrior";
        String name2 = "the_stealth_warrior";
        String name3 = "The-stealth-Warrior";
        System.out.println(toCamelCase(name));
        System.out.println(toCamelCase(name2));
        System.out.println(toCamelCase(name3));
    }
}