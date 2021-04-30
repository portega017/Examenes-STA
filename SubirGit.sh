#!/bin/sh
# Nombre
echo "¿Cual es tu nombre?"
read NOMBRE

# Workspace
cd ~/Examenes-STA/$NOMBRE

# Commit message
echo "¿Que has hecho hoy?"
read COMMIT

# Git
git pull
git add -A
git commit -m "$COMMIT"
git push
