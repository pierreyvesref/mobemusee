package services;

import objects.musee.Exposition;
import objects.musee.Oeuvre;

import java.util.Collection;
import java.util.List;

public class FacadeFront implements IFacadeFront {

    private static final FacadeFront instance = new FacadeFront();

    private List<Oeuvre> oeuvres;
    private List<Exposition> expositions;

    public static FacadeFront renvoyerInstance() {
        return instance;
    }

    @Override
    public Collection<Oeuvre> getAllOeuvres() {
        return this.oeuvres;
    }

    @Override
    public Collection<Exposition> getAllExpositions() {
        return this.expositions;
    }

}
