package sudoku;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static sudoku.Sudoku.LENGTH;

public class Display {
    private final JFrame frame;
    private final JPanel panel;
    private final BoxElement[][] boxElements;
    
    public Display(SudokuController controller){
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        panel = new JPanel();
        
        panel.setLayout(new GridLayout(LENGTH,LENGTH));
        boxElements = new BoxElement[LENGTH][LENGTH];
        for(int line=0;line<LENGTH;line++){
            for(int column=0;column<LENGTH;column++){
                boxElements[line][column] = new BoxElement(controller, line, column);
                panel.add(boxElements[line][column]);
            }
        }
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    public BoxElement getBoxElement(int line, int column){
        return boxElements[line][column];
    }

    public JPanel getPanel() {
        return panel;
    }
}
