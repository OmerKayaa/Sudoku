package OmerKayaa.Interfaceses;

import OmerKayaa.Model.Sudoku;

public interface Solution
{
    static Sudoku findSolution (Sudoku sudoku)
    {
        sudoku.forEachContainer(container ->
                                {
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
                                    return false;
                                });
        return sudoku;
    }
}

