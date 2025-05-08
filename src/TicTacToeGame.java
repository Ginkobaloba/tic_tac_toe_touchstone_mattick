//these are the imports needed for the Tic Tac Toe game
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// instantiating the game class, this is where the game logic is stored
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

        //sets menu bar for the game and window information
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        setSize(400, 400); // Set window size   
        setLayout(new BorderLayout()); // Set layout manager
        setLocationRelativeTo(null); // Center the window on the screen
        JPanel gridPanel = new JPanel(new GridLayout(3, 3)); // Create a 3x3 grid layout for the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 60); // Set font for buttons

        // Create buttons and add them to the grid panel
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
        // Create a label to display the current player's turn
        statusLabel = new JLabel("X's Turn", SwingConstants.CENTER);
        // Set font and border for the status label
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        // Set the background color of the status label
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setOpaque(true); // Make the background color visible
        // Set the layout manager for the main window
        add(statusLabel, BorderLayout.NORTH);
        // Add the grid panel to the center of the window
        add(gridPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // runs when a button is clicked
        // Check if the game is over
        if (gameOver) return;
        // Get the row and column from the action command of the button
        String[] parts = e.getActionCommand().split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        // Check if the button is already clicked
        if (!buttons[row][col].getText().equals("")) return;
        // Make the move on the board and check if it's valid
        boolean validMove = board.makeMove(row, col, currentPlayer);
        if (!validMove) return;
        // Update the button text and disable it
        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setEnabled(false);
        // Check for a win or draw
        if (board.checkWin(currentPlayer)) {
            statusLabel.setText(currentPlayer + " wins!");
            JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
            gameOver = true;
            return;
        }
        // Check for a draw
         // Check if the board is full and no winner        
        if (board.isFull()) {
            statusLabel.setText("Draw - No winner");
            JOptionPane.showMessageDialog(this, "It's a draw!");
            gameOver = true;
            return;
        }
        // Switch to the next player
        // Update the status label to show the next player's turn
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText(currentPlayer + "'s Turn");
    }
}