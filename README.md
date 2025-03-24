👩‍⚕️ Caregiver App
===================

This is a **prototype Android app** designed for caregivers. It remotely monitors a paired **UserSideApp**, which is used by the care receiver (the person being monitored). The two apps communicate through the **Firebase Cloud**, allowing caregivers to receive updates, data, and alerts in real-time.

This project is a part of a connected health monitoring system and is ideal for testing or demonstration purposes.

* * * * *

🚀 Features
-----------

-   Real-time remote monitoring of the care receiver

-   Firebase integration for seamless data transfer

-   Simple UI for easy access to key patient information

* * * * *

🧩 System Requirements
----------------------

-   Android Studio (Electric Eel or newer recommended)

-   Android SDK version **33+**

-   Java JDK **11 or newer**

-   A Firebase project (shared with UserSideApp)

-   An Android device or emulator running Android **10 (API 29)** or later

* * * * *

🛠️ How to Set Up and Run the App (Step-by-Step)
------------------------------------------------

This guide is beginner-friendly and assumes no prior experience with Android Studio or Firebase.

### 1\. 📦 Install Android Studio

-   Download and install [Android Studio](https://developer.android.com/studio)

-   Open it and allow it to install all necessary components (SDK, Emulator tools, etc.)

### 2\. 📁 Clone This Repository

You can download the project files either via Git or manually:

#### Option A: Clone with Git

```
git clone https://github.com/Adolfo-David-Romero/Caregiver.git
```

#### Option B: Download ZIP

-   Click the green **Code** button on the repo

-   Choose **Download ZIP**

-   Extract it to your desired folder

### 3\. 📂 Open the Project in Android Studio

-   Open Android Studio

-   Click **"Open an Existing Project"**

-   Navigate to the folder where you downloaded or extracted the `Caregiver` repo

-   Select the **Caregiver** folder

Android Studio will load and **start syncing the Gradle files** (this might take a few minutes).

### 4\. 🔥 Connect to Firebase

To connect your app to Firebase:

#### A. Create a Firebase Project

1.  Go to Firebase Console

2.  Click **Add project**

3.  Follow the setup steps and give it a name (e.g., `CaregiverSystem`)

#### B. Add Android App to Firebase

1.  Once your project is created, click **Add App > Android**

2.  Enter the Android package name (you can find this in the app-level `build.gradle`)

3.  Download the `google-services.json` file provided by Firebase

4.  Place the `google-services.json` file in the **app/** directory of your project

#### C. Enable Firebase Services

-   In the Firebase console, go to **Firestore Database** and create a database in test mode

-   Enable any other services (e.g., Authentication, Realtime Database, Storage) as needed, depending on how the UserSideApp is configured

> 💡 You must use the **same Firebase project** for both Caregiver and UserSideApp.

### 5\. 🔧 Final Setup

-   In Android Studio, let Gradle sync again after adding `google-services.json`

-   If prompted, install any missing SDK components

-   Build the app by selecting **Build > Make Project**

### 6\. ▶️ Run the App

-   Plug in your Android device or start an emulator

-   Click the green **Run** button (or select **Run > Run 'app'**)

* * * * *

🔗 Connected App: UserSideApp
-----------------------------

Make sure the **UserSideApp** (used by the person being monitored) is running and connected to the same Firebase instance.

You can find the UserSideApp here: [UserSideApp](https://github.com/Adolfo-David-Romero/UserSideApp.git)

* * * * *

🧑‍💻 For Developers
--------------------

-   App is written in **Kotlin**

-   Uses **MVVM architecture**

-   Connected to **Firebase Firestore** and **Realtime Database**

* * * * *

❓Troubleshooting
----------------

-   **Gradle build fails**: Make sure all dependencies are downloaded, and you have internet access

-   **Firebase not working**: Double-check that your `google-services.json` is in the correct location and is from the correct Firebase project

-   **App crashing**: Use the **Logcat** tab in Android Studio to view crash logs and debug

* * * * *

📬 Contact
----------

If you have questions or want to collaborate:

-   GitHub: [Adolfo-David-Romero](https://github.com/Adolfo-David-Romero)
