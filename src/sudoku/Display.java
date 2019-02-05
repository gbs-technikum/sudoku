package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static sudoku.Sudoku.LENGTH;

public class Display {
    private final JFrame frame;
    private final JPanel panel;
    private final JPanel field;
    private final JPanel control;
    private final JButton buttonSolution;
    private final BoxElement[][] boxElements;
    
    public Display(SudokuController controller){
        frame = new JFrame();
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        panel = new JPanel();
        field = new JPanel();
        control = new JPanel();
        buttonSolution = new JButton("Find Solution");
        control.add(buttonSolution);
        field.setLayout(new GridLayout(LENGTH,LENGTH));
        field.setPreferredSize(new Dimension(500, 500));
        
        control.setPreferredSize(new Dimension(500, 100));
        boxElements = new BoxElement[LENGTH][LENGTH];
        for(int line=0;line<LENGTH;line++){
            for(int column=0;column<LENGTH;column++){
                boxElements[line][column] = new BoxElement(controller, line, column);
                field.add(boxElements[line][column]);
            }
        }
        
        
        buttonSolution.addActionListener((l) -> {
            controller.lockNumbersWithValues();
            for(int line=0;line<LENGTH;line++){
                for(int column=0;column<LENGTH;column++){
                    if(!controller.getNumber(line, column).isEditable())
                        boxElements[line][column].setRed();
                }
            }
            controller.initFindingSolution();
            controller.findSolution();
            for(int line=0;line<LENGTH;line++){
                for(int column=0;column<LENGTH;column++){
                    boxElements[line][column].setNumber();
                }
            }
        });
        
        panel.add(field, BorderLayout.NORTH);
        panel.add(control, BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    public BoxElement getBoxElement(int line, int column){
        return boxElements[line][column];
    }
}
