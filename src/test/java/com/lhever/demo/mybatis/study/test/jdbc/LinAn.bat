
set PGPASSWORD=postgres
\connect LinAn



psql -d LinAn -U postgres -f LinAn\\car_info.sql
psql -d LinAn -U postgres -f LinAn\\community_info.sql
psql -d LinAn -U postgres -f LinAn\\device.sql
psql -d LinAn -U postgres -f LinAn\\doorevent.sql
psql -d LinAn -U postgres -f LinAn\\face.sql
psql -d LinAn -U postgres -f LinAn\\gatherdevice.sql
psql -d LinAn -U postgres -f LinAn\\house_info.sql
psql -d LinAn -U postgres -f LinAn\\image_info.sql
psql -d LinAn -U postgres -f LinAn\\motorvehicle.sql
psql -d LinAn -U postgres -f LinAn\\personhouse_relation.sql
psql -d LinAn -U postgres -f LinAn\\person_info.sql
psql -d LinAn -U postgres -f LinAn\\proberequest.sql
psql -d LinAn -U postgres -f LinAn\\visitorevent.sql




