public class Harcos {
    private String nev;
    private int szint;
    private int tapasztalat;
    private int eletero;
    private int alapEletero;
    private int alapSebzes;


    public Harcos(String nev, int statuszSablon) {
        this.nev = nev;
        szint = 1;
        tapasztalat = 0;
        switch (statuszSablon) {
            case 1:
                alapEletero = 15;
                alapSebzes = 3;
                break;
            case 2:
                alapEletero = 12;
                alapSebzes = 4;
                break;
            case 3:
                alapEletero = 8;
                alapSebzes = 4;
                break;
        }
        eletero = getMaxEletero();
    }
    // -----------------------------------------------------------------------------
    // Alap getterek

    public String getNev() {
        return nev;
    }

    public int getSzint() {
        return szint;
    }

    public int getTapasztalat() {
        return tapasztalat;
    }

    public int getEletero() {
        return eletero;
    }

    public int getAlapEletero() {
        return alapEletero;
    }

    public int getAlapSebzes() {
        return alapSebzes;
    }
    // Alap getterek vége
    // -----------------------------------------------------------------------------


    // -----------------------------------------------------------------------------
    // Alap setterek
    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setSzint(int szint) {
        this.szint = szint;
    }

    public void setTapasztalat(int tapasztalat) {
        this.tapasztalat = tapasztalat;
        if (this.tapasztalat > getSzintLepeshez()) {
            setSzint(getSzint() + 1);
            this.tapasztalat -= getSzintLepeshez();
            setEletero(getMaxEletero());
        }
    }

    public void setEletero(int eletero) {
        this.eletero = eletero;
        if (this.eletero <= 0) {
            setTapasztalat(0);
        }
        if (this.eletero > getMaxEletero()) {
            this.eletero = getMaxEletero();

        }
    }

    public void setAlapEletero(int alapEletero) {
        this.alapEletero = alapEletero;
    }

    public void setAlapSebzes(int alapSebzes) {
        this.alapSebzes = alapSebzes;
    }
    //Alap setterek vége
    // -----------------------------------------------------------------------------

    // -----------------------------------------------------------------------------
    // Többi getter

    public int getSebzes() {
        return alapSebzes + szint;
    }

    public int getSzintLepeshez() {
        return 10 + szint * 5;
    }

    public int getMaxEletero() {
        return alapEletero + szint * 3;
    }
    // Többi getter vége
    // -----------------------------------------------------------------------------

    public void megkuzd(Harcos masikHarcos) {
        boolean tovabb = true;
        if (masikHarcos.equals(this)) {
            tovabb = false;
            System.out.println("Hiba, ugyan azt a harcost adta meg!");
        } else if (this.getEletero() <= 0 || masikHarcos.getEletero() <= 0) {
            tovabb = false;
            System.out.println("Az egyik harcos életereje 0");
        }

        if (tovabb) {
            masikHarcos.setEletero(masikHarcos.getEletero() - this.getSebzes());
            if (masikHarcos.getEletero() > 0) {
                this.setEletero(this.getEletero() - masikHarcos.getSebzes());
            }
            if (this.getEletero() > 0) {
                this.setSzint(this.getSzint() + 5);
            } else {
                this.setEletero(0);
                masikHarcos.setSzint(masikHarcos.getSzint() + 10);
            }
            if (masikHarcos.getEletero() > 0) {
                masikHarcos.setEletero(0);
                masikHarcos.setSzint(masikHarcos.getSzint() + 5);
            } else {
                this.setSzint(this.getSzint() + 10);
            }

        }
    }

    public void gyogyul() {
        if (this.getEletero() == 0) {
            this.setEletero(this.getMaxEletero());
        } else {
            this.setEletero(this.getEletero() + 3 + this.getSzint());
        }
    }

    @Override
    public String toString() {
        return String.format("%s - LVL: %s - EXP: %d - HP: %d%% - DMG: %d",
                nev, szint, tapasztalat / getSzintLepeshez(), eletero / getMaxEletero() * 100, getSebzes());
    }


}
