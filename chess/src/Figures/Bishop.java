package Figures;
import Board.Board;

public class Bishop extends Figure {
    public Bishop(char color, Board board) {
        super("B", color, board);
    }

    private boolean way_is_clear(int row, int col, int row1, int col1) {
        if (Math.abs(col1 - col) == Math.abs(row1 - row)) {
            int col_step = (col1 - col)/Math.abs(col1 - col);
            int step = col_step;
            if (row1 - row > 0) {
                for (int r = row + 1; r != row1; r++){
                    if (!this.getBoard().is_null(r, col + step))
                        return false;
                    step += col_step;
                }
            } else {
                for (int r = row - 1; r != row1; r--){
                    if (!this.getBoard().is_null(r, col + step))
                        return false;
                    step += col_step;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (!super.canMove(row, col, row1, col1)) return false;

        if (this.getBoard().is_null(row1, col1)) {
            return way_is_clear(row, col, row1, col1);
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        if (!this.getBoard().is_null(row1, col1)) {
            return way_is_clear(row, col, row1, col1);
        }
        return false;
    }
}
