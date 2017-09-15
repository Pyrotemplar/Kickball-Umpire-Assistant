package com.pyrotemplar.kickballUmpireAssistant.RulesFragment;

import android.support.annotation.NonNull;

import com.pyrotemplar.kickballUmpireAssistant.DataObjects.RuleBook;

import java.util.List;

/**
 * Created by Manny on 7/10/2017.
 */

public class RuleBookPresenter implements RuleBooksContract.Presenter {


    private List<RuleBook> arrayOfRuleBooks;
   // private RuleBooksContract.View mRuleBookView;

    RuleBookPresenter(@NonNull RuleBooksContract.View ruleBookView) {

      //  this.mRuleBookView = ruleBookView;
        ruleBookView.setPresenter(this);

    }


    @Override
    public RuleBook getRuleBook(int position) {
        return arrayOfRuleBooks.get(position);
    }

    @Override
    public RuleBook createRuleBook(String title, String URL, int position) {

        RuleBook ruleBook = new RuleBook();
        ruleBook.setTittle(title);
        ruleBook.setURL(URL);
        ruleBook.setPosition(position);
        return ruleBook;
    }

    @Override
    public void editRuleBook(int position, String title, String URL) {

        arrayOfRuleBooks.get(position).setTittle(title);
        arrayOfRuleBooks.get(position).setURL(URL);

    }

    @Override
    public void saveRuleBook(RuleBook ruleBook) {

        this.arrayOfRuleBooks.add(ruleBook);
    }

    @Override
    public void removeRuleBook(int position) {
        this.arrayOfRuleBooks.remove(position);

    }

    @Override
    public void loadRuleBookList(List<RuleBook> ruleBookList) {
        this.arrayOfRuleBooks = ruleBookList;
    }


    @Override
    public List<RuleBook> getRuleBookList() {
        return arrayOfRuleBooks;
    }


}
