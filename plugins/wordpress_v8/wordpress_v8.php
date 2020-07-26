<?php
   /*
   Plugin Name: wordpress-v8
   Plugin URI: https://github.com/whamtet/wordpress-v8
   description: Vroom vroom
   Version: 0.1
   Author: whamtet
   Author URI: https://github.com/whamtet
   License: GPL2
   */
class AwesomeHelper {
  function call($callback, $param_arr) {
    return call_user_func_array($callback, $param_arr);
  }
  function log($to_log) {
    syslog(LOG_INFO, $to_log);
  }
  function ev($s) {
    return eval($s);
  }
}
function set_v8() {
  global $v8js;
  try {
      $isProd = $_ENV["ENV"] == 'prod';
      if ($isProd)
      {
        $v8js = new V8Js('PHP', array(), array(), TRUE, file_get_contents('/tmp/snapshot'));
      } else {
        $v8js = new V8Js();
        $v8js->executeString(file_get_contents(__DIR__ . '/clj_wp.js'));
      }
      $v8js->foo = new AwesomeHelper;
      $v8js->globals = $GLOBALS;
      //we need to actually export the symbols back to php
      $v8js->executeString('clj_wp.main.exxx()');
    } catch (Exception $e) {
      error_log($e->getMessage());
    }
}

function v8($func) {
  global $v8js;
  if (!isset($v8js)) {
    set_v8();
  }
  try {
    return $v8js->executeString($func);
  } catch (Exception $e) {
    error_log($e->getMessage());
  }
}
function v8_args($func, $args) {
  global $v8js;
  if (!isset($v8js)) {
    set_v8();
  }
  try {
    $v8js->args = $args;
    return $v8js->executeString($func . '()');
  } catch (Exception $e) {
    error_log($e->getMessage());
  }
}
// need to actually define stuff
set_v8();
?>
