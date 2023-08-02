package com.project.emergencyaircraft;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SendNotificationFragment extends Fragment {

    private Spinner emergencyTypeSpinner;
    private Spinner eventSpinner;
    private TextView dateTextView;
    private TextView timeTextView;
    private Calendar calendar;

    public SendNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_notification, container, false);

        Button sendButton = view.findViewById(R.id.sendNotificationButton);
        emergencyTypeSpinner = view.findViewById(R.id.emergencyTypeSpinner);
        eventSpinner = view.findViewById(R.id.eventSpinner);
        dateTextView = view.findViewById(R.id.dateTextView);
        timeTextView = view.findViewById(R.id.timeTextView);

        // Set up the Spinners
        ArrayAdapter<CharSequence> emergencyAdapter = ArrayAdapter.createFromResource(
                requireContext(), R.array.emergency_types, android.R.layout.simple_spinner_item);
        emergencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emergencyTypeSpinner.setAdapter(emergencyAdapter);

        ArrayAdapter<CharSequence> eventAdapter = ArrayAdapter.createFromResource(
                requireContext(), R.array.event_types, android.R.layout.simple_spinner_item);
        eventAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner.setAdapter(eventAdapter);

        // Initialize Calendar instance
        calendar = Calendar.getInstance();

        // Set up the OnClickListener for dateTextView to open the DatePicker
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Set up the OnClickListener for timeTextView to open the TimePicker
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emergencyType = emergencyTypeSpinner.getSelectedItem().toString();
                String event = eventSpinner.getSelectedItem().toString();

                // Format the selected date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String selectedDate = dateFormat.format(calendar.getTime());
                String selectedTime = timeFormat.format(calendar.getTime());

                // Display the selected date, time, and other inputs
                String message = "Emergency Type: " + emergencyType + "\nEvent: " + event +
                        "\nSelected Date: " + selectedDate + "\nSelected Time: " + selectedTime;
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
        timePickerDialog.show();
    }

    // DateSetListener for DatePicker
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // Update the dateTextView with the selected date
            updateDateTextView();
        }
    };

    // TimeSetListener for TimePicker
    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            // Update the timeTextView with the selected time
            updateTimeTextView();
        }
    };

    private void updateDateTextView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String selectedDate = dateFormat.format(calendar.getTime());
        dateTextView.setText(selectedDate);
    }

    private void updateTimeTextView() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String selectedTime = timeFormat.format(calendar.getTime());
        timeTextView.setText(selectedTime);
    }
}
