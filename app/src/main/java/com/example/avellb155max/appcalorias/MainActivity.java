package com.example.avellb155max.appcalorias;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.avellb155max.appcalorias.Fragmentos.FragmentAtividadeFisica;
import com.example.avellb155max.appcalorias.Fragmentos.FragmentDiario;
import com.example.avellb155max.appcalorias.Fragmentos.FragmentPerformance;
import com.example.avellb155max.appcalorias.Fragmentos.FragmentRefeicoes;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    // Guarda o profile que está sendo usado
    private static final int PROFILE_SETTING = 1;

    // Váriavel dos construtores
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Troca o nome da ToolBar
        getSupportActionBar().setTitle(R.string.drawer_item_compact_header);

        // Crição de profiles
        final IProfile profile = new ProfileDrawerItem().withName("Gustavo").withEmail("gustavo@gmail.com");

        // Criação do ambiente da conta no Drawer
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header3)
                .addProfiles(profile,
                        new ProfileSettingDrawerItem().withName("Adicionar Conta").withDescription("TEST").withIdentifier(PROFILE_SETTING))
                .withSavedInstance(savedInstanceState)
                .build();

        // Criação do Drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) // Setando o account header no drawer
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_user).withIcon(FontAwesome.Icon.faw_user),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_Diary).withIcon(FontAwesome.Icon.faw_book),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_physical_activities).withIcon(FontAwesome.Icon.faw_bicycle),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_physical_meal).withIcon(FontAwesome.Icon.faw_cutlery),

                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    switch (position) {
                            case 1:
                                Fragment fragment_user = new FragmentPerformance();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment_user).addToBackStack(null).commit();
                                break;
                            case 2:
                                Fragment fragment_diario = new FragmentDiario();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment_diario).addToBackStack(null).commit();
                                break;
                            case 3:
                                Fragment fragment_atividade = new FragmentAtividadeFisica();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment_atividade).addToBackStack(null).commit();
                                break;
                            case 4:
                                Fragment fragment_refeicao = new FragmentRefeicoes();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment_refeicao).addToBackStack(null).commit();
                                break;
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    class ActionBarCallBack implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(com.mikepenz.materialize.util.UIUtils.getThemeColorFromAttrOrRes(MainActivity.this, R.attr.colorPrimaryDark, R.color.material_drawer_primary_dark));
            }

            //mode.getMenuInflater().inflate(R.menu.cab, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    }
}
