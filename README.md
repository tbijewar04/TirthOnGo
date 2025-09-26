# TirthOnGo
TirthOnGo is a spiritual tour booking platform built with JavaFX and Firebase, designed to help users explore, plan, and book religious and spiritual destinations with ease.

## Tech stack
- Java 17+
- JavaFX
- Gradle (or Maven)
- Firebase (Auth / Firestore / Storage)

## Prerequisites
- JDK 17 installed
- Gradle wrapper (or Maven)
- Firebase project & service account (stored in CI secrets)

## Run (Gradle)
./gradlew run

## How to add Firebase credentials
- DO NOT commit serviceAccountKey.json.
- For local dev: put it in `config/serviceAccountKey.json` and add to `.gitignore`.
- For CI: add the service account as a GitHub secret (see .github/workflows).

## Features
- Browse and book spiritual tour packages
- User authentication with Firebase
- Secure data storage using Firestore
- Simple and clean JavaFX UI
