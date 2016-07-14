package com.carvalho.education.hbc.estudolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.carvalho.education.hbc.HbcApp;
import com.carvalho.education.hbc.R;
import com.carvalho.education.hbc.entities.Estudo;
import com.carvalho.education.hbc.estudolist.EstudoListPresenter;
import com.carvalho.education.hbc.estudolist.events.EstudoListEvent;
import com.carvalho.education.hbc.estudolist.ui.adapters.EstudoAdapter;
import com.carvalho.education.hbc.estudolist.ui.adapters.OnItemClickListerner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 06094547659 on 14/07/2016.
 */
public class EstudoListActivity extends AppCompatActivity implements EstudoListView, OnItemClickListerner {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    EstudoAdapter adapter;
    EstudoListPresenter presenter;

    public EstudoListActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_stored_list);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.getEstudos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_main) {
            navigateToMainScreen();
            return true;
        } else if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, HbcApp.class));
    }

    private void logout() {
        /*LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_estudo_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        Estudo estudo = new Estudo();
        estudo.setComment("Direito Administrativo");
        estudo.setDate(new Date());
        estudo.setEstudoID("1");
        estudo.setTime("18:00");

        Estudo estudo2 = new Estudo();
        estudo2.setComment("Direito Tributario");
        estudo2.setDate(new Date());
        estudo2.setEstudoID("2");
        estudo2.setTime("18:45");
        adapter = new EstudoAdapter(Arrays.asList(estudo, estudo2), this);
        presenter = new EstudoListPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void getEstudos() {

            }

            @Override
            public void removeEstudo(Estudo estudo) {

            }

            @Override
            public void onEventMainThread(EstudoListEvent event) {

            }

            @Override
            public EstudoListView getView() {
                return null;
            }
        };
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.toolbar)
    public void onToobarClick() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void setEstudos(List<Estudo> data) {
        adapter.setEstudos(data);
    }

    @Override
    public void estudoUpdate() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void estudoDeleted(Estudo estudo) {
        adapter.removeEstudo(estudo);
    }

    @Override
    public void onDeleteClick(Estudo estudo) {
        presenter.removeEstudo(estudo);
    }

    @Override
    public void onItemClick(Estudo estudo) {
        //presenter.
        //abrir nova atividade de edição
    }
}
