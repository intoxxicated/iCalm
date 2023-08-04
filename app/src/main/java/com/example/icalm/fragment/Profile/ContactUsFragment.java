package com.example.icalm.fragment.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.icalm.R;

public class ContactUsFragment extends Fragment {

    private EditText subjectEditText, messageEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);

        subjectEditText = rootView.findViewById(R.id.subjectEditText);
        messageEditText = rootView.findViewById(R.id.messageEditText);

        Button sendButton = rootView.findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        return rootView;
    }

    private void sendEmail() {
        String subject = subjectEditText.getText().toString();
        String message = messageEditText.getText().toString();

        String[] recipients = {"17ankita1711@gmail.com","prateekt445@gmail.com","shweta88750@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL,recipients );
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}

