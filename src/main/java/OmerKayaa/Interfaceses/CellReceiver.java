package OmerKayaa.Interfaceses;

import OmerKayaa.Model.SimpleCell;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface CellReceiver<E extends SimpleCell>
{
    @NonNull
    E receiver ( int x, int y );
}
