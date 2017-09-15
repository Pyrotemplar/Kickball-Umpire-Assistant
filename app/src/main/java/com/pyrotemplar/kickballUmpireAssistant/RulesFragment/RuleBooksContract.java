package com.pyrotemplar.kickballUmpireAssistant.RulesFragment;

import com.pyrotemplar.kickballUmpireAssistant.DataObjects.RuleBook;

import java.util.List;

/**
 * Created by Manny on 7/9/2017.
 */

public interface RuleBooksContract {
    interface View {
        void setPresenter(RuleBookPresenter clickerPresenter);

        void AddNewRuleLinkButtonClicked(android.view.View view);

        void ruleWebViewCloseButtonClicked(android.view.View view);

    }

    interface Presenter {
        RuleBook getRuleBook(int position);

        RuleBook createRuleBook(String title, String URL, int position);

        void editRuleBook(int position, String title, String URL);

        void saveRuleBook(RuleBook ruleBook);

        void removeRuleBook(int position);

        void loadRuleBookList(List<RuleBook> ruleBookList);

        List<RuleBook> getRuleBookList();
    }
}
