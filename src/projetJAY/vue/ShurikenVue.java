package projetJAY.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import projetJAY.modele.Composant;

public class ShurikenVue {

    private Composant shurikenDuModele;
    private Pane paneJeu;
    private ImageView shuriken;

    public ShurikenVue(Composant ennemiDuModele, Pane paneJeu) {
        this.shurikenDuModele = ennemiDuModele;
        this.paneJeu = paneJeu;
    }

    public ImageView getShurikenCree() {
        return shuriken;
    }

    public void creerShuriken() {

        Image ShurikenIMG = new Image("/projetJAY/ressources/shuriken.png");
        ImageView ShurikenSurLaVue = new ImageView();
        ShurikenSurLaVue.setImage(ShurikenIMG);
        ShurikenSurLaVue.setX(0);
        ShurikenSurLaVue.setY(0);
        ShurikenSurLaVue.setFitWidth(17);
        ShurikenSurLaVue.setFitHeight(17);
        ShurikenSurLaVue.translateXProperty().bind(shurikenDuModele.getPositionXProperty());
        ShurikenSurLaVue.translateYProperty().bind(shurikenDuModele.getPositionYProperty());
        ShurikenSurLaVue.setId(shurikenDuModele.getIdComposant());

        this.paneJeu.getChildren().add(ShurikenSurLaVue);
        this.shuriken = ShurikenSurLaVue;
    }
}