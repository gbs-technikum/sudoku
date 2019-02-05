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
        value = 7;
        findSolution();
        System.out.println(checkAll());
    }
    
    private boolean checkLine(int line){
        Integer numbers[][] = sudoku.getNumbers();
        for(int counter1=0;counter1<LENGTH;counter1++){
            for(int counter2=counter1+1;counter2<LENGTH;counter2++){
                if(numbers[line][counter1] != 0 && Objects.equals(numbers[line][counter2], numbers[line][counter1])){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean checkColumn(int column){
        Integer numbers[][] = sudoku.getNumbers();
        for(int counter1=0;counter1<LENGTH;counter1++){
            for(int counter2=counter1+1;counter2<LENGTH;counter2++){
                if(numbers[counter1][column] != 0 && Objects.equals(numbers[counter2][column], numbers[counter1][column])){
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean checkBox(int box){
        Integer numbers[][] = sudoku.getNumbers();
        Integer boxNumbers[] = new Integer[LENGTH];
        
        boxNumbers[0] = numbers[(box/3)*3+0][(box%3)*3+0];
        boxNumbers[1] = numbers[(box/3)*3+0][(box%3)*3+1];
        boxNumbers[2] = numbers[(box/3)*3+0][(box%3)*3+2];
        boxNumbers[3] = numbers[(box/3)*3+1][(box%3)*3+0];
        boxNumbers[4] = numbers[(box/3)*3+1][(box%3)*3+1];
        boxNumbers[5] = numbers[(box/3)*3+1][(box%3)*3+2];
        boxNumbers[6] = numbers[(box/3)*3+2][(box%3)*3+0];
        boxNumbers[7] = numbers[(box/3)*3+2][(box%3)*3+1];
        boxNumbers[8] = numbers[(box/3)*3+2][(box%3)*3+2];
        
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
                if(sudoku.getNumber(l, c)==0){
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
        Integer number = sudoku.getNumber(line, column)+1;
        if(number==10)
            sudoku.setNumber(line,column,0);
        else
            sudoku.setNumber(line,column,number);
    }
    
    public void decreaseNumber(int line, int column){
        Integer number = sudoku.getNumber(line, column)-1;
        System.out.println(number);
        if(number==-1)
            sudoku.setNumber(line,column,9);
        else
            sudoku.setNumber(line,column,number);
    }
    
    public Integer getNumber(int line, int column){
        return sudoku.getNumber(line, column);
    }

    private void findSolution() {
        sudoku.setNumber(line, column, value);
        if(checkAll()){
            
            if(isFinished()){
                return;
            }
            
            stepForeward();
            findSolution();
        } else{
            value++;
            if(value==10){
                sudoku.setNumber(line, column, 0);
                stepBackwards();
                findSolution();
            } else{
                findSolution();
            }
        }
    }
    
    private void stepForeward(){
        column++;
        if(column==LENGTH){
            column=0;
            line++;
        }
        value = 1;
    }
    
    private void stepBackwards(){
        column--;
        if(column==-1){
            column=LENGTH-1;
            line--;
        }
        value = sudoku.getNumber(line, column)+1;
        if(value==10){
            stepBackwards();
        }
    }
}
