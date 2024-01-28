package org.etu.docteur.fanafody;

import org.etu.docteur.aretina.Parametre;

public class ParametreFanafody extends Parametre {

    int niveauEffetFanafody;
    public ParametreFanafody(int idParametre, String nomParametre, int niveauEffet) {
        super(idParametre, nomParametre);
        setNiveauEffetFanafody(niveauEffet);
    }
    public int getNiveauEffetFanafody() {
        return niveauEffetFanafody;
    }

    public void setNiveauEffetFanafody(int niveauEffetFanafody) {
        this.niveauEffetFanafody = niveauEffetFanafody;
    }
}
