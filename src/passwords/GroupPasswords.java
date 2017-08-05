
package passwords;

import java.util.ArrayList;

public class GroupPasswords extends ArrayList<Password> {

    public GroupPasswords(ArrayList<String> entry) throws Exception {
        super();
        if(!entry.isEmpty()) {
            int stringLength = entry.get(0).length();
            this.add(new Password(entry.get(0)));
            for(int i=1 ; i<entry.size() ; i++) {
                String word = entry.get(i);
                if(word.length() == stringLength) {
                    this.add(new Password(word));
                } else {
                    throw new Exception("Invalid key length "+word);
                }
            }
            initValues();
            order();
        } else {
            throw new Exception("Keys is empty");
        }
    }
    
    private void initValues() {
        int size = this.size();
        
        Password iKey;
        Password jKey;
        for(int i=0 ; i<size ; i++) {
            for(int j=0 ; j<size ; j++) {
                if(i != j) {
                    iKey = this.get(i);
                    jKey = this.get(j);
                    int similar = iKey.countCorrectChars(jKey);
                    iKey.addCorrectCharsCount(similar);
                }
            }
        }
    }
    
    private void order() {
        int size = this.size();
        Password tmp;
        for(int i=0 ; i<size ; i++) {
            for(int j=(i+1) ; j<size ; j++) {
                if(this.get(i).correctChars.size() < this.get(j).correctChars.size()) {
                    tmp = this.get(i);
                    this.set(i, this.get(j) );
                    this.set(j, tmp);
                }
            }
        }
    }
    
    public ArrayList<String> similar(int indexKey, int similarChars) {
        Password selectedKey = this.get(indexKey);
        ArrayList<String> similarKeys = new ArrayList<>();
        for(Password key : this) {
            if(selectedKey.equals(key)) {
                continue;
            }
            int count = selectedKey.countCorrectChars(key);
            if(count == similarChars) {
                similarKeys.add(key.keyword);
            }
        }
        return similarKeys;
    }

}
