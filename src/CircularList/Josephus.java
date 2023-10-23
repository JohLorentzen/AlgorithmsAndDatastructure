package CircularList;

/**
 * Represents a survival program that josephus can use to survive his men.
 * @author johanneslorentzen
 */
public class Josephus {
  private CircularList josephusMen;

  /**
   * Creates the army to josephus including himself
   *
   * @param nrMen the number of men josephus has + himself
   */
  public Josephus(int nrMen) {
    josephusMen = new CircularList();
    for (int i = 1; i <= nrMen; i++) {
      josephusMen.addLast(i);
    }
  }

  /**
   * Finds the number that does not get killed by any other.
   *
   * @param killInterval the killing order interval.
   */
  public void findSurvivor(int killInterval) {
    int count = 0;
    for (Node e = josephusMen.findHead(); e != null; e = e.next) {
      if (josephusMen.findNr() == 1) {
        System.out.printf("\nNr: %s  was left alone.\n", e.element);
        break;
      }
      else if (count == killInterval-1) {
        System.out.printf("\nNr: %s was killed.\n", e.element);
        josephusMen.deleteNode(e);
        count = 0;
        continue;
      }
      count++;
    }
  }

}


