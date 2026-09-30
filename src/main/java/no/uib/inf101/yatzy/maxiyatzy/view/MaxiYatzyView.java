package no.uib.inf101.yatzy.maxiyatzy.view;

import no.uib.inf101.yatzy.View.YatzyView;
import no.uib.inf101.yatzy.maxiyatzy.model.MaxiYatzyModel;
import no.uib.inf101.yatzy.maxiyatzy.model.score.MaxiScoreCategory;

/**
 * View for the MaxiYatzy variant. Extends YatzyView with MaxiScoreCategory.
 */
public class MaxiYatzyView extends YatzyView<MaxiScoreCategory> {

    /**
     * Constructs a view for the MaxiYatzy game using its model.
     * 
     * @param model the MaxiYatzy game model
     */
    public MaxiYatzyView(MaxiYatzyModel model) {
        super(model, MaxiScoreCategory.values());
    }
}
