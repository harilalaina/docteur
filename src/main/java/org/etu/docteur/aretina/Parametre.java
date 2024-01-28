package org.etu.docteur.aretina;

public class Parametre {
    int idParametre;
    String nomParametre;

    public Parametre(int idParametre, String nomParametre) {
        setIdParametre(idParametre);
        setNomParametre(nomParametre);
    }

    public int getIdParametre() {
        return idParametre;
    }

    public void setIdParametre(int idParametre) {
        this.idParametre = idParametre;
    }

    public String getNomParametre() {
        return nomParametre;
    }

    public void setNomParametre(String nomParametre) {
        this.nomParametre = nomParametre;
    }

    public int calculerDistanceParametre(int niveauParametre, ParametreAretina parametreAretina) {

        if (niveauParametre < parametreAretina.getInf()) {
            return parametreAretina.getInf() - niveauParametre;
        } else if (niveauParametre > parametreAretina.getSup()) {
            return niveauParametre - parametreAretina.getSup();
        } else {
            return 0;
        }

    }
}
