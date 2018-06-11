package OmerKayaa;

import OmerKayaa.Model.Containers.Container;
import OmerKayaa.Model.Containers.Row;
import OmerKayaa.Model.SimpleCell;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class ContainerTester extends TestCase
{
    int[] unSolvedContainer, solvedContainer, oneMissingElement;
    Container ExperimentOnUnsolved, ExperimentOnSolved, ExperimentOnOneMissing;

    @Before
    public void setUp ()
    {
        unSolvedContainer = new int[]   {5, 3, 0, 0, 7, 0, 0, 0, 0};
        oneMissingElement = new int[]   {5, 3, 4, 6, 7, 8, 9, 1, 0};
        solvedContainer = new int[]     {5, 3, 4, 6, 7, 8, 9, 1, 2};

        SimpleCell[] solvedCells = new SimpleCell[9];

        for ( int i = 0; i < 9; i++ )
        {
            solvedCells[i] = new SimpleCell(solvedContainer[i],i,0,(type, number) -> ExperimentOnSolved);
        }

        ExperimentOnSolved = new Row((x, y) -> solvedCells[x],0);

        SimpleCell[] unSolvedCells = new SimpleCell[9];

        for ( int i = 0; i < 9; i++ )
        {
            unSolvedCells[i] = new SimpleCell(unSolvedContainer[i],i,0, (type, number) -> ExperimentOnUnsolved);
        }

        ExperimentOnUnsolved = new Row((x, y) -> unSolvedCells[x],0);

        SimpleCell[] oneMissingCells = new SimpleCell[9];

        for ( int i = 0; i < 9; i++ )
        {
            oneMissingCells[i] = new SimpleCell(oneMissingElement[i],i,0 , (type, number)
                    -> ExperimentOnOneMissing );
        }

        ExperimentOnOneMissing = new Row((x, y) -> oneMissingCells[x],0);
    }

    @Test
    public void testErasingShouldWork ()
    {
        Container container = ExperimentOnOneMissing;
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
                              }
        );
        container.checkForOneMissingValueSolution();
        for ( int i = 0; i < 9; i++ )
        {
            assertEquals(container.getCells(i).getValue(), solvedContainer[i]);
        }
    }

    @Test
    public void testShouldFindOnePossibleSolution()
    {
        assertEquals(ExperimentOnOneMissing.checkOnePossibleSolution(),511);
    }
}
