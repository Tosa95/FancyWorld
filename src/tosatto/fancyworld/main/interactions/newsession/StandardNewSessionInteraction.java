/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.main.interactions.newsession;

import java.util.Random;
import tosatto.fancyworld.IO.MessageIO;
import tosatto.fancyworld.game.BaseGame;
import tosatto.fancyworld.game.Game;
import tosatto.fancyworld.game.player.PointedPlayer;
import tosatto.fancyworld.game.world.BundledWorld;
import tosatto.fancyworld.game.world.World;
import tosatto.fancyworld.game.world.factories.BundledWorldFactory;
import tosatto.fancyworld.game.world.generators.BasicRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.BundledRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.KeyedRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.RandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.TrialedRandomWorldGenerator;
import tosatto.fancyworld.game.world.generators.factories.WorldGeneratorStackFactory;
import tosatto.fancyworld.main.WorldTopologies;
import tosatto.fancyworld.main.bundles.ParametersBundle;
import tosatto.fancyworld.main.bundles.StandardBundleParametersNames;
import tosatto.fancyworld.main.bundles.factories.BundleFactory;
import tosatto.fancyworld.main.bundles.factories.StandardBundleFactory;
import tosatto.fancyworld.main.interactions.bundles.BaseBundleInteraction;
import tosatto.fancyworld.main.interactions.keysettings.KeySettingsInteraction;
import tosatto.fancyworld.main.interactions.trialsettings.TrialSettingsInteraction;

/**
 *
 * @author Davide
 */
public class StandardNewSessionInteraction implements NewSessionInteraction{

    private TrialSettingsInteraction trialsInteraction;
    private KeySettingsInteraction keysInteraction;
    private WorldGeneratorStackFactory genStackFactory;

    public StandardNewSessionInteraction(TrialSettingsInteraction trialsInteraction, 
            KeySettingsInteraction keysInteraction, WorldGeneratorStackFactory genStackFactory) {
        
        this.trialsInteraction = trialsInteraction;
        this.keysInteraction = keysInteraction;
        this.genStackFactory = genStackFactory;
    }
    
    
    @Override
    public Game interact(String topology, MessageIO io) {
        
        io.inform("Ora verrà creata una nuova sessione.");
           
        BundleFactory factory = new StandardBundleFactory();

        BaseBundleInteraction bbp = new BaseBundleInteraction();

        ParametersBundle pb = factory.create();

        bbp.interact(pb, io);

        RandomWorldGenerator gen = genStackFactory.create(pb, topology);

        PointedPlayer p = new PointedPlayer("Granli Brum");
        p.setPoints(pb.getParameter(StandardBundleParametersNames.INITIAL_POINTS));
        BundledWorld w = (BundledWorld)gen.generate();

        w.setBundle(pb);

        BaseGame game = new BaseGame(p, w, "Game");

        p.setPlace(w.getStartPlace());

        keysInteraction.interact(w, io);
        trialsInteraction.interact(w, io);


        game.setName(topology);
        
        return game;
    }
    
}
