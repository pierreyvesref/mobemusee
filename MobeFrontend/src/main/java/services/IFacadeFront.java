package services;

import objects.musee.Exposition;
import objects.musee.Oeuvre;
import objects.musee.Salle;

import java.util.Collection;

public interface IFacadeFront {

    Collection<Oeuvre> getAllOeuvres();
    Collection<Exposition> getAllExpositions();

}
