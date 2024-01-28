package org.etu.docteur.aretina;

public class ParametreAretina extends Parametre {
    int inf;
    int sup;

    public ParametreAretina(int idParametre, String nomParametre, int inf, int sup) {
        super(idParametre, nomParametre);
        setInf(inf);
        setSup(sup);
    }

    public int getInf() {
        return inf;
    }

    public void setInf(int inf) {
        this.inf = inf;
    }

    public int getSup() {
        return sup;
    }

    public void setSup(int sup) {
        this.sup = sup;
    }

}
