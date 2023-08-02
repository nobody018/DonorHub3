package com.example.donorhub.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.concurrent.futures.CallbackToFutureAdapter;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donorhub.R;
import com.example.donorhub.database.RequestHelperClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class RequestUpload extends AppCompatActivity {
    String phoneNo;
    TextView ngo_name, description, purpose;
    RadioGroup category;

    RadioButton selectedCategory;
    ImageView ngo_image, upload_image;
    RelativeLayout relativeLayout;
    Button button;

    Uri ImageUri;
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_upload);

        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        ngo_name = findViewById(R.id.name_ngo);
        description = findViewById(R.id.description);
        purpose = findViewById(R.id.purpose);
        category = findViewById(R.id.radioGroup2);
        ngo_image = findViewById(R.id.upload_image);
        upload_image = findViewById(R.id.img_upload_btn);
        relativeLayout = findViewById(R.id.relative);
        button = findViewById(R.id.upload_btn);
        selectedCategory = findViewById(category.getCheckedRadioButtonId());

        phoneNo = getIntent().getStringExtra("phoneNo");

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
                relativeLayout.setVisibility(View.VISIBLE);
                upload_image.setVisibility(View.GONE);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageReference reference = firebaseStorage.getReference("Request").child(System.currentTimeMillis()+"");

                reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                RequestHelperClass requestHelperClass = new RequestHelperClass();
                                requestHelperClass.setDescription(uri.toString());

                                requestHelperClass.setNgo_name(ngo_name.getText().toString());
                                requestHelperClass.setDescription(description.getText().toString());
                                //requestHelperClass.setCategory(selectedCategory.getText().toString());
                                requestHelperClass.setPurpose(purpose.getText().toString());

                                database.getReference().child("Request").push().setValue(requestHelperClass)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(RequestUpload.this, "Request uploaded successfully", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(RequestUpload.this, "error", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                });

            }
        });


    }

    private void UploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent =  new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(RequestUpload.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK){
            ImageUri = data.getData();
            ngo_image.setImageURI(ImageUri);
        }
    }
}