package org.etu.docteur.fanafody;

import java.util.List;

public class Fanafody {
    int idFanafody;
    String nomFanafody;
    double prixFanafody;
    List<ParametreFanafody> parametresFanafody;

    public Fanafody(int idFanafody, String nomFanafody, double prixFanafody, List<ParametreFanafody> parametresFanafody) {
        setIdFanafody(idFanafody);
        setNomFanafody(nomFanafody);
        setPrixFanafody(prixFanafody);
        setParametresFanafody(parametresFanafody);
    }

    public int getIdFanafody() {
        return idFanafody;
    }

    public void setIdFanafody(int idFanafody) {
        this.idFanafody = idFanafody;
    }

    public String getNomFanafody() {
        return nomFanafody;
    }

    public void setNomFanafody(String nomFanafody) {
        this.nomFanafody = nomFanafody;
    }

    public double getPrixFanafody() {
        return prixFanafody;
    }

    public void setPrixFanafody(double prixFanafody) {
        this.prixFanafody = prixFanafody;
    }

    public List<ParametreFanafody> getParametresFanafody() {
        return parametresFanafody;
    }

    public void setParametresFanafody(List<ParametreFanafody> parametresFanafody) {
        this.parametresFanafody = parametresFanafody;
    }

    public int getNiveauEffetFanafody(int idParametre) {

        for (ParametreFanafody parametreFanafody : parametresFanafody) {
            int idParametreFanafody = parametreFanafody.getIdParametre();

            if (idParametre == idParametreFanafody) {
                return parametreFanafody.getNiveauEffetFanafody();
            }
        }

        return 0;
    }
}
