package sudoku;

public class Sudoku {
    
    public static final Integer LENGTH = 9;
    private Integer[][] numbers;
    
    public Sudoku(){        
        init();
    }
    
    private void init(){
        numbers = new Integer[LENGTH][LENGTH];
        for(int l=0;l<LENGTH;l++){
            for(int c=0;c<LENGTH;c++){
                numbers[l][c] = 0;
            }
        }
    }
    
    public Integer getNumber(int line, int column){
        return numbers[line][column];
    }
    
    public void setNumber(int line, int column, int value){
        numbers[line][column] = value;
    }

    public Integer[][] getNumbers() {
        return numbers;
    }
    
}
