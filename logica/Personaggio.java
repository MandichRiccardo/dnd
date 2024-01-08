package logica;
public class Personaggio {
    protected String nome;
    protected int iniziativa;
    protected int puntiFerita;
    protected int puntiFeritaTotali;
    protected int classeArmatura;
    protected int competenza;
    protected int puntiEsperienza;
    protected int livello;
    protected boolean ispirazione;
    protected int tiro;
    protected int dannoIniziale;
    protected int bonusSalvezza;
    protected boolean[][] tiriControMorte = new boolean[2][3];
    protected boolean morto;
    protected Statistica forza;
    protected Statistica destrezza;
    protected Statistica costituzione;
    protected Statistica intelligenza;
    protected Statistica saggezza;
    protected Statistica carisma;

    public Personaggio() {
        this.nome = Dadi.getString("inserisci il nome del personaggio");
        this.iniziativa = Dadi.getInt("Inserisci l'iniziativa di " + this.nome);
        this.puntiFerita = Dadi.getInt("Inserisci i punti ferita di " + this.nome);
        this.puntiFeritaTotali = Dadi.getInt("Inserisci i punti ferita totali di " + this.nome);
        this.classeArmatura = Dadi.getInt("Inserisci la classe armatura di " + this.nome);
        this.competenza = Dadi.getInt("Inserisci la competenza di " + this.nome);
        this.puntiEsperienza = Dadi.getInt("Inserisci i punti esperienza di " + this.nome);
        this.livello = Dadi.getInt("Inserisci l'iniziativa di " + this.nome);
        this.ispirazione = Dadi.getBoolean("Inserisci l'ispirazione di " + this.nome);
        this.dannoIniziale = Dadi.getInt("Inserisci il danno iniziale di " + this.nome);
        for(int i=0;i<2;i++) {
            for (boolean tiro : tiriControMorte[i]) tiro = false;
        }
        int successi = -1;
        int fallimenti = -1;
        while(successi<0 || successi > 2){
            successi = Dadi.getInt("quanti successi nei tiri contro morte ha fatto " + this.nome);
        }
        for(int i=0;i<successi;i++) tiriControMorte[0][i] = true;
        while(fallimenti<0 || fallimenti > 2){
            fallimenti = Dadi.getInt("quanti successi nei tiri contro morte ha fatto " + this.nome);
        }
        for(int i=0;i<fallimenti;i++) tiriControMorte[1][i] = true;
        this.morto = tiriControMorte[1][2];
        this.forza = new Statistica(Dadi.getInt("Inserisci il punteggio di forza di " + this.nome));
        this.destrezza = new Statistica(Dadi.getInt("Inserisci il punteggio di destrezza di " + this.nome));
        this.costituzione = new Statistica(Dadi.getInt("Inserisci il punteggio di costituzione di " + this.nome));
        this.intelligenza = new Statistica(Dadi.getInt("Inserisci il punteggio di intelligenza di " + this.nome));
        this.saggezza = new Statistica(Dadi.getInt("Inserisci il punteggio di saggezza di " + this.nome));
        this.carisma = new Statistica(Dadi.getInt("Inserisci il punteggio di carisma di " + this.nome));
    }

    public int tiroForza(){
        return Dadi.tiro(0, 20, this.forza.bonus);
    }

    public int tiroDestrezza(){
        return Dadi.tiro(0,20, this.destrezza.bonus);
    }

    public int tiroCostituzione(){
        return Dadi.tiro(0,20, this.costituzione.bonus);
    }

    public int tiroIntelligenza(){
        return Dadi.tiro(0,20, this.intelligenza.bonus);
    }

    public int tiroSaggezza(){
        return Dadi.tiro(0,20, this.saggezza.bonus);
    }

    public int tiroCarisma(){
        return Dadi.tiro(0,20, this.carisma.bonus);
    }

    public int tiroSalvezza(String statistica){
        statistica = statistica.toLowerCase();
        switch (statistica) {
            case "for" -> {
                if (forza.salvezza) return tiroForza() + bonusSalvezza;
                else return tiroForza();
            }
            case "des" -> {
                if (destrezza.salvezza) return tiroDestrezza() + bonusSalvezza;
                else return tiroDestrezza();
            }
            case "cos" -> {
                if (costituzione.salvezza) return tiroCostituzione() + bonusSalvezza;
                else return tiroCostituzione();
            }
            case "int" -> {
                if (intelligenza.salvezza) return tiroIntelligenza() + bonusSalvezza;
                else return tiroIntelligenza();
            }
            case "sag" -> {
                if (saggezza.salvezza) return tiroSaggezza() + bonusSalvezza;
                else return tiroSaggezza();
            }
            case "car" -> {
                if (carisma.salvezza) return tiroCarisma() + bonusSalvezza;
                else return tiroCarisma();
            }
            default -> {
                System.out.println("WARNING:\thai inserito una statistica non riconosciuta per cui ho tirato un d20 puro");
                return Dadi.tiro(1, 20);
            }
        }
    }


    public class Statistica{
        public int punteggio;
        public int bonus;
        public boolean salvezza;

        public Statistica(int punteggio){
            this.punteggio = punteggio;
            bonus = punteggio-10;
            if(bonus<0)
                bonus--;
            bonus /= 2;
        }
    }

}