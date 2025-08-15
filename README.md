# Hospital Management App - Android App

## Overview
The Hospital Management System (HMS) Android app allows patients and admins to manage hospital operations digitally.
- Patients can search doctors, view details, and book appointments.
- Admins can manage doctor profiles, appointments, and patient records.
- Uses Firebase Realtime Database and Firebase Storage for backend.

 ## Tech Stack & Libraries
- Language: Java
- UI: XML (Android Views), Material Design Components
- Backend: Firebase Realtime Database 
- RecyclerView & Adapters for dynamic lists

## Features Completed (Till Now)
1. Authentication & Profiles
- Separate login and registration for:
    - Admin
    - Patient
- Profile creation stored in Firebase.

2. Firebase Realtime Database Setup
- Users Node → Stores profile data (role, name, specialization, timings, etc.).
- Images stored in Firebase Storage (doctor profile photos, etc.).

3. Patient Features
- Patient dashboard with Toolbar Search Icon.
- Search doctors by name or specialization.
- RecyclerView list of doctors fetched from Firebase.
- Doctor click → Doctor Details Page with option to book appointment.

4. Admin Features
- Manage doctor profiles.
- View appointments.
- Upload/update doctor images.

## Features in Progress / Future Work
- For Patients
  - Appointment booking form + confirmation system.
  - Appointment history page.

- For Admin
  - Approve/reject appointments.
  - Edit doctor profiles.
  - View all patients in the system.

- General
  - Firebase Authentication integration (email/password login).
  - Profile picture upload from gallery/camera.
  - Push notifications for appointments
 
## Project Folder Structure
```
app/
 ├── java/
 │    └── com.rzjaffery.hms/
 │         ├── ui/              # Activities & UI logic
 │         │    ├── admin/
 │         │    ├── patient/
 │         │    └── doctor/
 │         ├── data/model/      # Data models
 │         ├── utils/           # Adapters, helpers
 │         └── MainActivity.java
 ├── res/
 │    ├── layout/               # XML layouts
 │    ├── drawable/             # Icons & backgrounds
 │    └── values/               # Strings, colors, styles
 └── AndroidManifest.xml

```

## How to Run
- Clone the repo:
  ```
  git clone https://github.com/rzjaffery/Hospital-Management-App.git
- Open in Android Studio.
- Place google-services.json inside app/ folder.
- Sync Gradle.
- Run the app on an emulator or real device.

## Contributors
- **Rayyan Zafar** — Project Owner & Developer
- **ChatGPT** — Technical Assistant

