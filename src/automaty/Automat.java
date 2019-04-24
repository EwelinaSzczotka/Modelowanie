package automaty;

public class Automat {

    private int[] cells;
    private int[] rule;
    String bc;

    public Automat(int size, int rule, String bc){
        cells = new int[size];
        for(int i = 0; i< cells.length; i++){
            cells[i] = 0;
        }
        cells[cells.length/2] = 1;
        this.bc = bc;
        this.rule = generateRule(rule);
    }

    public int[] generateRule(int rule){

        int[] row = new int[8];
        String s = Integer.toBinaryString(rule);
        char[] ch = s.toCharArray();
        int diff = row.length - ch.length;
        for(int i =0; i<diff; i++){
            row[i] = 0;
        }
        for(int i =0; i<ch.length;i++){
            if(ch[i]=='1') row[i+diff] = 1;
            else row[i+diff]=0;
        }
        return row;
    }

    public int[] nextStep(){

        int[] tempRow = new int[cells.length];

        int prev = 0, next = 0, now = 0;

        for(int i = 0; i< cells.length; i++){



            if(i==0) {
                if (bc.equals("periodyczny")) {
                    prev=cells[cells.length-1];
                    now = cells[i];
                    next = cells[i + 1];
                }
                if (bc.equals("odbijający")){
                    prev=cells[i];
                    now = cells[i];
                    next = cells[i + 1];
                }
                if (bc.equals("stały")){
                    prev=1;
                    now = cells[i];
                    next = cells[i + 1];
                }
            }
            else if(i==cells.length-1){
                if (bc.equals("periodyczny")) {
                    prev = cells[i - 1];
                    now = cells[i];
                    next=cells[0];
                }
                if (bc.equals("odbijający")){
                    prev = cells[i - 1];
                    now = cells[i];
                    next=cells[i];
                }
                if (bc.equals("stały")){
                    prev = cells[i - 1];
                    now = cells[i];
                    next=1;
                }
            }
            else{
                prev = cells[i - 1];
                now = cells[i];
                next = cells[i + 1];
            }
            if(prev == 1 && now == 1 && next == 1) tempRow[i] = rule[0];
            else if (prev == 1 && now == 1 && next == 0) tempRow[i] = rule[1];
            else if (prev == 1&& now == 0&& next == 1) tempRow[i] = rule[2];
            else if (prev == 1 && now == 0 && next == 0) tempRow[i] = rule[3];
            else if (prev == 0 && now == 1 && next == 1) tempRow[i] = rule[4];
            else if (prev == 0 && now == 1&& next == 0) tempRow[i] = rule[5];
            else if (prev == 0 && now == 0 && next == 1) tempRow[i] = rule[6];
            else if (prev == 0 && now == 0&& next == 0) tempRow[i] = rule[7];

        }

        cells = tempRow;
        return cells;
    }

    public int[] firstStep(){
        return cells;
    }

}
