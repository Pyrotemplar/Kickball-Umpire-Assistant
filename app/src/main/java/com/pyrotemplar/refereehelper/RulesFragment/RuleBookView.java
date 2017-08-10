package com.pyrotemplar.refereehelper.RulesFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pyrotemplar.refereehelper.Adapters.RulesRecyclerAdapter;
import com.pyrotemplar.refereehelper.DataObjects.RuleBook;
import com.pyrotemplar.refereehelper.DialogFragments.AddNewRulesBookLinkDialogFragment;
import com.pyrotemplar.refereehelper.R;
import com.pyrotemplar.refereehelper.Utils.CustomWebview;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Manuel Montes de Oca on 4/25/2017.
 */

public class RuleBookView extends Fragment implements RuleBooksCotract.View, RulesRecyclerAdapter.ClickListener {
    public static final String SP_RULE_BOOK_KEY = "ruleBookKey";
    public static final String RULE_BOOK_POSITION = "ruleBookPosition";
    public static final String RULE_BOOK_TITLE = "ruleBookTitle";
    public static final String RULE_BOOK_URL = "ruleBookURL";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.webViewInclude)
    View webViewInclude;
    @BindView(R.id.ruleBookEntreeInclude)
    View ruleBookEntreeInclude;

    private AddNewRulesBookLinkDialogFragment addNewRulesBookLinkDialogFragment;
    private RulesRecyclerAdapter rulesRecyclerAdapter;
    private static RuleBooksCotract.Presenter mPresenter;
    private List<RuleBook> mRulebookList;
    private Bundle mArgs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.rule_books_layout, null);
        ButterKnife.bind(this, rootView);

        // inflater.inflate(R.layout.rules_layout, null);
        addNewRulesBookLinkDialogFragment = new AddNewRulesBookLinkDialogFragment();
        addNewRulesBookLinkDialogFragment.setTargetFragment(this, 2);

        mArgs = new Bundle();

        new RuleBookPresenter(this);

        mRulebookList = new ArrayList<>();
        loadRuleBookList();
        mPresenter.loadRuleBookList(mRulebookList);


        rulesRecyclerAdapter = new RulesRecyclerAdapter(getContext(), mRulebookList);
        rulesRecyclerAdapter.setListener(this);
        recyclerView.setAdapter(rulesRecyclerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helperCallBack());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();
        return rootView;

    }

    @Override
    public void onStop() {
        mRulebookList = mPresenter.getRuleBookList();
        saveRuleBookList();
        super.onStop();
    }

    private void loadRuleBookList() {

        Gson gson = new Gson();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        String json = prefs.getString(SP_RULE_BOOK_KEY, null); //Retrieve previously saved data
        if (json != null) {
            Type type = new TypeToken<ArrayList<RuleBook>>() {
            }.getType();
            mRulebookList = gson.fromJson(json, type); //Restore previous data
        }
        if (mRulebookList.isEmpty()) {
            String[] startingTitle = getResources().getStringArray(R.array.title);
            String[] startingURL = getResources().getStringArray(R.array.URL);

            for (int i = 0; i < startingTitle.length; i++) {
                mRulebookList.add(mPresenter.createRuleBook(startingTitle[i], startingURL[i], i));
            }

        }


    }

    private void saveRuleBookList() {

        // fetch the data
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor edit = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mRulebookList); //Convert the array to json
        edit.putString(SP_RULE_BOOK_KEY, json);
        edit.commit();
    }

    private ItemTouchHelper.Callback helperCallBack() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mPresenter.removeRuleBook(viewHolder.getAdapterPosition());
                rulesRecyclerAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                viewHolder.setIsRecyclable(false);
                rulesRecyclerAdapter.updateList();
            }
        };
        return simpleCallback;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            int position = data.getIntExtra(RULE_BOOK_POSITION, -1);
            if (position == -1)
                mPresenter.saveRuleBook(mPresenter.createRuleBook(data.getStringExtra(RULE_BOOK_TITLE), data.getStringExtra(RULE_BOOK_URL), mPresenter.getRuleBookList().size()));
            else
                mPresenter.editRuleBook(position, data.getStringExtra(RULE_BOOK_TITLE), data.getStringExtra(RULE_BOOK_URL));

            rulesRecyclerAdapter.updateList();

            // saveRuleBookList();
        }

    }


    @Override
    public void setPresenter(RuleBookPresenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.AddNewRuleLinkButtonView)
    @Override
    public void AddNewRuleLinkButtonClicked(View view) {

        mArgs = null;
        addNewRulesBookLinkDialogFragment.setArguments(mArgs);
        addNewRulesBookLinkDialogFragment.show(getFragmentManager(), "TAG");
        // if (isHapticFeedbackEnabled)
        //     hapticFeedback(view);
        //  return true;
    }

    @OnClick(R.id.ruleWebViewCloseButton)
    @Override
    public void ruleWebViewCloseButtonClicked(View view) {
        ruleBookEntreeInclude.setVisibility(View.VISIBLE);
        webViewInclude.setVisibility(View.INVISIBLE);
    }


    @Override
    public void itemClicked(View view, int position) {
        // TODO: 7/10/2017 implement WebView from here

        // String URL = getResources().getString(R.string.PDF_Google_Docs_Viewer_string) + mPresenter.getRuleBook(position).getURL();
        String URL = mPresenter.getRuleBook(position).getURL();
        if ((URL != null) && (URL.endsWith(".PDF") || URL.endsWith(".pdf"))) {
            URL = getResources().getString(R.string.PDF_Google_Docs_Viewer_string) + URL;
        }
        // recyclerView.setVisibility(View.INVISIBLE);
        //AddNewRuleLinkButtonView.setVisibility(View.INVISIBLE);
        ruleBookEntreeInclude.setVisibility(View.INVISIBLE);
        webViewInclude.setVisibility(View.VISIBLE);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        //mWebView.loadUrl("javascript:(function() { " +
        //     "document.getElementsByClassName('drive-viewer-toolstrip')[0].style.visibility='hidden'; })()");

    /*    mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (mWebView.getUrl() != null || (!mWebView.getUrl().equals(url))) {
                    view.loadUrl(url);
                }
                return true;
            }
        });*/

       /* if (mWebView != null || (!mWebView.getUrl().equals(URL)))
            mWebView.loadUrl(URL);*/
        mWebView.setWebViewClient(new CustomWebview());
        if (mWebView != null || (!mWebView.getUrl().equals(URL)))
            mWebView.loadUrl(URL);




    }


    @Override
    public void itemLongClicked(View view, int position) {
        mArgs = new Bundle();
        mArgs.putInt(RULE_BOOK_POSITION, position);
        mArgs.putString(RULE_BOOK_TITLE, mPresenter.getRuleBook(position).getTittle());
        mArgs.putString(RULE_BOOK_URL, mPresenter.getRuleBook(position).getURL());
        addNewRulesBookLinkDialogFragment.setArguments(mArgs);
        addNewRulesBookLinkDialogFragment.show(getFragmentManager(), "TAG");

    }
}
