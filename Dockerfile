FROM doctorlove/docker-php-apache-v8js
ADD latest.tar.gz /
RUN rmdir /app && mv /wordpress /app
COPY wp-config.php /app/wp-config.php
