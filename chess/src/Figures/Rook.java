package Figures;
import Board.Board;
public class Rook extends Figure {
    public Rook(char color, Board board) {
        super("R", color, board);
    }

    private boolean way_is_clear(int row, int col, int row1, int col1) {
        if (row == row1 || col == col1) {
            if (row == row1) {
                int step = (col1 > col) ? 1 : -1;
                for (int c = col + step; c != col1; c += step) {
                    if (!this.getBoard().is_null(row, c)) {
                        return false;
                    }
                }
            } else {
                int step = (row1 > row) ? 1 : -1;
                for (int r = row + step; r != row1; r += step) {
                    if (!this.getBoard().is_null(r, col)) {
                        return false;
                    }
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
