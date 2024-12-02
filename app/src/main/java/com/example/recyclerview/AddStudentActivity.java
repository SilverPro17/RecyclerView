package com.example.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    private Uri imageUri; // Armazena o URI da imagem selecionada
    private boolean isEditMode = false; // Indica se estamos no modo de edição
    private int studentIndex = -1; // Armazena o índice do estudante sendo editado, se aplicável

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Referencias aos elementos do layout
        ImageView studentImage = findViewById(R.id.student_image);
        Button selectImageButton = findViewById(R.id.select_image_button);
        EditText nameInput = findViewById(R.id.name_input);
        EditText emailInput = findViewById(R.id.email_input);
        EditText phoneInput = findViewById(R.id.phone_input);
        EditText studentNumberInput = findViewById(R.id.student_number_input);
        Button saveButton = findViewById(R.id.save_student_button);

        // Verifica se os dados de um estudante foram enviados para edição
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("studentIndex")) {
            isEditMode = true; // Ativa o modo de edição
            studentIndex = intent.getIntExtra("studentIndex", -1); // Recupera o índice do estudante

            // Preenche os campos com os dados recebidos
            nameInput.setText(intent.getStringExtra("name"));
            emailInput.setText(intent.getStringExtra("email"));
            phoneInput.setText(intent.getStringExtra("phone"));
            studentNumberInput.setText(intent.getStringExtra("studentNumber"));

            // Configura a image, se fornecida
            String imageUriString = intent.getStringExtra("imageUri");
            if (imageUriString != null) {
                imageUri = Uri.parse(imageUriString); // Converte a string para URI
                studentImage.setImageURI(imageUri); // Define a imagem no ImageView
            }
        }

        // Lógica para selecao daimagem da galeria
        selectImageButton.setOnClickListener(v -> {
            Intent pickImageIntent = new Intent(Intent.ACTION_PICK); // Ação para selecionar uma imagem
            pickImageIntent.setType("image/*"); // Filtro para imagens
            startActivityForResult(pickImageIntent, 1);
        });

        // Lógica para guardar os dados do estudante
        saveButton.setOnClickListener(v -> {
            // Prepara um Intent para retornar os dados do estudante
            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", nameInput.getText().toString());
            resultIntent.putExtra("email", emailInput.getText().toString());
            resultIntent.putExtra("phone", phoneInput.getText().toString());
            resultIntent.putExtra("studentNumber", studentNumberInput.getText().toString());
            if (imageUri != null) {
                resultIntent.putExtra("imageUri", imageUri.toString());
            }
            resultIntent.putExtra("studentIndex", studentIndex); // Índice do estudante (usado na edição)
            setResult(RESULT_OK, resultIntent); // Retorna os dados para a MainActivity
            finish();
        });
    }

    // Método chamada quando a imagem é selecionada
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se o resultado é da seleção de imagem e se está tudo OK
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData(); // Recupera o URI da imagem selecionado
            ImageView studentImage = findViewById(R.id.student_image);
            studentImage.setImageURI(imageUri); // Define a imagem selecionada no ImageView
        }
    }
}
