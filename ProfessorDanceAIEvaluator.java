

import org.jgap.*;
import java.util.ArrayList;

public class ProfessorDanceAIEvaluator
    extends FitnessFunction {


  public static final int MAX_BOUND = 1000;
  private ArrayList<Integer[]> trail_list;
  private int trail_length;

  public ProfessorDanceAIEvaluator(int tl) {
    trail_length = tl;
    trail_list = new ArrayList<Integer[]>();
  }

 //Euclidean distance for novelty search
  //fitness func gives higher score to every trail done so far
  //sums over distance of current path at each step to the corresponding step in archived path
  //does this for every archived path.
  public double evaluate(IChromosome a_subject) {
    float sum = 0;
    for (Integer[] trail : trail_list) {
      int archive_x = 0;
      int archive_y = 0;
      int candidate_x = 0;
      int candidate_y = 0;
      for (int i = 0; i < trail_length; i++){
         if (trail[i] == 0) archive_y += 1;
         else if (trail[i] == 1) archive_x += 1;
         else if (trail[i] == 2) archive_y -= 1;
         else archive_x -= 1;
         if (Integer.parseInt(a_subject.getGene(i).toString().substring(a_subject.getGene(i).toString().length() - 1)) == 0) candidate_y += 1;
         else if (Integer.parseInt(a_subject.getGene(i).toString().substring(a_subject.getGene(i).toString().length() - 1)) == 1) candidate_x += 1;
         else if (Integer.parseInt(a_subject.getGene(i).toString().substring(a_subject.getGene(i).toString().length() - 1)) == 2) candidate_y -=1;
         else candidate_x -= 1;
         sum += Math.sqrt((candidate_x - archive_x)*(candidate_x - archive_x) + (candidate_y - archive_y)*(candidate_y-archive_y));
      }
    }
    Gene[] chromosome = a_subject.getGenes();
    Integer[] int_array = new Integer[a_subject.size()];
    //this is hacky for type-checking.
    for (int i = 0; i < a_subject.size(); i++)
      int_array[i] = Integer.parseInt(a_subject.getGene(i).toString().substring(a_subject.getGene(i).toString().length() - 1));
    trail_list.add(int_array);
    return sum;
  }

  public ArrayList<Integer[]> dance_dump() {
     return trail_list;
  }
}
