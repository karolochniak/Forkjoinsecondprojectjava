import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class projekt2 {
    public static void main(String[] args) {
        long wszystkieKropki = 2000000000;
        ForkJoinPool basen = new ForkJoinPool();
        ZadaniePi glowneZadanie = new ZadaniePi(wszystkieKropki);

        long czasStart = System.currentTimeMillis();
        long wSrodkuKola = basen.invoke(glowneZadanie);
        long czasKoniec = System.currentTimeMillis();

        double oszacowanePi= 4.0 * wSrodkuKola/wszystkieKropki;

        System.out.println("\n--- WYNIKI ---");
        System.out.println("Przyblizenie Pi: "+oszacowanePi);
        System.out.println("Blad bezwzgledny: " + Math.abs(Math.PI - oszacowanePi));
        System.out.println("Czas wykonania: " + (czasKoniec - czasStart) + " ms");
        System.out.println("Uzyte watki robocze (rdzenie): " + basen.getParallelism());
    }
}

class ZadaniePi extends RecursiveTask<Long> {
    private static final long PROG= 5_000_000;
    private final long kropkiDoZrobienia;

    public ZadaniePi(long ileKropek) {
        this.kropkiDoZrobienia = ileKropek;
    }

    @Override
    protected Long compute() {
        if (kropkiDoZrobienia <= PROG) {
            return klepRecznie();
        } else
        {
            long polowa = kropkiDoZrobienia/2;
            long resztka = kropkiDoZrobienia - polowa;

            ZadaniePi leweZadanko = new ZadaniePi(polowa);
            ZadaniePi praweZadanko = new ZadaniePi(resztka);

            leweZadanko.fork();

            long wynikPrawy = praweZadanko.compute();
            long wynikLewy = leweZadanko.join();

            return wynikLewy + wynikPrawy;
        }
    }

    private long klepRecznie() {
        long trafione = 0;
        ThreadLocalRandom los = ThreadLocalRandom.current();

        for(long i=0; i<kropkiDoZrobienia; i++){
            double x = los.nextDouble();
            double y = los.nextDouble();

            if(x*x + y*y <= 1.0) {
                trafione++;
            }
        }
        return trafione;
    }
}
