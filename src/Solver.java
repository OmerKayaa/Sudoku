import java.util.*;

public class Solver
{
    private int[][] sudoku=new int[9][9];
    private boolean loopworked=true,methodworked=true,solved=false,error=false;
    private RecordPossibilities[][] pointPossib=new RecordPossibilities[9][9];
    private RecordPossibilities[] square=new RecordPossibilities[9];
    private RecordPossibilities[] column=new RecordPossibilities[9];
    private RecordPossibilities[] row=new RecordPossibilities[9];
    private ArrayList<RecordPossibilities> recursPossibilities = new ArrayList<>();
    
    Solver(int[][] sudoku)
        {
        this.sudoku=sudoku;
        while(loopworked)
            {                           //yazdığım methodlardan herangi birisi çalıştığı sürece cevabı buluncaya kadar loop tekrar ediyor
            loopworked=false;           //loop un çalıştığını bu değişken belirliyor                
            searchPossibilities();      //sudoku array indeki noktalar için ihtimal listesi üretiyor
            searchError();              //satır ve sütunlarda aynı iki eleman var mı kontrol ediyor
            if(error)
                return;
        while(methodworked)             //method çalıştığı sürece aynı işlemler devam ediyor
                {                         
                methodworked=false;     //ilk başta method çalışmadığını varsayıyor(method çalışırsa medhodworked true oluyor)
                searchSolution();       //sudoku array indeki noktalardan birinde tek ihtimal varsa o ihtimali noktaya yazıyor
                }
        methodworked=true;
        while(methodworked)
                {
                methodworked=false;
                searchPossibilities();     
                searchAdvacedSolution();
                }             
            if(solved())
                return;
            }
        
        if(!solved() && !error)
            {
            searchPossibilities();
            recurs();
            if(solved()&&!error)
                {
                solved=true;
                error=false;
                }
            }
        }
    
    private void recurs()
        {
        for(int p=2;p<9;p++)
            {
            for(int y=0;y<9;y++)
                {
                for(int x=0;x<9;x++)
                    {
                    if(sudoku[y][x]==0 && pointPossib[y][x].possibility.size()==p)
                        {
                        recursPossibilities.add(pointPossib[y][x]);
                        }
                    }
                }
            }
        for(int i=0;i<recursPossibilities.size();i++)
            {
            for(int j=0;j<recursPossibilities.get(i).possibility.size();j++)
                {
                int[][] sudoku=copyOfSudokuArray();
                
                sudoku[recursPossibilities.get(i).y][recursPossibilities.get(i).x]
                        =recursPossibilities.get(i).possibility.get(j);
                
                Solver solution=new Solver(sudoku);
                
                    if(!solution.error && solution.solved)
                        {
                        this.sudoku= solution.sudoku;
                        solved=true;
                        error=false;
                        return;
                        }
                }
            }
        }
    
    private void searchAdvacedSolution()
      {
        for(int i=0;i<9;i++)
            {
            for(int j=0;j<square[i].possibility.size();j++)
                {
                int possbilitycounter=0;
                int xloc=0,yloc=0;
                
                for(int y=0;y<3;y++)
                    {
                    for(int x=0;x<3;x++)
                        {
                        if(sudoku[square[i].y+y][square[i].x+x]==0)
                        for(int k=0;k<pointPossib[square[i].y+y][square[i].x+x].possibility.size();k++)
                            {
                            if(pointPossib[square[i].y+y][square[i].x+x].possibility.get(k)
                                ==square[i].possibility.get(j))
                                {
                                possbilitycounter++;
                                yloc=square[i].y+y;
                                xloc=square[i].x+x;
                                }
                            }
                        }
                    }
                if(possbilitycounter==1)
                    {
                    worked();
                    sudoku[yloc][xloc]=square[i].possibility.get(j);
                    changePossibility(yloc, xloc, square[i].possibility.get(j));
                    }
                }
            
            
            
            
            }
      }
    
    private void searchSolution()
        {
        for(int y=0;y<9;y++)
            {
            for(int x=0;x<9;x++)
                {
                if(sudoku[y][x]==0)
                    {
                        if(pointPossib[y][x].possibility.size()==1)
                            {
                            worked();
                            sudoku[y][x]=pointPossib[y][x].possibility.get(0);
                            changePossibility(y, x,pointPossib[y][x].possibility.get(0));
                            }

                    else if(pointPossib[y][x].possibility.size()==0)
                        {
                        error=true;
                        solved=false;
                        return;
                        }
                    }
                }
            }
        }
    
