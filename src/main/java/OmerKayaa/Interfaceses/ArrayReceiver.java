package OmerKayaa.Interfaceses;

import OmerKayaa.Model.SimpleCell;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface ArrayReceiver<E extends SimpleCell>
{
	@NonNull
	E cellReceiver ( int x, int y );
}
