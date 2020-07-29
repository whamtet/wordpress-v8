FROM doctorlove/docker-php-apache-v8js
ADD latest.tar.gz /
RUN rmdir /app && mv /wordpress /app && chown -R application /app
RUN ln -s /config/wp-config.php /app/wp-config.php
