#!/bin/bash 

#Variables
USER=openerp
#PORT=9999 ###port optionnel, activer si il y a un pgpool
HOST=localhost
ENCO=UTF8
TEMPL=template0
BASE=$1 #nom de la nouvelle base de données
DIR_DUMP_BASE=$2 #chemin du dump de la base de données
BASE_TINY=$3 #nom de la nouvelle base de données tiny_autologin
DIR_DUMP_TINY=$4 #nom du dump tiny_autologin

####Création e la nouvelle base de données####
su - postgres -c "createdb $BASE -U $USER -h $HOST -E $ENCO -T $TEMPL" #-p $PORT
####Remplir de la base de données####
su - postgres -c "psql -d $BASE < $DIR_DUMP_BASE"
####Modification des noms d'utilisateurs####
su - postgres -c "psql -c \"UPDATE res_users SET login= login || '_test';\" $BASE" 
####Copie des modifications effectué dans un fichie temporaire####
su - postgres -c "psql -c \"SELECT login FROM res_users;\" $BASE" > /tmp/fichier_temporaire
####Présentation du fichier pour-injection des données dans une tab tiny_autologin####
sed -i".bak" '1,2d' /tmp/fichier_temporaire
sed -i".bak" '/rows/d' /tmp/fichier_temporaire
sed -i".bak" '/^$/d' /tmp/fichier_temporaire

for LINE in `cat /tmp/fichier_temporaire`; 
	do
	echo "insert into users ( nom,db ) values ( '"$LINE"' , '"$BASE"' );" >> /tmp/fichier_temporaire.sql
	done

	####!!!Optionnel seulement si la base tiny_autologin n'existe pas!!!####
            #--------------------------------------------------------------#
####Création de la base tiny_autlogin#### 
su - postgres -c "createdb $BASE_TINY -U $USER -h $HOST -E $ENCO -T $TEMPL" #-p $PORT
####Remplir de la base de donnée tiny_autologin####
su - postgres -c "psql -d $BASE_TINY < $DIR_DUMP_TINY"
            #--------------------------------------------------------------#

####Modification de la base tiny_autologin####
while read line 
	do
	su - postgres -c "psql -c \"$line\" $BASE_TINY"
	done < /tmp/fichier_temporaire.sql 
####Nettoyage####
rm /tmp/fichier_temporaire
rm /tmp/fichier_temporaire.bak
rm /tmp/fichier_temporaire.sql

