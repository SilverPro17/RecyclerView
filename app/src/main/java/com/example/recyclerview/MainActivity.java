package com.example.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView; // Exibe a lista de estudantes
    private StudentAdapter adapter; // Adaptador para gerenciar a exibição no RecyclerView
    private List<Student> students; // Lista de estudantes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa a lista dos estudantes
        students = new ArrayList<>();

        // Configura o RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa o adaptador
        adapter = new StudentAdapter(this, students);
        recyclerView.setAdapter(adapter);

        // Configura o botão "Adicionar Estudante"
        findViewById(R.id.add_student_button).setOnClickListener(v -> {
            // Abre a AddStudentActivity para adicionar um novo estudante
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    // Método chamado quando a AddStudentActivity retorna um resultado
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se o resultado vem da AddStudentActivity
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Recupera os dados do estudante retornados pela AddStudentActivity
            String name = data.getStringExtra("name");
            String email = data.getStringExtra("email");
            String phone = data.getStringExtra("phone");
            String studentNumber = data.getStringExtra("studentNumber");
            String imageUriString = data.getStringExtra("imageUri");
            Uri imageUri = imageUriString != null ? Uri.parse(imageUriString) : null; // Converte o URI da imagem para o formato correto
            int studentIndex = data.getIntExtra("studentIndex", -1); // Obtém o índice do estudante, se estiver editando

            if (studentIndex == -1) {
                students.add(new Student(name, email, phone, imageUri, studentNumber));
            } else {
                // Atualiza os dados de um estudante existente
                Student student = students.get(studentIndex);
                student.setName(name);
                student.setEmail(email);
                student.setPhone(phone);
                student.setStudentNumber(studentNumber);
                student.setImageUri(imageUri);
            }

            adapter.notifyDataSetChanged();
        }
    }
}
