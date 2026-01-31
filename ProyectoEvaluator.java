package proyecto;

import java.util.ArrayList;
import org.opt4j.core.Objectives;
import org.opt4j.core.Objective.Sign;
import org.opt4j.core.problem.Evaluator;

public class ProyectoEvaluator implements Evaluator<ArrayList<Integer>>
{
	@Override
    public Objectives evaluate(ArrayList<Integer> phenotype) {
        
        double beneficioTotal = 0.0;
        int numRenovablesUsados = 0;
        
        for (int i = 0; i < phenotype.size(); i++) {
            int idSuministrador = phenotype.get(i);
            
            // Si el cliente tiene asignado un suministrador (no es -1)
            if (idSuministrador != -1) {
                // 1. Sumar Beneficio
                beneficioTotal += DatosElectricidad.matrizBeneficio[idSuministrador][i];
                
                // 2. Contar si es renovable
                if (DatosElectricidad.esRenovable(idSuministrador)) {
                    numRenovablesUsados++;
                }
            }
        }
        
        Objectives objectives = new Objectives();
        // Objetivo 1: Maximizar Beneficio Económico
        objectives.add("Beneficio Total", Sign.MAX, beneficioTotal);
        
        // Objetivo 2: Maximizar Número de asignaciones a Renovables
        objectives.add("Uso Renovables", Sign.MAX, numRenovablesUsados);
        
        return objectives;
    }
}
