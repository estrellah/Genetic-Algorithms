package proyecto;

import java.util.ArrayList;
import org.opt4j.core.genotype.IntegerGenotype;
import org.opt4j.core.problem.Decoder;

public class ProyectoDecoder implements Decoder<IntegerGenotype, ArrayList<Integer>> {

    @Override
    public ArrayList<Integer> decode(IntegerGenotype genotype) {
        ArrayList<Integer> phenotype = new ArrayList<>();
        
        //por cada suministrador (0 a 13)
        int[] capacidadUsada = new int[DatosElectricidad.numSuministradores];
        
        //Recorremos el genotipo (cada gen es un cliente)
        for (int i = 0; i < genotype.size(); i++) {
            //Cada posición es un cliente y lo que contiene es el id del suministrador
            if (i >= DatosElectricidad.demandaCliente.length) break;

            int idSuministrador = genotype.get(i);
            int demandaActual = DatosElectricidad.demandaCliente[i];
            
            //Si el gen es -1 (sin asignar) o es un ID inválido (fuera de rango 0-13)
            //Asignamos -1 en el fenotipo.
            if (idSuministrador < 0 || idSuministrador >= DatosElectricidad.numSuministradores) {
                phenotype.add(-1); 
            } else {
                //Comprobar restricción de capacidad (Hard Constraint)
                if (capacidadUsada[idSuministrador] + demandaActual <= DatosElectricidad.capacidadSuministrador[idSuministrador]) {
                    //CUMPLE: Asignamos y actualizamos la capacidad gastada
                    capacidadUsada[idSuministrador] += demandaActual;
                    phenotype.add(idSuministrador);
                } else {
                    //NO CUMPLE (Excede capacidad)-->(-1)
                    phenotype.add(-1);
                }
            }
        }
        
        return phenotype;
    }
}