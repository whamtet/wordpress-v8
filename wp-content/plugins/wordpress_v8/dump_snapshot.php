<?php
$snapshot = V8Js::createSnapshot(file_get_contents(__DIR__ . '/clj_wp.js'));
$myfile = fopen("/tmp/snapshot", "w");
fwrite($myfile, $snapshot);
fclose($myfile);
?>
snapshot created!
