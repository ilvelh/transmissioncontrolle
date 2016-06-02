package ca.teyssedre.android.transmissioncontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import ca.teyssedre.android.transmissioncontrol.model.item.TransmissionElement;
import ca.teyssedre.android.transmissioncontrol.model.request.TransmissionRequest;
import ca.teyssedre.android.transmissioncontrol.model.response.ListArgsResponse;
import ca.teyssedre.android.transmissioncontrol.task.ListItems;
import ca.teyssedre.android.transmissioncontrol.task.OnTaskComplete;
import ca.teyssedre.android.transmissioncontrol.view.OnTorrentClickListener;
import ca.teyssedre.android.transmissioncontrol.view.TorrentListAdapter;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements OnTorrentClickListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private OnTaskComplete<TransmissionRequest<ListArgsResponse>> onListResponse;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Validate that a profile exist

        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert toolbar != null;
        toolbar.setTitle(getTitle());

        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.item_list);

        assert recyclerView != null;
        assert fab != null;

        onListResponse = new OnTaskComplete<TransmissionRequest<ListArgsResponse>>() {
            @Override
            public void onCompleted(TransmissionRequest<ListArgsResponse> data) {
                setupRecyclerView(data.arguments.getTorrents(), recyclerView);
            }

            @Override
            public void onError() {
                Snackbar.make(fab, "An error occurred :(", Snackbar.LENGTH_LONG)
                        // TODO: add detail error popup
                        //.setAction("See Details", null)
                        .show();
            }
        };

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListItems task = new ListItems(onListResponse);
                Snackbar.make(view, "Fetching data ...", Snackbar.LENGTH_LONG)
                        .show();
                task.execute();
            }
        });
        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull List<TransmissionElement> data, RecyclerView recyclerView) {
        recyclerView.setAdapter(new TorrentListAdapter(data, this));
    }

    @Override
    public void onTorrentClick(View view, TransmissionElement element) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ItemDetailFragment.ARG_ITEM_ID, element);
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Context context = view.getContext();
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, element);

            context.startActivity(intent);
        }
    }

    @Override
    public void onTorrentLongClick(View view, TransmissionElement element) {

    }
}
