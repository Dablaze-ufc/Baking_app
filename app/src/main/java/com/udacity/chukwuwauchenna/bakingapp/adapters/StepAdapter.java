package com.udacity.chukwuwauchenna.bakingapp.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.udacity.chukwuwauchenna.bakingapp.R;
import com.udacity.chukwuwauchenna.bakingapp.model.Step;

import java.util.List;

/**
 * Created by ChukwuwaUchenna
 */
public class StepAdapter extends
        RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private List<Step> steps;
    private OnStepItemClickListener onItemClickListener;

    public StepAdapter(List<Step> steps,
                       OnStepItemClickListener onItemClickListener) {
        this.steps = steps;
        this.onItemClickListener = onItemClickListener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }

        void bind(Step step, OnStepItemClickListener listener) {
            TextView stepText = itemView.findViewById(R.id.step_text_name);
            ImageView thumbnail = itemView.findViewById(R.id.image_thumbnail);
            stepText.setText(step.getShortDescription());
            if (TextUtils.isEmpty(step.getThumbnailURL())){
                thumbnail.setImageResource(R.drawable.ic_cupcake);

            }else {
            Picasso.get().load(step.getThumbnailURL())
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.recipe_image)
                    .into(thumbnail);

           }
            stepText.setOnClickListener(v -> listener.onStepItemClicked(step));
        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_layout_steps, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Step item = steps.get(position);
        holder.bind(item, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        if (steps == null)
            return 0;
        else
            return steps.size();
    }

    public interface OnStepItemClickListener {
        void onStepItemClicked(Step step);
    }
}