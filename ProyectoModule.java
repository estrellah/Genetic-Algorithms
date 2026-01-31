package proyecto;

import org.opt4j.core.problem.ProblemModule;

public class ProyectoModule extends ProblemModule 
{

	@Override
	protected void config() 
	{
		// mostrara errores mientras ProyectoCreator, ProyectoDecoder y ProyectoEvaluator no esten completos
		bindProblem(ProyectoCreator.class, ProyectoDecoder.class, ProyectoEvaluator.class);
	}

}
