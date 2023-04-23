package nl.hhs;

import java.util.ArrayList;

// Voor een observable relevante functionaliteit wordt afgehandeld
// binnen deze class, maar van Observable zelf kan geen object worden
// gemaakt.
public abstract class Observable {

    // De interne lijst met observers.
    private final ArrayList<IObserver> observers;

    // De status van de observable: is deze wel of niet gewijzigd?
    private boolean hasChanged;

    // De object-variabelen (properties) krijgen een initiële
    // waarde.
    protected Observable() {
        this.observers = new ArrayList<> ();
        clearChanged ();
    }

    // Voegt een observer toe aan de interne lijst met observers.
    public void addObserver (IObserver observer) {
        observers.add (observer);
    }

    // Verwijdert een observer uit de interne lijst met observers.
    public boolean deleteObserver (IObserver observer) {
        return observers.remove (observer);
    }

    // Verwijdert alle observers uit de interne lijst met observers.
    public void deleteObservers () {
        observers.clear ();
    }

    // Geeft het aantal observers in de interne lijst terug.
    public int countObservers () {
        return observers.size ();
    }

    // Maakt de interne status (of de observable wel of niet veranderd is) true.
    protected void setChanged () {
        hasChanged = true;
    }

    // Maakt de interne status (of de observable wel of niet veranderd is) false.
    protected void clearChanged () {
        hasChanged = false;
    }

    // Geeft de waarde van de interne status (wel of niet veranderd) terug.
    public boolean hasChanged () {
        return hasChanged;
    }

    // Als de interne status aangeeft dat de observable veranderd is, ontvangen
    // alle in de interne lijst geregistreerde gebruikers daarvan een bericht.
    public void notifyObservers () {
        notifyObservers (null);
    }

    // Idem als hierboven, maar nu wordt een object meegestuurd naar de methode
    // update in de interface van de observer.
    public void notifyObservers (Object object) {

        if (hasChanged ()) {

            for (IObserver observer : observers) {
                observer.update (this, object);
            }

            clearChanged ();
        }
    }
}