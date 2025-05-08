import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private JLabel statusLabel;
    private Board board;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToeGame() {
        board = new Board();
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameOver = false;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        Font buttonFont = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(buttonFont);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setActionCommand(i + "," + j);
                gridPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        add(statusLabel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        String[] parts = e.getActionCommand().split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        if (!buttons[row][col].getText().equals("")) return;

        boolean validMove = board.makeMove(row, col, currentPlayer);
        if (!validMove) return;

        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setEnabled(false);

        if (board.checkWin(currentPlayer)) {
            statusLabel.setText(currentPlayer + " wins!");
            JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
            gameOver = true;
            return;
        }

        if (board.isFull()) {
            statusLabel.setText("Draw - No winner");
            JOptionPane.showMessageDialog(this, "It's a draw!");
            gameOver = true;
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText(currentPlayer + "'s Turn");
    }
}