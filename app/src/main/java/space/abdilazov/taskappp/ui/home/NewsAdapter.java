package space.abdilazov.taskappp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import space.abdilazov.taskappp.R;
import space.abdilazov.taskappp.databinding.ItemBinding;
import space.abdilazov.taskappp.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> list;
    private OnClick onClick;
    private ItemBinding binding;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public NewsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
        if (position % 2 == 0) {
            binding.tvItem.setBackgroundResource(R.color.ser);
        } else {
            binding.tvItem.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(list.indexOf(news));
    }

    public void addItems(List<News> news) {
        this.list.clear();
        list.addAll(news);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
    public News getItem(int pos){
        return list.get(pos);
    }

    public void updateItem(News news) {
        int index = list.indexOf(news);
        list.set(index,news);
        notifyItemChanged(index);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemBinding binding;

        public ViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(News news) {
            binding.tvItem.setText(news.getTitle());
            itemView.setOnLongClickListener(v -> {
                onClick.onLongClick(getAdapterPosition());
                return true;
            });
            itemView.setOnClickListener(v -> onClick.onClick(getAdapterPosition()));
        }
    }
    interface OnClick{
        void onLongClick(int position);
        void onClick(int pos);
    }
}
