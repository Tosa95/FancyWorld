/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tosatto.fancyworld.game.world.generators.factories;

import tosatto.fancyworld.game.world.generators.RandomWorldGenerator;
import tosatto.fancyworld.main.bundles.ParametersBundle;

/**
 *
 * @author Davide
 */
public interface WorldGeneratorStackFactory {
    public RandomWorldGenerator create (ParametersBundle bundle, String topology);
}
