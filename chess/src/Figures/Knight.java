package Figures;
import Board.Board;

public class Knight extends Figure {
    public Knight(char color, Board board) {
        super("k", color, board);
    }

    private boolean way_is_clear(int row, int col, int row1, int col1) {
        return  ((Math.abs(row1 - row) == 2 && Math.abs(col1 - col) == 1) ||
                (Math.abs(row1 - row) == 1 && Math.abs(col1 - col) == 2));
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
