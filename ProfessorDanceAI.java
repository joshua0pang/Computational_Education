import java.io.*;
import java.awt.image.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jgap.*;
import org.jgap.impl.*;
import org.jgap.audit.*;
import java.util.ArrayList;

public class ProfessorDanceAI {
  /**
   * The total number of times we'll let the population evolve.
   */
  private static final int MAX_ALLOWED_EVOLUTIONS = 80;

  public static void dance(int trail_length)
      throws Exception {
    
    Configuration conf = new DefaultConfiguration();
    
    ProfessorDanceAIEvaluator myFunc =
        new ProfessorDanceAIEvaluator(trail_length);
    conf.setFitnessFunction(myFunc);
   
    //here it interprets the path of the ant so it can be genetically sampled.
    Gene[] sampleGenes = new Gene[trail_length];
    //puts every step into a gene
    for (int i = 0; i < trail_length; i++)
      sampleGenes[i] = new IntegerGene(conf, 0, 3); 
    Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
    conf.setSampleChromosome(sampleChromosome);
    conf.setPopulationSize(4); //keeps easy to visualize.
    
    //random seed
    Genotype population = Genotype.randomInitialGenotype(conf);
    population.evolve();
    //Creates an ArrayList which is the history of all paths.
    ArrayList<Integer[]> trails = myFunc.dance_dump(); 
    for (Integer[] trail : trails) {
      System.out.println();
      for (int i = 0; i < trail.length; i++) 
         System.out.print(trail[i] + ", ");
      System.out.print("//");
    }
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Syntax: ProfessorDanceAI <trail_length>");
    }
    else {
      try {
        int amount = Integer.parseInt(args[0]);
        if (amount < 1 
            ) {
          System.out.println("The <amount> argument must be between 1 and "
                             + ".");
        }
        else {
          try {
            dance(amount);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } catch (NumberFormatException e) {
        System.out.println(
            "The <amount> argument must be a valid integer value");
      }
    }
  }
}
