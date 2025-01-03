package Figures;
import Board.Board;

public class Pawn extends Figure {
    private  boolean isFirstStep = true;
    public Pawn(char color, Board board) {
        super("p", color, board);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (!super.canMove(row, col, row1, col1)) return false;

        if (this.getBoard().is_null(row1, col1)) {
            if (this.isFirstStep) {
                if ((((((row + 2 == row1) && this.getBoard().is_null(row + 1, col)) ||
                        (row + 1 == row1)) && this.getColor() == 'w') ||
                        ((((row - 2 == row1) && this.getBoard().is_null(row - 1, col)) ||
                                (row - 1 == row1)) && this.getColor() == 'b')) &&
                        (col == col1)) {
                    this.isFirstStep = false;
                    return true;
                }
            }
        } else {
            return (((row + 1 == row1)) && this.getColor() == 'w') ||
                    (((row - 1 == row1)) && this.getColor() == 'b') &&
                            (col == col1);
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        return (super.canAttack(row, col, row1, col1) &&
                (row - row1 == -1 && this.getColor()=='w')
                || (row - row1 == 1 && this.getColor()=='b')
                && (Math.abs(col - col1) == 1) && !this.getBoard().is_null(row1, col1));
    }
}
