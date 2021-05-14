#!/bin/sh

# Salto de linea dependiendo de tu versión de echo
if [ "`echo -n`" = "-n" ]; then
  n=""
  c="\c"
else
  n="-n"
  c=""
fi

# Nombre y correo
echo $n "¿Que nombre quieres ponerle a tu carpeta de GitHub?"
read NOMBRE

echo $n "¿Cual es tu nombre completo?"
read NOMBRECOMPLETO

echo $n "¿Cual es tu correo de GitHub?"
read CORREO

# Git initial configuration
cd ~
sudo apt install git -y
git config --global credential.helper store
git config --global user.name "$NOMBRECOMPLETO"
git config --global user.email "$CORREO"

# Workspace
mkdir ~/N/$NOMBRE
