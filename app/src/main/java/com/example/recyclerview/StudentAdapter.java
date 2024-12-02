package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> students;

    // Construtor para inicializar o adaptador com o contexto e a lista de estudantes
    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    // Método chamado para inflar o layout de cada item do RecyclerView
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item (student_item.xml) e cria um ViewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    // Método chamado para vincular os dados de um estudante ao ViewHolder
    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        // Obtém o estudante na posição atual
        Student student = students.get(position);

        // Define os valores dos campos do layout com os dados do estudante
        holder.name.setText(student.getName());
        holder.email.setText(student.getEmail());
        holder.phone.setText(student.getPhone());
        holder.studentNumber.setText(student.getStudentNumber());
        holder.image.setImageURI(student.getImageUri());

        // Configura a clique no item do RecyclerView
        holder.itemView.setOnClickListener(v -> {
            // Cria um Intent para abrir a AddStudentActivity com os dados do estudante
            Intent intent = new Intent(context, AddStudentActivity.class);
            intent.putExtra("name", student.getName());
            intent.putExtra("email", student.getEmail());
            intent.putExtra("phone", student.getPhone());
            intent.putExtra("studentNumber", student.getStudentNumber());
            intent.putExtra("imageUri", student.getImageUri());
            intent.putExtra("studentIndex", position);

            ((MainActivity) context).startActivityForResult(intent, 1);
        });
    }

    // Retorna o número total de itens na lista
    @Override
    public int getItemCount() {
        return students.size();
    }

    // Classe ViewHolder que mantém as referências aos elementos do layout de cada item
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, email, phone, studentNumber; 

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            // Vincula os elementos do layout aos campos da classe
            image = itemView.findViewById(R.id.student_image);
            name = itemView.findViewById(R.id.student_name);
            email = itemView.findViewById(R.id.student_email);
            phone = itemView.findViewById(R.id.student_phone);
            studentNumber = itemView.findViewById(R.id.student_number);
        }
    }
}


