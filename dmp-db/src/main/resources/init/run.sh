createuser -U postgres -d -P -S -R -l -i dmp
# Entrer le mot de passe

createdb -E UTF-8 -U dmp -h 127.0.0.1 dmp