workflow "Build, Test and Distribute" {
  on = "push"
  resolves = "Publish Code Coverage"
}

action "Build" {
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  args = "./gradlew assembleDebug -PpreDexEnable=false"
}

action "Check" {
  needs = ["Build"]
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  args = "./gradlew testDebug jacocoTestReport checkstyle pmd jdepend lintDebug buildDashboard -PpreDexEnable=false"
}

action "Run UI Tests" {
  needs = ["Build"]
  uses = "vgaidarji/android-github-actions/emulator@v1.0.0"
}

action "Check", "Run UI Tests" {
  needs = ["Distribute"]
  uses = "vgaidarji/android-github-actions/build@v1.0.0"
  secrets = ["TOKEN"]
  args = "./gradlew coveralls -PpreDexEnable=false"
}
