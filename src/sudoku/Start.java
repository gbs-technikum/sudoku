package sudoku;

public class Start {
    
    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        SudokuController controller = new SudokuController(sudoku);
        Display display = new Display(controller);
    }
}
