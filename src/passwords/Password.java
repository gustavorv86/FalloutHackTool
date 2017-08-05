
package passwords;

import java.util.ArrayList;

public class Password {
    
    public String keyword;
    public char[] characters;
    public ArrayList<Integer> correctChars;
    
    public Password(String string){
        this.keyword = string;
        this.characters = string.toCharArray();
        this.correctChars = new ArrayList<>();
    }
    
    public int countCorrectChars(Password otherKey) {
        int size = characters.length;
        int count = 0;
        for(int i=0 ; i<size ; i++) {
            if(this.characters[i] == otherKey.characters[i]){
                count++;
            }
        }
        return count;
    }
    
    public void addCorrectCharsCount(int integer){
        if(correctChars.isEmpty()) {
            correctChars.add(integer);
        } else if(!correctChars.contains(integer)){
            for(int i=0 ; i<correctChars.size() ; i++) {
                if(correctChars.get(i) > integer) {
                    correctChars.add(i, integer);
                    return;
                }
            }
            correctChars.add(integer);
        }
    }
    
    public String correctCharsToString() {
        String string = "";
        for(Integer i : correctChars) {
            string += ","+i;
        }
        if(!string.isEmpty()) {
            string = "["+string.substring(1)+"]";
        }
        return string;
    }
    
    @Override
    public String toString() {
        return this.keyword+" "+this.correctCharsToString();
    }
}
