<p align="center">
  <img width="200px" src="assets/nozama-logo.png" />
</p>

# Nozama

Welcome to Nozama!

Nozama is a world-class e-commerce platform that allows users to browse and
purchase products from a wide range of categories. Users can also leave reviews
on products they have purchased, and view reviews left by other users.

This repo contains the source code for the Nozama backend, which is built using
Java and Javalin. It is an API which allows developers to interact
with the Nozama database, so they can build their own applications on top of it.

> [!CAUTION]
>
> Please note that this repo is for learning purposes. It contains plenty of bad
> security practices, and should not be used in production.

## Getting started

1. Make sure your machine is set up according to the instructions with

   - [bash](https://tech-docs.corndel.com/bash/)

   - [vscode](https://tech-docs.corndel.com/vscode/)

   - [git](https://tech-docs.corndel.com/git/)

   - [java and maven](https://tech-docs.corndel.com/java/installation.html)

1. Clone the repository (i.e. download it), so you have a copy on your machine.

1. Once cloned, open a terminal in the project folder

1. Run the command

   ```bash
   ./mvnw clean && ./mvnw compile && ./mvnw exec:java -Dexec.mainClass=com.corndel.nozama.App
   ```

   and visit [http://localhost:8080/](http://localhost:8080/) in your browser.

## Exercises

Take a look in the `exercises` directory for information on the deep dives.

Everything you need (including documentation URLs) is in the `exercises` folder.

## Workshops

Read `CONTRIBUTING.md` to understand how to work on this project - it contains
all the information you will need to work on Nozama.
