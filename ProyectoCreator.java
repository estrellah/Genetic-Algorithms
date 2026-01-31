package proyecto;

import java.util.Random;
import org.opt4j.core.genotype.IntegerGenotype;
import org.opt4j.core.problem.Creator;

public class ProyectoCreator implements Creator<IntegerGenotype>
{
	@Override
    public IntegerGenotype create() {
        //Rango de valores: -1 (Sin suministrador) a 13 (Suministrador 13)
        IntegerGenotype genotype = new IntegerGenotype(-1, DatosElectricidad.numSuministradores - 1);
        
        //Inicializamos con valores aleatorios para los 20 clientes
        genotype.init(new Random(), DatosElectricidad.numClientes);
        
        return genotype;
    }
}
