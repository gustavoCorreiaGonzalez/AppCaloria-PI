package com.example.avellb155max.appcalorias;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.example.avellb155max.appcalorias.Atividades.ListarAtividade;
import com.example.avellb155max.appcalorias.Atividades.ListarCategoria;
import com.example.avellb155max.appcalorias.Fragmentos.FragmentDadosPessoais;
import com.example.avellb155max.appcalorias.Fragmentos.FragmentDiario;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.RecyclerViewCacheUtil;

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

        // Troca o nome da ToolBar
        setSupportActionBar(toolbar);
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
                .withToolbar(toolbar)
                .withHasStableIds(true)
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
                                Fragment fragment_user = new FragmentDadosPessoais();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment_user).addToBackStack(null).commit();
                                break;
                            case 2:
                                Fragment fragment_diario = new FragmentDiario();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment_diario).addToBackStack(null).commit();
                                break;
                            case 3:
                                /*Fragment fragment_atividade = new FragmentAtividadeFisica();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment_atividade).addToBackStack(null).commit();*/

                                Intent i = new Intent(view.getContext(), ListarCategoria.class);
                                startActivity(i);
                                break;
                            case 4:
                                /*Fragment fragment_refeicao = new FragmentRefeicoes();
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment_refeicao).addToBackStack(null).commit();*/

                                Intent i2 = new Intent(view.getContext(), ListarAtividade.class);
                                Bundle params = new Bundle();

                                params.putString("idTipo", String.valueOf(5));
                                i2.putExtras(params);
                                startActivity(i2);
                                break;
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
        //if you have many different types of DrawerItems you can magically pre-cache those items to get a better scroll performance
        //make sure to init the cache after the DrawerBuilder was created as this will first clear the cache to make sure no old elements are in
        RecyclerViewCacheUtil.getInstance().withCacheSize(2).init(result);

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelection(21, false);

            //set the active profile
            headerResult.setActiveProfile(profile);
        }

        result.updateBadge(4, new StringHolder(10 + ""));
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
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

}


