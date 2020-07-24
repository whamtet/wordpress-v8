<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'wordpress' );

/** MySQL database username */
define( 'DB_USER', 'wordpress' );

/** MySQL database password */
define( 'DB_PASSWORD', 'wordpress' );

/** MySQL hostname */
define( 'DB_HOST', 'db' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         't:_%-z^dT>^;<KH#?$j@.3pHB|heVT%tUeaKbtaz:B lx-iRu^l9=|W2O9,esUyx' );
define( 'SECURE_AUTH_KEY',  '_onSUJCP&Pj8zaB~zJ)e ^&/K46*7a7bixeXjP4DilOlO7kX,rW|7d9 l)fr4]Rb' );
define( 'LOGGED_IN_KEY',    '5CArXk_v[T_CU7NN3z36>YpiJT=6{/6I,KYYlW>*iu%{VWSJ!NXzujebs,4=W~JV' );
define( 'NONCE_KEY',        '{;7J_RV+$*l9ADGxnP[D2#WeRnefTSv^xOtIpZnfw;ChN,Pyr=B*aousS~,|rmd*' );
define( 'AUTH_SALT',        'Z~sAIC_T/vQD3v,&yt3v17d&Ta)dOIkTz<L+h^}d1pH-nDLx[<g(=xt(J8Gnno|f' );
define( 'SECURE_AUTH_SALT', '?I s$WS=J[`zkrvD49:[TE%wcU<(v#5mb3Ec-~]{rhh*{0#3$^~(R>/-b-x|}[we' );
define( 'LOGGED_IN_SALT',   'qF{#G:rvVJ4VJqKpQyv:{!FJC<(t4BHxo}k4xMF%LlxzEMNjIA^x1dSh6iYMJkc;' );
define( 'NONCE_SALT',       ']C)vAE0Q?~v_Ti:>qqzy|C>XAyPu,KfwM}(b@3@(ovXBU3;];bT/vG9zpL<GtMbJ' );

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';

