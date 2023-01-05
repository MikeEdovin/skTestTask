dropdb --if-exists sk_example_db
dropuser --if-exists sk_example_user
createuser sk_example_user -P
createdb -O sk_example_user sk_example_db
psql -U sk_example_user -d sk_example_db