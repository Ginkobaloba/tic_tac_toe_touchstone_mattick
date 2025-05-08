import javax.swing.SwingUtilities;

public class Main {    
    public static void main(String[] args) {
        // launches the GUI, this is how the documentation says to do it to make it thread safe
        //Need to research lambda expressions and what they do
        SwingUtilities.invokeLater(() -> {
            TicTacToeGame game = new TicTacToeGame();
            game.setVisible(true);
        });
    }
}
