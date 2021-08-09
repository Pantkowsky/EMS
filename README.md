# Requirements

The gradle plugin used in this project required `Java 11` for it to be compiled. If you currently don't have `Java 11` installed on your machine 
(assuming you're using a linux distro) you can:
```
sudo apt-get update
sudo apt-get install openjdk-11-jdk
```
Additionally, make sure you have the latest android gradle plugin installed.

If you are running this app on an emulator, make sure you use the emulator
that is `api 26` and up, as this is the minimum required sdk version.

To launch this project, clone/download this repository to your machine and open it from `Android Studio`.
When `gradle` finishes indexing, `SHIFT + F10` to launch it.

# How to

Add Button - click to create a new employee (_ADD_ operation)

![image](https://user-images.githubusercontent.com/9194725/128772348-ac1f2f1d-7064-4cf8-8c79-bf5e49c71f4f.png)

Delete Button - click to delete an employee (_REMOVE_ operation)

![image](https://user-images.githubusercontent.com/9194725/128772630-5cea5454-6297-4f3a-9dad-076fcfc78e8a.png)

Give Raise Button - click to give an employee a raise (_UPDATE_ operation)

![image](https://user-images.githubusercontent.com/9194725/128772726-12c2160a-a790-4f68-87d1-a8d70b9392c0.png)

_GET_ operation is implemented implicitly and used for displaying all employees on the screen

# Modularized project structure

Project is made from standalone android modules, as shown below:
```
.
-> app 
-> buildSrc
-> domain
-> features
   -> base
   -> roster
```
where:
- `app` module contains the `launch options` component as well as `splash` activity
- `buildSrc` module contains the project and build configuration logic. 
- `domain` module contains the database with its DAOs and business logic
- `features` subdirectory contains all the standalone feature modules. Currently it contains `base` 
  which contains all base android components for feature development, as well as `roster` which is the 
  feature visible on the screen after app launch
  
# Code Style

This project leverages two linting tools: 
- <a href="https://ktlint.github.io/">ktlint</a>
- <a href="https://github.com/detekt/detekt">detekt</a>

In favor of convenience, `./linters/` directory contains a `stylecheck.sh` script, which run both
`ktlint` and `detekt` and fails early if any issues arise, otherwise completes successfully. To run it, open the 
terminal in `./linters/` directory and run:
```
chmod +x stylecheck.sh
./stylecheck.sh
```

# Architecture

Project is built with clean architecture in mind, using mvi pattern (<a href="https://hannesdorfmann.com/android/mosby3-mvi-1/">great link</a>, <a href="https://medium.com/swlh/mvi-architecture-with-android-fcde123e3c4a">another great link</a>) as the underlying design pattern: The chart below represents the
architecture in more detail:

_*keep in mind that this project does not contain an Api Client which does appear on the diagram below_

![image](https://user-images.githubusercontent.com/9194725/128768454-edd6fb8a-082a-4ae3-9841-0aa356b969c1.png)

The data flow from database access until ui rendering is done via below model conversion:
```
Employee > EmployeeModel > EmployeeData > ViewState
```

# frameworks

- <a href="https://developer.android.com/training/data-storage/room">Room</a> for local storage
- <a href="https://developer.android.com/guide/navigation/navigation-getting-started">Navigation component</a> for feature navigation
- <a href="http://reactivex.io/">ReactiveX</a> for obvious reasons
- <a href="https://junit.org/junit5/docs/current/user-guide/">Junit5</a> for testing
- <a href="https://mockk.io/ANDROID.html">Mockk</a> for mocking
- <a href="https://insert-koin.io/">Koin</a> for dependency injection
- <a href="https://developer.android.com/training/constraint-layout/motionlayout">MotionLayout</a> for fluid layout animations
