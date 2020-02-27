package com.example.graduationprojectgallery.helperClasses;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationprojectgallery.R;

import org.w3c.dom.Text;


public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    Context context;
    boolean sticky ;
    int headerOffset;
    String prevTxt =" ";

    SectionCallback sectionCallback;

    View headerView;
    TextView photoDate ;


    public RecyclerItemDecoration (Context context , int headerHieght , boolean isSticky ,SectionCallback sectionCallback)

    {
        this.context=context;
        this.headerOffset=headerHieght;
        this.sticky=isSticky;
        this.sectionCallback=sectionCallback;

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);
        if (sectionCallback.isSectionHeader(pos))
        {
            outRect.top=headerOffset;
        }

    }
    private View inflateHeader(RecyclerView recyclerView)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.row_section_header , recyclerView ,false);
        return view;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (headerView==null)
        {
            headerView=inflateHeader(parent);
            photoDate=(TextView)headerView.findViewById(R.id.date_header);

            fixLayoutSize(headerView , parent);
        }

        for (int i =0 ; i<parent.getChildCount() ; i++)
        {
            View child = parent.getChildAt(i);
            int childPos = parent.getChildAdapterPosition(child);
            String title =sectionCallback.getSetionHeader(i);

            if(!prevTxt.equalsIgnoreCase(title) || sectionCallback.isSectionHeader(childPos))
            {
                 drawHeader(c,child , headerView);
                 prevTxt=title;
            }

        }


    }

    private void drawHeader(Canvas c, View child, View headerView) {
        c.save();
        if (sticky)
        {
           c.translate(0 , Math.max(0 , child.getTop()-headerView.getHeight()));
        }
        else
            {
                c.translate(0 , child.getTop()-headerView.getHeight());

            }

        headerView.draw(c);
        c.restore();
    }

    private void fixLayoutSize(View headerView, RecyclerView parent) {

        int widthSpec=View.MeasureSpec.makeMeasureSpec(parent.getWidth() , View.MeasureSpec.EXACTLY);
        int hieghtSpec=View.MeasureSpec.makeMeasureSpec(parent.getHeight() , View.MeasureSpec.UNSPECIFIED);

        int childWidth= ViewGroup.getChildMeasureSpec(widthSpec , parent.getPaddingLeft()+parent.getPaddingRight() , headerView.getLayoutParams().width);
        int childHieght=ViewGroup.getChildMeasureSpec(hieghtSpec , parent.getPaddingBottom()+parent.getPaddingBottom() , headerView.getLayoutParams().height);

        headerView.measure(childWidth , childHieght);
        headerView.layout(0,0, headerView.getMeasuredWidth() , headerView.getMeasuredHeight());
    }

    public interface SectionCallback
    {
        public boolean isSectionHeader(int position);
        public String getSetionHeader(int position);
    }
}
