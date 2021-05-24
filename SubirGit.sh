#!/bin/sh

# Nombre
echo "¿Cual es tu nombre?"
read NOMBRE

# Pull
cd ~/Examenes-STA/
git pull

# Workspace
cd ~/Examenes-STA/$NOMBRE

# Commit message
echo "¿Que has hecho hoy?"
read COMMIT

# Git
git add -A
git commit -m "$COMMIT"
git push
