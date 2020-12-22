echo "Starting container"
service postgresql start
service apache2 start
sudo -i -u postgres bash << EOF
echo "In"
createdb db_cliente
psql -c "ALTER USER postgres PASSWORD 'sys';"
EOF
echo "Out"
whoami
cd app
mvn spring-boot:run
echo "Container Started"
