package sudoku;

import java.util.Objects;
import static sudoku.Sudoku.LENGTH;

public class SudokuController {
    
    private static Sudoku sudoku;
    private int line;
    private int column;
    private int value;
    
    public SudokuController(Sudoku sudoku){
        SudokuController.sudoku = sudoku;
        line = 0;
        column = 0;
        value = 1;
        System.out.println(checkAll());
    }
    
    private boolean checkLine(int line){
        Number numbers[][] = sudoku.getNumbers();
        for(int counter1=0;counter1<LENGTH;counter1++){
            for(int counter2=counter1+1;counter2<LENGTH;counter2++){
                if(numbers[line][counter1].getValue() != 0 && Objects.equals(numbers[line][counter2].getValue(), numbers[line][counter1].getValue())){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean checkColumn(int column){
        Number numbers[][] = sudoku.getNumbers();
        for(int counter1=0;counter1<LENGTH;counter1++){
            for(int counter2=counter1+1;counter2<LENGTH;counter2++){
                if(numbers[counter1][column].getValue() != 0 && Objects.equals(numbers[counter2][column].getValue(), numbers[counter1][column].getValue())){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean checkBox(int box){
        Number numbers[][] = sudoku.getNumbers();
        Integer boxNumbers[] = new Integer[LENGTH];
        
        boxNumbers[0] = numbers[(box/3)*3+0][(box%3)*3+0].getValue();
        boxNumbers[1] = numbers[(box/3)*3+0][(box%3)*3+1].getValue();
        boxNumbers[2] = numbers[(box/3)*3+0][(box%3)*3+2].getValue();
        boxNumbers[3] = numbers[(box/3)*3+1][(box%3)*3+0].getValue();
        boxNumbers[4] = numbers[(box/3)*3+1][(box%3)*3+1].getValue();
        boxNumbers[5] = numbers[(box/3)*3+1][(box%3)*3+2].getValue();
        boxNumbers[6] = numbers[(box/3)*3+2][(box%3)*3+0].getValue();
        boxNumbers[7] = numbers[(box/3)*3+2][(box%3)*3+1].getValue();
        boxNumbers[8] = numbers[(box/3)*3+2][(box%3)*3+2].getValue();
        
        for(int counter1=0;counter1<LENGTH;counter1++){
            for(int counter2=counter1+1;counter2<LENGTH;counter2++){
                if(boxNumbers[counter1] != 0 && Objects.equals(boxNumbers[counter2], boxNumbers[counter1])){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isZero(){
        for(int l=0;l<LENGTH;l++){
            for(int c=0;c<LENGTH;c++){
                if(sudoku.getNumber(l, c).getValue()==0){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean checkAll(){
        for(int i=0;i<LENGTH;i++){
            if(!checkLine(i) || !checkColumn(i) || !checkBox(i))
                return false;
        }
        return true;
    }
    
    private boolean isFinished(){
        return checkAll() && !isZero();
    }
    
    public void increaseNumber(int line, int column){
        Integer number = sudoku.getNumber(line, column).getValue()+1;
        if(number==10)
            sudoku.getNumber(line,column).setValue(0);
        else
            sudoku.getNumber(line,column).setValue(number);
    }
    
    public void decreaseNumber(int line, int column){
        Integer number = sudoku.getNumber(line, column).getValue()-1;
        System.out.println(number);
        if(number==-1)
            sudoku.getNumber(line,column).setValue(9);
        else
            sudoku.getNumber(line,column).setValue(number);
    }
    
    public Number getNumber(int line, int column){
        return sudoku.getNumber(line, column);
    }

    public void findSolution() {
        sudoku.getNumber(line,column).setValue(value);
        if(checkAll()){
            
            if(isFinished()){
                return;
            }
            
            stepForeward();
            findSolution();
        } else{
            value++;
            if(value==10){
                sudoku.getNumber(line,column).setValue(0);
                stepBackwards();
                findSolution();
            } else{
                findSolution();
            }
        }
    }

    public void initFindingSolution() {
        line=0;
        column=-1;
        stepForeward();
    }
    
    private void stepForeward(){
        column++;
        if(column==LENGTH){
            column=0;
            line++;
        }
        value = 1;
        if(!sudoku.getNumber(line,column).isEditable())
            stepForeward();
    }
    
    private void stepBackwards(){
        column--;
        if(column==-1){
            column=LENGTH-1;
            line--;
        }
        if(!sudoku.getNumber(line,column).isEditable())
            stepBackwards();
        value = sudoku.getNumber(line,column).getValue()+1;
        if(value==10){
            stepBackwards();
        }
    }
    
    public void lockNumbersWithValues(){
        for(int l=0;l<LENGTH;l++){
            for(int c=0;c<LENGTH;c++){
                if(sudoku.getNumber(l,c).getValue()!=0)
                    sudoku.getNumber(l,c).setEditable(false);
            }
        }
    }
}
