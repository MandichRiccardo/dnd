public class Personaggio{
    private String nome;
    private int iniziativa;
    private int hp;
    private int ca;
    private int comp;
    private Caratteristica[] punteggi = new Caratteristica[6];
    private Caratteristica[] bonus = new Caratteristica[6];
    private int xp;
    private int lvl;
    private int ordine;
    private int tiro;
    private int dannoIniziale;
    private boolean amico;
    private boolean[][] morte;

    //inserire come parametro la stringa "valori" nel caso si vogliano assegnare dei valori di default mentre
    //inserire la stringa "input" nel caso si vogliano inserire da tastiera tutti i dati non ricavabili
    public Personaggio(String nome) {
        String metodoValori = Interazione.strput("vuoi che io prenda i valori di " + nome + " standard (inserisci \"valori\") o tramite input (inserisci \"input\")");
        for (int i = 0; i < 6; i++) {
            punteggi[i] = new Caratteristica();
            bonus[i] = new Caratteristica();
            switch (i) {
                case 0 -> {
                    punteggi[i].nome = "Forza\t\t\t";
                    bonus[i].nome = "Forza";
                }
                case 1 -> {
                    punteggi[i].nome = "Destrezza\t\t";
                    bonus[i].nome = "Destrezza";
                }
                case 2 -> {
                    punteggi[i].nome = "Costituzione\t";
                    bonus[i].nome = "Costituzione";
                }
                case 3 -> {
                    punteggi[i].nome = "Intelligenza\t";
                    bonus[i].nome = "Intelligenza";
                }
                case 4 -> {
                    punteggi[i].nome = "Saggezza\t\t";
                    bonus[i].nome = "Saggezza";
                }
                case 5 -> {
                    punteggi[i].nome = "Carisma\t\t";
                    bonus[i].nome = "Carisma";
                }
            }
        }
        switch (metodoValori) {
            case "valori" -> {
                iniziativa = 0;
                hp = 10;
                ca = 14;
                for(int i=0;i<6;i++){
                    punteggi[i].valore = 10;
                    bonus[i].valore = Caratteristica.getBonus(punteggi[i].valore);
                }
                comp = 2;
                xp = 200;
                lvl = 4;
                dannoIniziale = 0;
            }
            case "input" -> {
                hp = Interazione.input("quanti punti ferita ha " + nome + "?");
                ca = Interazione.input("qual'è la classe armatura di " + nome + "?");
                for (int i = 0; i < 6; i++) {
                    punteggi[i].valore = Interazione.input("qual'è il punteggio di " + bonus[i].nome + " di " + nome);
                    bonus[i].valore = Caratteristica.getBonus(punteggi[i].valore);
                }
                iniziativa = bonus[Caratteristica.destrezza].valore;
                xp = Interazione.input("quali sono i punti esperienza di " + nome + "?");
                lvl = Interazione.input("di che livello è " + nome + "?");
                switch (lvl) {
                    case 1, 2, 3, 4 -> comp = 2;
                    case 5, 6, 7, 8 -> comp = 3;
                    case 9, 10, 11, 12 -> comp = 4;
                    case 13, 14, 15, 16 -> comp = 5;
                    case 17, 18, 19, 20 -> comp = 6;
                }
                dannoIniziale = Interazione.input("qual'è il danno iniziale di " + nome);
            }
            default -> {
            }
        }
        this.nome = nome;
        ordine = 0;
        tiro = 0;
        morte = new boolean[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                morte[i][j] = false;
            }
        }
        amico = Interazione.boolput(nome+" è un amico?");
    }

    public String getNome() {
        return nome;
    }

    public int getIniziativa() {
        return iniziativa;
    }

    public int getHp() {
        return hp;
    }

    public int getCa() {
        return ca;
    }

    public int getComp() {
        return comp;
    }

    public int getValorePunteggi(int indice) {
        return punteggi[indice].valore;
    }

    public String getNomePunteggi(int indice) {
        return punteggi[indice].nome;
    }

    public int getValoreBonus(int indice) {
        return bonus[indice].valore;
    }

    public String getNomeBonus(int indice) {
        return bonus[indice].nome;
    }

    public int getXp() {
        return xp;
    }

    public int getLvl() {
        return lvl;
    }

    public int getOrdine() {
        return ordine;
    }

    public int getTiro() {
        return tiro;
    }

    public int getDannoIniziale() {
        return dannoIniziale;
    }

    public boolean getAmico() {
        return amico;
    }

    public boolean getMorte(int riga, int colonna) {
        return morte[riga][colonna];
    }

    public String getMorte() {
        String info = "";
        for(int i=0;i<2;i++){
            info = info.concat("\t");
            switch (i) {
                case 0 -> info += "superati\t\t\t\t\t";
                case 1 -> info += "falliti\t\t\t\t\t\t";
            }
            for(int j=0;j<3;j++){
                info = info.concat(getMorte(i, j)+"\t");
            }
            info += "\n";
        }
        return info;
    }

    public String getPunteggi() {
        String info = "";
        for (int i = 0; i < 6; i++) {
            info = info.concat("\tpunteggio " + getNomePunteggi(i) + "\t\t" + getValorePunteggi(i) + "\n");
        }
        return info;
    }

    public String getBonus() {
        String info = "";
        for (int i = 0; i < 6; i++) {
            info = info.concat("\tbonus " + getNomePunteggi(i) + "\t\t\t" + getValoreBonus(i) + "\n");
        }
        return info;
    }

    public String toString(){
        String info = "nome:\t\t\t\t\t\t\t"+getNome()+"\n";
        info+="iniziativa:\t\t\t\t\t\t"+getIniziativa()+"\n";
        info+="punti ferita:\t\t\t\t\t"+getHp()+"\n";
        info+="classe armatura:\t\t\t\t"+getCa()+"\n";
        info+="bonus competenza:\t\t\t\t"+getComp()+"\n";
        info+="punteggi statistiche:\n"+getPunteggi()+"\n";
        info+="bonus statistiche:\n"+getBonus()+"\n";
        info+="punti esperienza:\t\t\t\t"+getXp()+"\n";
        info+="livello:\t\t\t\t\t\t"+getLvl()+"\n";
        info+="ordine:\t\t\t\t\t\t\t"+getOrdine()+"\n";
        info+="tiro:\t\t\t\t\t\t\t"+getTiro()+"\n";
        info+="danno iniziale:\t\t\t\t\t"+getDannoIniziale()+"\n";
        info+="amico:\t\t\t\t\t\t\t"+getAmico()+"\n";
        info+="tiri salvezza contro morte:\n"+getMorte()+"\n";
        return info;
    }

    public boolean equals(Personaggio pg){
        if(this.nome != pg.nome){
            return false;
        }
        if(this.hp != pg.hp){
            return false;
        }
        if(this.ca != pg.ca){
            return false;
        }
        if(this.comp != pg.comp){
            return false;
        }
        for(int i=0;i<6;i++){
            if(!this.punteggi[i].equals(pg.punteggi[i])){
                return false;
            }
            if(!this.bonus[i].equals(pg.bonus[i])){
                return false;
            }
        }
        if(this.xp != pg.xp){
            return false;
        }
        if(this.lvl != pg.lvl){
            return false;
        }
        if(this.dannoIniziale != pg.dannoIniziale){
            return false;
        }
        if (this.amico != pg.amico) {
            return false;
        }
        return true;
    }
}