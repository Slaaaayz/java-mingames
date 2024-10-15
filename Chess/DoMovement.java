package Chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;


public class DoMovement implements ActionListener{
    private int x;
    private int y;
    private int newx;
    private int newy;
    private GridChess Grid;
    public DoMovement(int x,int y ,int newx,int newy,GridChess BackgroundGrid) {
        this.x = x;
        this.y = y;
        this.newx = newx;
        this.newy = newy;
        this.Grid = BackgroundGrid;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(this.Grid.PiecesGrid[newx][newy].GetPiece()=="King"){
            if (this.Grid.PiecesGrid[x][y].GetColor() == "White"){
                YouLoose("Noir");
            }else {
                YouLoose("Blanc");
            }

        }
        this.Grid.PiecesGrid[newx][newy].SetMoved(true);
        this.Grid.removePieceImage(Grid.PiecesGrid[x][y].Panel);
        this.Grid.removePieceImage(Grid.PiecesGrid[newx][newy].Panel);
        if (this.Grid.PiecesGrid[x][y].GetColor() == "White"){
            this.Grid.setPieceImage("./Chess/Image/WhitePieces1/"+this.Grid.PiecesGrid[x][y].GetPiece()+".png", this.Grid.PiecesGrid[newx][newy].Panel);
        }else {
            this.Grid.setPieceImage("./Chess/Image/BlackPieces1/"+this.Grid.PiecesGrid[x][y].GetPiece()+".png", this.Grid.PiecesGrid[newx][newy].Panel);
        }
        this.Grid.PiecesGrid[newx][newy].SetPiece(this.Grid.PiecesGrid[x][y].GetPiece());
        this.Grid.PiecesGrid[newx][newy].SetColor(this.Grid.PiecesGrid[x][y].GetColor());
        this.Grid.PiecesGrid[x][y].SetPiece("");
        this.Grid.PiecesGrid[x][y].SetColor("");
        
        boolean RowColor = true;
        for(int i = 0; i < 8; i++) {
            RowColor = !RowColor;
            for(int j = 0; j < 8; j++) {
                RowColor = !RowColor;
                if (RowColor) {
                    Grid.BackgroundGrid[i][j].setBackground(new Color(238,238,210));
                } else {
                    Grid.BackgroundGrid[i][j].setBackground(new Color(118,130,86));
                }
                Grid.BackgroundGrid[i][j].setBounds(j * 100, i * 100, 100, 100);
                for(ActionListener al : this.Grid.PiecesGrid[i][j].Panel.getActionListeners()) {
                    this.Grid.PiecesGrid[i][j].Panel.removeActionListener(al);
                }
                Grid.PiecesGrid[i][j].Panel.addActionListener(new ShowMovement(i,j,Grid));
            }
        }
    }
    private void YouLoose(String qui) {
        this.Grid.getContentPane().removeAll();
        this.Grid.getContentPane().setBackground(new Color(255,127,127));
        
        JLabel gameOverLabel = new JLabel("L equipe "+qui+" a perdu");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.Grid.add(gameOverLabel, BorderLayout.CENTER);
        
        this.Grid.revalidate(); 
        this.Grid.repaint();
    }
}
