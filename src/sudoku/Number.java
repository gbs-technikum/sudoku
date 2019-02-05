package sudoku;

public class Number {
    private Integer value;
    private boolean editable;
    
    public Number(){
        value = 0;
        editable = true;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
