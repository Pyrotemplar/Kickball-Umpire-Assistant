package com.pyrotemplar.kickballUmpireAssistant.LeagueFragment;


/**
 * Created by Manuel Montes de Oca on 8/16/2017.
 */

public class LeagueContract {
    interface View {
        void AddNewTeamButtonClicked(android.view.View view);

        void setPresenter(LeaguePresenter presenter);
    }

    interface Presenter {
    }
}
