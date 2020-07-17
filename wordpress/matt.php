<pre>
  <?php
  class Foo {
  var $bar = "bar";
  function bar($what) { echo "I'm a ", $what, "!\n"; }
  function call($callback, $param_arr) {
    return call_user_func_array($callback, $param_arr);
  }
}
function barber($type)
{
    echo "You wanted a $type haircut, no problem\n";
}
function set_v8() {
  global $v8js;
  try {
      $isProd = TRUE;
      $dumpSnapshot = FALSE;
      if ($isProd)
      {
        $v8js = new V8Js('PHP', array(), array(), TRUE, file_get_contents('snapshot'));
      } else {
        $v8js = new V8Js();
      }
      $v8js->foo = new Foo;
      $v8js->globals = $GLOBALS;

      // This prints 'bar'
      // $v8->executeString('print(PHP.foo.$bar, "\n");');
      // This prints "I'm a function!"
      // $v8->executeString('PHP.foo.__call("bar", ["function"]);');

      if(!$isProd)
      {
        $v8js->executeString(file_get_contents('clj_wp.js'));
      }
      if($dumpSnapshot)
      {
        $snapshot = V8Js::createSnapshot(file_get_contents('clj_wp.js'));
//         var_dump($snapshot);
        $myfile = fopen("snapshot", "w");
        fwrite($myfile, $snapshot);
        fclose($myfile);
      }
    } catch (Exception $e) {
      echo $e->getMessage();
    }
}

function v8($func) {
  global $v8js;
  if (!isset($v8js)) {
    set_v8();
  }
  try {
    $v8js->executeString($func);
  } catch (Exception $e) {
    echo $e->getMessage();
  }
}
v8('clj_wp.core.print_globals()');
?>
  </pre>
done
