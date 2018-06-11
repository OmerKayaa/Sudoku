package OmerKayaa.Interfaceses;

import OmerKayaa.Model.Sudoku;

public interface Solution
{
    static Sudoku findSolution (Sudoku sudoku)
    {
        do
        {
            Traveler.Traveler(new Traveler.SudokuPrinter(sudoku));
            System.out.println("---------------------");
        }
        while (
                sudoku.forEachContainer(container ->
                                        {
                                            container.forEachCell(cell ->
                                                                  {
                                                                      if (cell.getValue() != 0)
                                                                      {
                                                                          container.forEachCell(simpleCell ->
                                                                                                {
                                                                                                    simpleCell
                                                                                                            .erasePossibility(cell.getValue());
                                                                                                    return false;
                                                                                                });
                                                                          container.erasePossibility(cell.getValue());
                                                                      }
                                                                      return false;
                                                                  }
                                            );

                                            return container.checkForOneMissingValueSolution();
                                        })
                );
        System.out.println("\t\tResult\n---------------------");
        Traveler.Traveler(new Traveler.SudokuPrinter(sudoku));
        return sudoku;
    }
}

