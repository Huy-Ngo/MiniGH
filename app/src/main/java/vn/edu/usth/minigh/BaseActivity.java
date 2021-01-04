// Base activity with drawer
package vn.edu.usth.minigh;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


public class BaseActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    public BaseActivity(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerLayout = findViewById(R.id.drawer_layout);

        TextView follower = (TextView) findViewById(R.id.follower_total);
        follower.setText(Html.fromHtml("<b>"+5+"</b> Follower"));

        TextView following = (TextView) findViewById(R.id.following_total);
        following.setText((Html.fromHtml("<b>"+5+"</b> Following")));
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void openDrawer(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void closeDrawer(View view) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void redirectActivity(Class aclass) {
        Intent intent = new Intent(this, aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void clickProfile(View view) {
        if (this.getClass() != ProfileActivity.class) {
            redirectActivity(ProfileActivity.class);
        }
    }

    public void clickRepo(View view) {
        if (this.getClass() != RepoActivity.class) {
            redirectActivity(RepoActivity.class);
        }
    }
    public void clickIssue(View view){
        if (this.getClass() != IssueActivity.class) {
            redirectActivity(IssueActivity.class);
        }
    }
    public void clickPRs(View view){
        if (this.getClass() != PRActivity.class) {
            redirectActivity(PRActivity.class);
        }
    }

    public void logout(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SessionManager manager = new SessionManager(
                    getApplicationContext());
                manager.removeSession();
                redirectActivity(AuthActivity.class);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
