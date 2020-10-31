package ru.hse.lection03.presentationlayer.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.hse.lection03.R;
import ru.hse.lection03.objects.Droid;

public class DroidAdapter extends RecyclerView.Adapter<DroidViewHolder> {
    protected final DroidViewHolder.IListener mListener;
    protected final List<Droid> mData;

    public DroidAdapter(List<Droid> data, DroidViewHolder.IListener listener) {
        mListener = listener;
        mData = data;
    }


    // Инициализируем ViewHolder
    @NonNull
    @Override
    public DroidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Получаем инфлейтер и создаем нужный layout в зависимости от ViewType
        View itemView;
        if (viewType == R.layout.item_droid) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_droid, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_row, parent, false);
        }
        return new DroidViewHolder(itemView, mListener);
    }

    // Вставляем данные во ViewHolder
    @Override
    public void onBindViewHolder(@NonNull DroidViewHolder holder, int position) {
        if(position == mData.size()) {
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText( "Button Clicked", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            final Droid item = mData.get(position);
            holder.bind(item);
        }
    }

    // Переприсвыиваем элемент, если он последний
    @Override
    public int getItemViewType(int position) {
        return (position == mData.size()) ? R.layout.load_more_row : R.layout.item_droid;
    }
    // Размер данных, уваличенный на елиницу, чтобы дообавить кнопку
    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }
}