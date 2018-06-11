package OmerKayaa.Interfaceses;

import OmerKayaa.Model.Containers.ContainerType;
import OmerKayaa.Model.Containers.Container;

public interface ContainerReceiver<E extends Container>
{
    E receiver(ContainerType type , int number);
}
