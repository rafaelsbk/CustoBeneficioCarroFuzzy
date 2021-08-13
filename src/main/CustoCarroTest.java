package main;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import net.sourceforge.jFuzzyLogic.rule.Rule;

public class CustoCarroTest {

	public static void main(String[] args) {
		
		FIS fis = FIS.load("src/resource/CustoCarroMandani.fcl", true); // MANDANI
		//FIS fis = FIS.load("src/resource/tipperSingleton.fcl", true); // USANDO SINGLETON
		//FIS fis = FIS.load("src/resource/tipperTS.fcl", true); // TAKAGI-SUGENO
			
		//APRESENTA AS VARIÃ�VEIS MODELADAS
        JFuzzyChart.get().chart(fis.getFunctionBlock("tipper"));
		
        //SETA AS ENTRADAS
	    fis.setVariable("valorAquisicao", 3);
	    fis.setVariable("consumoRodoviario", 5); 
	    
	    //AVALIA
	    fis.evaluate();

	    //MOSTRA O GRÃ�FICO DA VARIÃ�VEL DE SAÃ�DA
        Variable custoBeneficio = fis.getFunctionBlock("tipper").getVariable("custoBeneficio");
        JFuzzyChart.get().chart(custoBeneficio, custoBeneficio.getDefuzzifier(), true);

        //PRINTA VARIÃ�VEL DE SAÃ�DA
        System.out.println(custoBeneficio.getValue());
        	if ((custoBeneficio.getValue() >= 0.00) && (custoBeneficio.getValue() <= 10.00))
        		System.out.println("O carro tem um bom custo beneficio");
        	else if ((custoBeneficio.getValue() >= 10.00) && (custoBeneficio.getValue() <= 20.00))
        		System.out.println("O carro tem um custo beneficio medio");
        	else if ((custoBeneficio.getValue() >= 20.00) && (custoBeneficio.getValue() <= 30.00))
        		System.out.println("O carro tem um baixo custo beneficio");
        		
        //MOSTRA CADA REGRA COM O VALOR DE ATIVAÃ‡ÃƒO
        for( Rule r : fis.getFunctionBlock("tipper").getFuzzyRuleBlock("No1").getRules() )
          System.out.println(r);
	}

}
