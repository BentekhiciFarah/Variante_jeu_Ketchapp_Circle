import java.util.Random;

/** Cette classe implémente un thread simple qui compte jusqu'à 100 */
class Tache extends Thread {

  /** Le générateur de nombres aléatoires */
  private static final Random rand = new Random();

  @Override
  public void run() {
    /* ce thread compte de 0 à 99 par pas de 1
     * avec un sleep de 100 à 300 ms entre chaque pas */
    for(int i=0;i<100;i++) {
      System.out.println("Tache "+getName()+" étape "+i);
      try { Thread.sleep(rand.nextInt(200)+100); }
      catch (Exception e) { e.printStackTrace(); }
    }
  }

  /** Le programme de test lance deux threads entrelacés */
  public static void main(String[] args) {
    (new Tache()).start();
    (new Tache()).start();
  }
}