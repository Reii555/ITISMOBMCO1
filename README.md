# WormBoxd

---

## Prerequisites

Before running the project with the Firebase Emulator, ensure you have the following installed on your machine:

1. **Node.js** (v18 or higher): [Download Node.js](https://nodejs.org/)
2. **Java Development Kit (JDK)** (JDK 11 or 17+): Required to run Firebase Emulators.
3. **Android Studio** (with Android SDK & an Android Virtual Device / Physical Device).
4. **Firebase CLI**:
   Install globally via terminal/command prompt:
   ```bash
   npm install -g firebase-tools
   ```

---

##  Setup & Configuration

### Step 1: Configure `local.properties`

The app reads `FIREBASE_EMULATOR_HOST` from your project's `local.properties` file (`D:/Fallen/Projects/Programs/ITISMOBMCO1/local.properties`).

1. Open `local.properties` in the root of the project.
2. Add the following line:

   * **If testing on the Android Studio Emulator (Default):**
     ```properties
     FIREBASE_EMULATOR_HOST=10.0.2.2
     ```
     *(Note: `10.0.2.2` is the special alias used by the Android Emulator to connect to your host machine's `localhost`)*

   * **If testing on a Physical Android Device (connected to same Wi-Fi):**
     ```properties
     FIREBASE_EMULATOR_HOST=X.X.X.X
     ```
     *(Replace `X.X.X.X` with your computer's local IP address on your Wi-Fi network)*

---

### Step 2: Start the Firebase Auth Emulator

1. Open a terminal / command prompt in the root directory of this project.
2. Start the Firebase Auth emulator:
   ```bash
   firebase emulators:start
   ```
3. Once started, you will see output similar to this:
   ```text
   ┌─────────────────────────────────────────────────────────────┐
   │ ✔  Emulators submitted and initialized successfully         │
   │ ✔  Authentication Emulator running at http://127.0.0.1:9099 │
   │ ✔  Emulator UI running at http://127.0.0.1:4000              │
   └─────────────────────────────────────────────────────────────┘
   ```
4. Open your browser and navigate to **[http://localhost:4000](http://localhost:4000)** to access the **Firebase Emulator Suite UI**.

---

## Testing the Application

### Step 3: Run the Android App

1. Launch **Android Studio**.
2. Make sure the build variant is set to `debug` (default).
3. Run the app on your Android Virtual Device or connected physical device.

---

### Step 4: Perform Authentication Tests

#### Test 1: User Registration
1. On the app's home/login screen, click **"Sign up here."**
2. Fill in the registration form:
   - **Username**: e.g. `bookworm99`
   - **Email Address**: e.g. `test@example.com`
   - **Password**: `password123`
   - **Confirm Password**: `password123`
3. Click **Sign Up**.
4. You should see a Toast message: `"Registration Successful"`.

#### Test 2: Verify in Firebase Emulator UI
1. Open your web browser to **[http://localhost:4000/auth](http://localhost:4000/auth)**.
2. You should see `test@example.com` listed under the **Authentication** tab with its User UID and Display Name (`bookworm99`).

#### Test 3: User Login
1. On the login screen, enter:
   - **Email Address**: `test@example.com`
   - **Password**: `password123`
2. Click **Login**.
3. You will see a Toast message `"Login Successful"` and be redirected to `HomeActivity`.

#### Test 4: Automatic Login / Session Persistence
1. Close/kill the app on the device.
2. Re-open the app.
3. The app will detect the active Firebase session in `onStart()` and automatically redirect you to `HomeActivity` without requiring re-login.

---

## Troubleshooting

* **Registration/Login fails with a network/timeout error:**
  - Verify that `firebase emulators:start` is actively running in your terminal.
  - Check your `local.properties` file: ensure `FIREBASE_EMULATOR_HOST=10.0.2.2` is present and sync the Gradle project.
* **"Passwords do not match" or empty field errors:**
  - Double check that all fields are filled out and both password fields match.
* **Resetting Test Users:**
  - Stop the emulator in your terminal using `Ctrl + C` and restart it (`firebase emulators:start`). By default, emulator state resets when restarted unless export/import flags are used.