    private void changePossibility(int y,int x,int change)
        {
        pointPossib[y][x]=null;
        for(int i=0;i<9;i++)
            {
            if(sudoku[y][i]==0)
            for(int j=0;j<pointPossib[y][i].possibility.size();j++)
                {
                if(pointPossib[y][i].possibility.get(j)==change)
                    {
                    pointPossib[y][i].possibility.remove(j);
                    break;
                    }
                }
            if(sudoku[i][x]==0)
            for(int j=0;j<pointPossib[i][x].possibility.size();j++)
                {
                if(pointPossib[i][x].possibility.get(j)==change)
                    {
                    pointPossib[i][x].possibility.remove(j);
                    break;
                    }
                }
            }
        for(int sy=0;sy<3;sy++)
            {
            for(int sx=0;sx<3;sx++)
                {
                if(sudoku[(y/3)*3+sy][(x/3)*3+sx]==0)
                for(int i=0;i<pointPossib[(y/3)*3+sy][(x/3)*3+sx].possibility.size();i++)
                    {
                    if(pointPossib[(y/3)*3+sy][(x/3)*3+sx].possibility.get(i)==change)
                        {
                        pointPossib[(y/3)*3+sy][(x/3)*3+sx].possibility.remove(i);
                        break;
                        }
                    }
                }
            }
        for(int i=0;i<square[getPointSquare(y,x)].possibility.size();i++)
            {
            if(square[getPointSquare(y,x)].possibility.get(i)==change)
                {
                square[getPointSquare(y,x)].possibility.remove(i);
                break;
                }
            }
        for(int i=0;i<column[x].possibility.size();i++)
            {
            if(column[x].possibility.get(i)==change)
                {
                column[x].possibility.remove(i);
                break;
                }
            }
        for(int i=0;i<row[x].possibility.size();i++)
            {
            if(row[x].possibility.get(i)==change)
                {
                row[x].possibility.remove(i);
                break;
                }
            }
        }
    
    private void searchPossibilities()
        {
        for(int i=0;i<9;i++)
            {
            square[i] = new RecordPossibilities();
            square[i].possibility=makeAarraylistforPos();
            square[i].x=(i%3)*3;
            square[i].y=(i/3)*3;
            column[i] = new RecordPossibilities();
            column[i].possibility=makeAarraylistforPos();
            column[i].x=i;
            row[i] = new RecordPossibilities();
            row[i].possibility=makeAarraylistforPos();
            row[i].y=i;
            
            int[] row=getRow(i);
            int[] column=getColumn(i);
            int[] square=getSquareAsArray(i);
            
            for(int j=0;j<9;j++)
                {
                if(square[j]!=0)
                    {
                    this.square[i].possibility.remove(square[j]-1);
                    this.square[i].possibility.add(square[j]-1, 0);      
                    }
                if(column[j]!=0)
                    {
                    this.column[i].possibility.remove(column[j]-1);
                    this.column[i].possibility.add(column[j]-1, 0);
                    }
                if(row[j]!=0)
                    {
                    this.row[i].possibility.remove(row[j]-1);
                    this.row[i].possibility.add(row[j]-1, 0);
                    }
                }
            } 

        for(int y=0;y<9;y++)
            {
            for(int x=0;x<9;x++)
                {
                if(sudoku[y][x]==0)
                    {
                    pointPossib[y][x]=new RecordPossibilities();
                    pointPossib[y][x].possibility=makeAarraylistforPos();
                    
                    for(int i=0;i<9;i++)
                        {
                        if(square[getPointSquare(y,x)].possibility.get(i)==0)
                            {
                            pointPossib[y][x].possibility.remove(i);
                            pointPossib[y][x].possibility.add(i,0);
                            }
                        if(column[x].possibility.get(i)==0)
                            {
                            pointPossib[y][x].possibility.remove(i);
                            pointPossib[y][x].possibility.add(i,0);
                            }
                        if(row[y].possibility.get(i)==0)
                            {
                            pointPossib[y][x].possibility.remove(i);
                            pointPossib[y][x].possibility.add(i,0);
                            }
                        }
                    pointPossib[y][x].x=x;
                    pointPossib[y][x].y=y;
                    }
                }
            }
        for(int k=0;k<9;k++)
            {
            for(int i=0,j=9;i<j;i++)
                {
                if(square[k].possibility.get(i)==0)
                    {
                    square[k].possibility.remove(i);
                    i--; j--;
                    }
                }
            for(int i=0,j=9;i<j;i++)
                {
                if(column[k].possibility.get(i)==0)
                    {
                    column[k].possibility.remove(i);
                    i--; j--;
                    }
                }
            for(int i=0,j=9;i<j;i++)
                {
                if(row[k].possibility.get(i)==0)
                    {
                    row[k].possibility.remove(i);
                    i--; j--;
                    }
                }
            for(int l=0;l<9;l++)
                {
                for(int i=0,j=9;i<j;i++)
                    {
                    if(sudoku[k][l]==0 && pointPossib[k][l].possibility.get(i)==0)
                        {
                        pointPossib[k][l].possibility.remove(i);
                        i--; j--;
                        }
                    }
                }
            }
        }
    
