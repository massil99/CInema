package Observateur;

public interface Sujet {
    void addObservateur(ObservateursListeFilms ob);
    void removeObservateur(ObservateursListeFilms ob);
    void notifier();
}
