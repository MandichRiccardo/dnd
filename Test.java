public class Test {
    public static void main(String[] args) {
        int tot = Interazione.input("quanti altri personaggi stanno combattendo?");
        Personaggio[] pg = new Personaggio[tot];
        creaPg(pg);
        for(int i=0;i<tot;i++) {
            Interazione.output(pg[i].toString());
        }
        pg[0].preparazioneOrdine(pg);
        pg[0].combattimento(pg);
        Interazione.close();
    }

    public static void creaPg(Personaggio[] pg){
        int i=0;
        while(i<pg.length){
            if(pg[i] != null){
                if(Interazione.boolput("vuoi modificare il personaggio?")) pg[i].modificaPg();
                i++;
            }else if(Interazione.boolput("vuoi prendere questo personaggio dal file dati?")){
                Reader r = new Reader();
                String[][] tabel = r.getCsv("Personaggi.csv");
                outElencoCsv(tabel);
                pg[i] = new Personaggio(tabel[Interazione.input("\ninserisci il valore corrispondente al personaggio che vuoi inserire\t(poi sarà possibile modificare il personaggio")]);
            }else{
                pg[i] = new Personaggio();
            }
        }
    }
    public static void outElencoCsv(String[][] tabel){
        for(int i=0;i<tabel.length;i++){
            System.out.print(i + ")\t");
            for(String r:tabel[i]){
                System.out.printf("%-35s", r);
            }
            Interazione.output("");
        }
    }
}