package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BoxElement extends JPanel{
    
    private JLabel label;
    private final SudokuController controller;
    private final int line;
    private final int column;
    
    public BoxElement(SudokuController controller, int line, int column){
        this.controller = controller;
        this.line = line;
        this.column = column;
        init();
    }
    
    private void init(){
        setBorder(BorderFactory.createLineBorder(Color.black));
        label = new JLabel();
        label.setFont(new Font("Courier", Font.BOLD,26));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    controller.increaseNumber(line, column);
                } else if(SwingUtilities.isRightMouseButton(e)){
                    controller.decreaseNumber(line, column);
                }
                setNumber();
            }
        });
        setNumber();
        add(label);
    }
    
    private void setNumber(){
        Integer number = controller.getNumber(line, column);
        if(number!=0){
            label.setText(number+"");
        } else{
            label.setText("");
        }
    }

    public JLabel getLabel() {
        return label;
    }
}
