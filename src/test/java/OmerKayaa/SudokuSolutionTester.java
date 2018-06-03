package OmerKayaa;

import OmerKayaa.Interfaceses.Converter;
import OmerKayaa.Interfaceses.Solution;
import OmerKayaa.Model.Containers.Container;
import OmerKayaa.Model.SimpleCell;
import OmerKayaa.Model.Sudoku;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class SudokuSolutionTester extends TestCase
{
    int[][] unSolvedSudoku, solvedSudoku, oneMissingElement;
    Sudoku ExperimentOnUnsolved,ExperimentOnSolved,ExperimentOnOneMissing;

    public void setUp ()
    {
        unSolvedSudoku = new int[][]
                {
                        {5, 3, 0, 0, 7, 0, 0, 0, 0},
                        {6, 0, 0, 1, 9, 5, 0, 0, 0},
                        {0, 9, 8, 0, 0, 0, 0, 6, 0},

                        {8, 0, 0, 0, 6, 0, 0, 0, 3},
                        {4, 0, 0, 8, 0, 3, 0, 0, 1},
                        {7, 0, 0, 0, 2, 0, 0, 0, 6},

                        {0, 6, 0, 0, 0, 0, 2, 8, 0},
                        {0, 0, 0, 4, 1, 9, 0, 0, 5},
                        {0, 0, 0, 0, 8, 0, 0, 7, 9}
                };
        solvedSudoku = new int[][]
                {
                        {5, 3, 4, 6, 7, 8, 9, 1, 2},
                        {6, 7, 2, 1, 9, 5, 3, 4, 8},
                        {1, 9, 8, 3, 4, 2, 5, 6, 7},

                        {8, 5, 9, 7, 6, 1, 4, 2, 3},
                        {4, 2, 6, 8, 5, 3, 7, 9, 1},
                        {7, 1, 3, 9, 2, 4, 8, 5, 6},

                        {9, 6, 1, 5, 3, 7, 2, 8, 4},
                        {2, 8, 7, 4, 1, 9, 6, 3, 5},
                        {3, 4, 5, 2, 8, 6, 1, 7, 9}
                };

        oneMissingElement = new int[][]
                {
                        {5, 3, 4, 6, 7, 8, 9, 1, 0},
                        {6, 0, 2, 1, 9, 5, 3, 4, 8},
                        {1, 9, 8, 3, 4, 2, 5, 6, 7},

                        {8, 5, 9, 7, 6, 1, 4, 2, 3},
                        {4, 2, 6, 8, 5, 3, 7, 9, 1},
                        {0, 1, 3, 9, 2, 4, 8, 5, 6},

                        {9, 6, 1, 5, 3, 7, 2, 8, 4},
                        {2, 8, 7, 4, 1, 9, 6, 3, 5},
                        {3, 4, 5, 2, 8, 6, 1, 7, 9}
                };

        SimpleCell[][] arr = new SimpleCell[9][9];

        Traveller(new voidTraveller()
                  {
                      @Override public void f4 (int j, int i)
                      {
                          arr[i][j] = new SimpleCell(unSolvedSudoku[i][j], j, i);
                      }
                  }
        );

        ExperimentOnUnsolved = new Sudoku((x, y) -> arr[y][x]);

        SimpleCell[][] arr1 = new SimpleCell[9][9];

        Traveller(new voidTraveller()
                  {
                      @Override public void f4 (int j, int i)
                      {
                          arr1[i][j] = new SimpleCell(solvedSudoku[i][j], j, i);
                      }
                  }
        );

        ExperimentOnSolved = new Sudoku((x, y) -> arr1[y][x]);

        SimpleCell[][] arr2 = new SimpleCell[9][9];

        Traveller(new voidTraveller()
                  {
                      @Override public void f4 (int j, int i)
                      {
                          arr2[i][j] = new SimpleCell(oneMissingElement[i][j], j, i);
                      }
                  }
        );

        ExperimentOnOneMissing = new Sudoku((x, y) -> arr2[y][x]);
    }

    @Test
    public void testSolution ()
    {
        Solution.findSolution(ExperimentOnUnsolved);
        Traveller(new voidTraveller()
                  {
                      @Override public void f4 (int j, int i)
                      {
                          Assert.assertEquals(ExperimentOnUnsolved.getCells(j, i).getValue(), solvedSudoku[i][j]);
                      }
                  }
        );
    }

    @Test
    public void testShouldFindSingleMissingValue ()
    {
        Solution.findSolution(ExperimentOnOneMissing);
        Assert.assertEquals(ExperimentOnOneMissing.getCells(8, 0).getValue(), solvedSudoku[0][8]);
        Assert.assertEquals(ExperimentOnOneMissing.getCells(1, 1).getValue(), solvedSudoku[1][1]);
        Assert.assertEquals(ExperimentOnOneMissing.getCells(0, 5).getValue(), solvedSudoku[5][0]);
    }

    @Test
    public void testErasingShouldWork ()
    {
        Container container = ExperimentOnOneMissing.getColumns(0);
        container.forEach(cell ->
                          {
                              if (cell.getValue() != 0)
                              {
                                  container.forEach(simpleCell ->
                                                    {
                                                        simpleCell.erasePossibility(cell.getValue());
                                                        return false;
                                                    });
                                  container.erasePossibility(cell.getValue());
                              }
                              return false;
                          }
        );
        for ( int i = 0; i < 9; i++ )
        {
            assertEquals(container.getCells(i).getValue(), solvedSudoku[i][0]);
        }
    }

    @Test
    public void testLocationShouldBeRight ()
    {
        Sudoku Experiment = new Sudoku((x, y) -> new SimpleCell(solvedSudoku[y][x], x, y));

        for ( int i = 0; i < 9; i++ )
        {
            for ( int j = 0; j < 9; j++ )
            {
                Assert.assertEquals(Experiment.getCells(j, i).getValue(), solvedSudoku[i][j]);
            }
        }

        System.out.println("Sudoku.getCell works fine");

        for ( int i = 0; i < 9; i++ )
        {
            for ( int j = 0; j < 9; j++ )
            {
                Assert.assertEquals(Experiment.getColumns(i).getCells(j).getValue(), solvedSudoku[j][i]);
                Assert.assertEquals(Experiment.getRows(i).getCells(j).getValue(), solvedSudoku[i][j]);
                Assert.assertEquals(Experiment.getSquares(i).getCells(j).getValue(),
                                    (int) (Converter.ContainerConverter(i, j, (a, b) -> solvedSudoku[b][a])));
            }
        }

        System.out.println("Sudoku.getContainers works fine");
    }

    public static void Traveller (Traveller t)
    {
        for ( int i = 0; i < 3; i++ )
        {
            t.f1(i);
            for ( int j = 0; j < 3; j++ )
            {
                t.f2(i * 3 + j);
                for ( int k = 0; k < 3; k++ )
                {
                    t.f3(k, i * 3 + j);
                    for ( int l = 0; l < 3; l++ )
                    {
                        t.f4(k * 3 + l, i * 3 + j);
                    }
                    t.ef3(k, i * 3 + j);
                }
                t.ef2(i * 3 + j);
            }
            t.ef1(i);
        }
    }

    interface Traveller
    {
        void f1 (int i);

        void f2 (int j);

        void f3 (int k, int j);

        void f4 (int l, int i);

        void ef1 (int i);

        void ef2 (int j);

        void ef3 (int k, int j);
    }

    class voidTraveller implements Traveller
    {
        @Override public void f1 (int i) {}

        @Override public void f2 (int j) {}

        @Override public void f3 (int k, int j) {}

        @Override public void f4 (int j, int i) {}

        @Override public void ef1 (int i) {}

        @Override public void ef2 (int j) {}

        @Override public void ef3 (int k, int j) {}
    }

    class printer extends voidTraveller
    {
        final int[][] array;

        printer (int array[][])
        {
            this.array = array;
        }

        @Override public void f4 (int l, int i)
        {
            System.out.print(array[i][l] + " ");
        }

        @Override public void ef3 (int k, int j)
        {
            System.out.print("  ");
        }

        @Override public void ef2 (int j)
        {
            System.out.println();
        }

        @Override public void ef1 (int i)
        {
            System.out.println();
        }
    }
}
