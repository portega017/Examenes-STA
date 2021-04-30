#!/bin/sh
# Nombre
echo "¿Cual es tu nombre?"
read NOMBRE

# Git initial configuration
cd ~
git config --global credential.helper store
git clone https://github.com/portega017/Examenes-STA.git

# Workspace
mkdir ~/Examenes-STA/$NOMBRE
