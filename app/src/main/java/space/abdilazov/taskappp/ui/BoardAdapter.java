package space.abdilazov.taskappp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import space.abdilazov.taskappp.R;
import space.abdilazov.taskappp.databinding.ItemBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private onClicker click;
    private int[] image = new int[]{R.drawable.ic_apple,R.drawable.ic_samsung,R.drawable.ic_xiaomi};
    private String[] title = new String[]{"Apple — американская корпорация, производитель персональных и планшетных компьютеров, аудиоплееров, смартфонов, программного обеспечения. Один из пионеров в области персональных компьютеров и современных многозадачных операционных систем с графическим интерфейсом. Штаб-квартира — в Купертино, штат Калифорния.","Samsung Group — южнокорейская группа компаний, один из крупнейших чеболей, основанный в 1938 году. На мировом рынке известен как производитель высокотехнологичных компонентов, включая полноцикловое производство интегральных микросхем, телекоммуникационного оборудования, бытовой техники, аудио- и видеоустройств.","Xiaomi Corporation — китайская корпорация, основанная Лэй Цзюнем в 2010 году. В 2021 году стала мировым лидером по объёму производства смартфонов. Также производит бытовую технику, планшеты, смарт-часы, электросамокаты и многое другое. С 2018 года является публичной компанией."};
    private ItemBoardBinding binding;

    public void setClick(onClicker click) {
        this.click = click;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(int position) {
            binding.imageView.setImageResource(image[position]);
            binding.textView.setText(title[position]);
            binding.button.setOnClickListener(v -> click.click());
            if (position == title.length - 1){
                binding.button.setVisibility(View.VISIBLE);
            }else {
                binding.button.setVisibility(View.GONE);
            }
        }
    }
    public interface onClicker {
        void click();
    }
}
