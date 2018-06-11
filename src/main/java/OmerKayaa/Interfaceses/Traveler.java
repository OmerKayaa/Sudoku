package OmerKayaa.Interfaceses;

import OmerKayaa.Model.Sudoku;

public interface Traveler
{
    interface Functions
    {
        void f1 (int i);

        void f2 (int j);

        void f3 (int k, int j);

        void f4 (int l, int i);
    }


    static void Traveler (Functions f)
    {
        for ( int i = 0; i < 3; i++ )
        {
            for ( int j = 0; j < 3; j++ )
            {
                for ( int k = 0; k < 3; k++ )
                {
                    for ( int l = 0; l < 3; l++ )
                    {
                        f.f4(k * 3 + l, i * 3 + j);
                    }
                    f.f3(k, i * 3 + j);
                }
                f.f2(i * 3 + j);
            }
            f.f1(i);
        }
    }

    class voidTraveler implements Functions
    {
        @Override public void f1 (int i){}

        @Override public void f2 (int j){}

        @Override public void f3 (int k, int j){}

        @Override public void f4 (int l, int i){}
    }

    class ArrayPrinter implements Functions
    {
        final int[][] array;

        ArrayPrinter (int array[][])
        {
            this.array = array;
        }

        @Override public void f4 (int l, int i)
        {
            System.out.print(array[i][l] + " ");
        }

        @Override public void f3 (int k, int j)
        {
            System.out.print("  ");
        }

        @Override public void f2 (int j)
        {
            System.out.println();
        }

        @Override public void f1 (int i)
        {
            System.out.println();
        }
    }

    class SudokuPrinter implements Functions
    {
        final Sudoku sudoku;

        SudokuPrinter (Sudoku s) {sudoku = s; }

        @Override public void f4 (int l, int i)
        {
            System.out.print(sudoku.getCells(l, i).getValue() + " ");
        }

        @Override public void f3 (int k, int j)
        {
            System.out.print("  ");
        }

        @Override public void f2 (int j)
        {
            System.out.println();
        }

        @Override public void f1 (int i)
        {
            System.out.println();
        }
    }

}
