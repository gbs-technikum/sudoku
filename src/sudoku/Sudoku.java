package sudoku;

public class Sudoku {
    
    public static final Integer LENGTH = 9;
    private Number[][] numbers;
    
    public Sudoku(){        
        init();
    }
    
    private void init(){
        numbers = new Number[LENGTH][LENGTH];
        for(int l=0;l<LENGTH;l++){
            for(int c=0;c<LENGTH;c++){
                numbers[l][c] = new Number();
            }
        }
    }
    
    public Number getNumber(int line, int column){
        return numbers[line][column];
    }

    public Number[][] getNumbers() {
        return numbers;
    }
    
}
