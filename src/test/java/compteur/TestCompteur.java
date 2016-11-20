package compteur;
import beans.Compteur;
import org.junit.Test;

/**
 * Created by Florian on 20/11/2016.
 */
public class TestCompteur {

    @Test
    public void creeCompteur() {

        Compteur c = new Compteur(-1,"monTitreDeTest","France","08/11/2016 17:30:00",9999);
    }

    @Test
    public void getCompteur() {

        Compteur c = new Compteur(-1,"monTitreDeTest","France","08/11/2016 17:30:00",9999);

        assert(c.getId() == -1);
        assert(c.getTitre().equals("monTitreDeTest"));
        assert(c.getPays().equals("France"));
        assert(c.getDate().equals("08/11/2016 17:30:00"));
        assert(c.getIdSession() == 9999);
    }

    @Test
    public void setCompteur() {

        Compteur c = new Compteur(-1,"monTitreDeTest","France","08/11/2016 17:30:00",9999);

        c.setId(-2);
        assert(c.getId() == -2);

        c.setTitre("titreModif");
        assert(c.getTitre().equals("titreModif"));

        c.setDate("20/11/2016 01:30:00");
        assert(c.getDate().equals("20/11/2016 01:30:00"));

        c.setIdSession(-5);
        assert(c.getIdSession() == -5);
    }
}
