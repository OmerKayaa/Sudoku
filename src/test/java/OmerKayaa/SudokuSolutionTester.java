package OmerKayaa;

import OmerKayaa.Interfaceses.Converter;
import OmerKayaa.Interfaceses.PointReceiver;
import OmerKayaa.Interfaceses.Solution;
import OmerKayaa.Interfaceses.Traveler;
import OmerKayaa.Model.Containers.ContainerType;
import OmerKayaa.Model.Sudoku;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class SudokuSolutionTester extends TestCase
{
    int[][] unSolvedSudoku, solvedSudoku, oneMissingElement;
    Sudoku ExperimentOnUnsolved, ExperimentOnSolved, ExperimentOnOneMissing;

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

        ExperimentOnUnsolved = new Sudoku((PointReceiver) (x, y) -> unSolvedSudoku[y][x]);
        ExperimentOnSolved = new Sudoku((PointReceiver) (x, y) -> solvedSudoku[y][x]);
        ExperimentOnOneMissing = new Sudoku((PointReceiver) (x, y) -> oneMissingElement[y][x]);
    }

    @Test
    public void testSolution ()
    {
        Solution.findSolution(ExperimentOnUnsolved);

        Traveler.Traveler(new Traveler.voidTraveler()
                          {
                              @Override public void f4 (int j, int i)
                              {
                                  Assert.assertEquals(ExperimentOnUnsolved.getCells(j, i).getValue(),
                                                      solvedSudoku[i][j]);
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
    public void testLocationShouldBeRight ()
    {
        for ( int i = 0; i < 9; i++ )
        {
            for ( int j = 0; j < 9; j++ )
            {
                Assert.assertEquals(ExperimentOnSolved.getCells(j, i).getValue(), solvedSudoku[i][j]);
            }
        }

        System.out.println("Sudoku.getCell works fine");

        for ( int i = 0; i < 9; i++ )
        {
            for ( int j = 0; j < 9; j++ )
            {
                Assert.assertEquals(ExperimentOnSolved.getContainer(ContainerType.Column, i).getCells(j).getValue(),
                                    solvedSudoku[j][i]);
                Assert.assertEquals(ExperimentOnSolved.getContainer(ContainerType.Row, i).getCells(j).getValue(),
                                    solvedSudoku[i][j]);
                Assert.assertEquals(ExperimentOnSolved.getContainer(ContainerType.Square, i).getCells(j).getValue(),
                                    (int) (Converter.ContainerConverter(i, j, (a, b) -> solvedSudoku[b][a])));
            }
        }

        System.out.println("Sudoku.getContainers works fine");
    }

    @Test
    public void testMultiContainerErasingShouldWork ()
    {
        ExperimentOnUnsolved.forEachContainer(container ->
        {
            container.forEachCell(cell ->
            {
                if (cell.getValue() != 0)
                {
                    container.forEachCell(simpleCell ->
                    {
                        simpleCell.erasePossibility(cell.getValue());
                        return false;
                    });
                    container.erasePossibility(cell.getValue());
                }
                return false;
            });
            return container.checkForOneMissingValueSolution();
        });
        System.out.println(ExperimentOnUnsolved.getCells(1, 1).getPossibleValues());
    }

    @Test
    public void testPossibilities ()
    {
        Solution.findSolution(ExperimentOnUnsolved);
        Assert.assertEquals(ExperimentOnUnsolved.getCells(1, 1).getPossibleValues() , 74);
    }

}
