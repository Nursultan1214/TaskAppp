package space.abdilazov.taskappp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import space.abdilazov.taskappp.R;
import space.abdilazov.taskappp.databinding.ItemBinding;
import space.abdilazov.taskappp.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> list;
    private ItemBinding binding;
    private OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public NewsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
        if (position % 2 == 0){
            binding.tvItem.setBackgroundResource(R.color.ser);
        }else {
            binding.tvItem.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void addItem(News news){
        list.add(0,news);
        notifyItemInserted(list.indexOf(news));
    }
    public void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
        }

        public void bind(News news) {
            binding.tvItem.setText(news.getTitle());
            itemView.setOnLongClickListener(v -> {
                onClick.onLongClick(getAdapterPosition());
                return true;
            });
        }
    }
    interface OnClick{
        void onLongClick(int position);
    }
}
