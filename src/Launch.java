import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Launch
{
    private static final JPanel panelCenter= new JPanel(new GridLayout(3, 3, 10, 10));
    private static final JTextField[][] SudokuText=new JTextField[9][9];
    private static final ArrayList<Record> record =new ArrayList<>();
    private static final JButton Solve = new JButton("Solve");
    private static final JButton Reset = new JButton("Reset");
    private static int[][] sudokusolution=new int[9][9];
    private static int[][] sudokuArray=new int[9][9];
    private static final Font font=new Font("",1,28);
    private static int state=0;
    
    public static void main(String[] args)
      {
        JFrame f=new sudokuframe();
        f.setVisible(true);
        f.setTitle("Sudoku Solver");
        f.setSize(500, 600);
        f.setLocationRelativeTo(null);
        f.setMinimumSize(new Dimension(300, 400));
        f.setMaximumSize(new Dimension(700,800));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
    
    private static void SetTextSolution()
      {
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
              {
                SudokuText[covertXYtoBoxCordinate(new Dimension(i, j)).width]
                        [covertXYtoBoxCordinate(new Dimension(i, j)).height]
                        .setText(String.valueOf(sudokusolution[i][j]));
                SudokuText[covertXYtoBoxCordinate(new Dimension(i, j)).width]
                        [covertXYtoBoxCordinate(new Dimension(i, j)).height]
                        .setEditable(false);
                SudokuText[covertXYtoBoxCordinate(new Dimension(i, j)).width]
                        [covertXYtoBoxCordinate(new Dimension(i, j)).height]
                        .setEnabled(false);
              }
      }
    
    public static Dimension covertXYtoBoxCordinate(Dimension d)
      {
        Dimension dreturn=new Dimension();
        dreturn.width=((d.height/3)*3)+(d.width/3);
        dreturn.height=((d.height%3)*3)+(d.width%3);
        return dreturn;
      }
    
    private static void Reset()
      {
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
              {
                SudokuText[i][j].setText("");
                SudokuText[i][j].setEditable(true);
                SudokuText[i][j].setEnabled(true);
                sudokuArray=new int[9][9];
                record.clear();
                state=0;
              }
      }

    public static class sudokuframe extends JFrame
      {
        public sudokuframe()
          {
            KeyStroke Z=KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK);
            setLayout(new BorderLayout());
            JPanel panelSouth =new JPanel();
              {
                panelSouth.setBorder(new LineBorder(Color.BLACK));
                Reset.setFont(font);
                Solve.setFont(font);
                panelSouth.add(Reset);
                panelSouth.add(Solve);
              }
            //Reset key Method
              {
                Reset.addActionListener((ActionEvent e) -> Reset());
              }
            //Solve Key Method
              {
                Solve.addActionListener((ActionEvent e) ->
                  {
                    Solver Solution=new Solver(sudokuArray);
                    if(Solution.getSolved())
                      {
                        sudokusolution=Solution.getSudokuSolution();
                        SetTextSolution();
                      }
                    else
                      {
                        Reset();
                        JOptionPane.showMessageDialog(null, "Invalid sudoku input", "Warning", JOptionPane.ERROR_MESSAGE);
                      }
                  });
              }
            //CenterPanel
              {
                ActionListener Zlisener =(ActionEvent e) ->
                  {
                    if(state<1);
                    else
                    record.get(state-1).Undo();
                  };
                panelCenter.registerKeyboardAction(Zlisener, Z, JPanel.WHEN_IN_FOCUSED_WINDOW);
                GridLayout[] inner=new GridLayout[9];
                JPanel[] innerpanel=new JPanel[9];
                for(int i=0;i<9;i++)
                  {
                    innerpanel[i]=new JPanel();
                    inner[i]=new GridLayout(3, 3);
                    innerpanel[i].setLayout(inner[i]);
                    for(int j=0;j<9;j++)
                      {
                        SudokuText[i][j]=new Textfieldmaker(i, j);
                        innerpanel[i].add(SudokuText[i][j]);
                      }
                    panelCenter.add(innerpanel[i]);
                  }
              }
            panelCenter.setBorder(new LineBorder(Color.BLACK));
            add(panelCenter,BorderLayout.CENTER);
            add(panelSouth,BorderLayout.SOUTH);
          } 
      }
    private static class Textfieldmaker extends JTextField
      {
        int x, y;
        boolean error=false;
        Textfieldmaker(int i,int j)
          {
            setCordinate(i, j);
            setToolTipText("point "+(x+1)+"x"+(y+1));
            setFont(font);
            addKeyListener(new KeyAdapter()
              {
                @Override
                public void keyReleased(KeyEvent e)
                  {
                    try
                      {
                        if((int) e.getKeyChar()<48 || (int) e.getKeyChar()>57||getText().length()>1)
                            throw new Exception();
                        sudokuArray[x][y]=Integer.valueOf(SudokuText[i][j].getText());
                        record.add(new Record(new Dimension(j, i), getText()));
                        setEditable(false);
                        setEnabled(false);
                      } 
                        catch (Exception ex)
                      {
                        setText("");
                        //JOptionPane.showMessageDialog(null, "Invalid input only one digit number allowed",
                        //        "Warning", JOptionPane.WARNING_MESSAGE); 
                      }
                    }
              });
          }
        private void setCordinate(int i, int j)
          {
            this.x=((i%3)*3)+((j%3));
            this.y=((i/3)*3)+(j/3);
          }
      }
    private static class Record extends Dimension
      {
        String Change="";
        private Record(Dimension d,String change)
          {
            this.height=d.height;
            this.width=d.width;
            this.Change=change;
            state++;
          }
        private void Undo()
          {
            sudokuArray[((height%3)*3)+((width%3))][((height/3)*3)+(width/3)]=0;
            SudokuText[height][width].setEditable(true);
            SudokuText[height][width].setEnabled(true);
            SudokuText[height][width].setText("");
            record.remove(--state);
          }
      }
}