    private boolean searchError()
        {
        int[][] check=new int[3][9];

        for(int i=0;i<9;i++)
            {
            check[0]=getColumn(i);
            check[1]=getRow(i);
            check[2]=getSquareAsArray(i);
            for(int k=1;k<=9;k++)
                {
                for(int cr=0;cr<3;cr++)
                    {
                    int element=0;
                    for(int j=0;j<9;j++)
                        {
                        if(check[cr][j]==k)
                            {
                            element++;
                            if(element>=2)
                                {
                                this.solved=false;
                                this.error=true;
                                }
                            }
                        }
                    }
                }
            }
        return this.error;
        }
    
    private ArrayList<Integer> makeAarraylistforPos()
        {
        ArrayList<Integer> i=new ArrayList<>(9);
        for(int j=1;j<=9;j++)
            {
            i.add(j);
            }
        return i;
        }
    
    private int[][] getSquareAsSquare(int s)
        {
        int[][] square = new int[3][3];
        
        for(int i=0;i<3;i++)
            {
            for(int j=0;j<3;j++)
                {
                square[i][j]=sudoku[((s/3)*3)+i][((s%3)*3)+j];
                }
            }
        
        return square;
        }
    
    private int[] getSquareAsArray(int s)
        {
        int[] square = new int[9];
        
        for(int i=0;i<3;i++)
            {
            for(int j=0;j<3;j++)
                {
                square[(i*3)+j]=sudoku[((s/3)*3)+i][((s%3)*3)+j];
                }
            }
        
        return square;
        }
    
    private int[] getSquareAsArray(int y,int x)
        {
        int s=getPointSquare(y, x);
        int[] square = new int[9];
        
        for(int i=0;i<3;i++)
            {
            for(int j=0;j<3;j++)
                {
                square[(i*3)+j]=sudoku[((s/3)*3)+i][((s%3)*3)+j];
                }
            }
        
        return square;
        }
    
    private int getPointSquare(int y,int x)
        {
        int squarenumber;
        squarenumber=((y/3)*3)+(x/3);
        return squarenumber;
        }
    
    private int[] getRow(int y)
        {
        int[] row=new int[9];
        for(int i=0;i<9;i++)
            {
            row[i]=sudoku[y][i];
            }
        return row;
        }
    
    private int[] getColumn(int x)
        {
            int[] column=new int[9];
            for(int i=0;i<9;i++)
                {
                column[i]=sudoku[i][x];
                }
            return column;
        }
    
    public boolean getSolved()
        {
        return solved;
        }

    public boolean getError()
        {
        return error;
        }
    
    private void worked()
      {
        loopworked=true;
        methodworked=true;
      }
    
    private boolean solved()
      {
        if(!searchError())
            {
            for(int y=0;y<9;y++)
                {
                for(int x=0;x<9;x++)
                    {
                    if(sudoku[y][x]==0)
                        {
                        solved = false;
                        break;
                        }
                    else solved = true;
                    }
                if(!solved)
                    break;
                }
            this.solved=solved;
            return solved;
            }
        else
            {
            this.solved=false;
            return false;
            }
      }
    
    private int[][] copyOfSudokuArray()
        {
            int[][] copy=new int[9][9];
            for(int y=0;y<9;y++)
                    {
                    for(int x=0;x<9;x++)
                        {
                        copy[y][x]=this.sudoku[y][x];
                        }
                    }
            return copy;
        }
    
    public int[][] getSudokuSolution()
        {
        return copyOfSudokuArray();
        }
    
    private class RecordPossibilities
        {
        int y,x;
        ArrayList<Integer> possibility=new ArrayList<Integer>();
        }

    @Override
    public String toString()
        {
        String str="";
        for(int y=0;y<9;y++)
            {
            for(int x=0;x<9;x++)
                {
                str=(x%3==0)? str+"  ": str+" ";
                str+=String.valueOf(sudoku[y][x]);
                }
            str=(y%3==2)? str+"\n\n" : str+"\n";
            }
        return str;
        }
}