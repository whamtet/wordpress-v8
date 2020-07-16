<?php
  $v8 = new V8Js();
  $src = file_get_contents('clj_wp.js');

  class Foo {
  	var $bar = "bar";
  	function bar($what) { echo "I'm a ", $what, "!\n"; }
  }
  try {
    $v8->foo = new Foo;
    $v8->get = $_GET;
    // This prints 'bar'
    // $v8->executeString('print(PHP.foo.$bar, "\n");');
    // This prints "I'm a function!"
    // $v8->executeString('PHP.foo.__call("bar", ["function"]);');

    $v8->executeString($src);
  } catch (Exception $e) {
    echo $e->getMessage();
  }
?>
<br>  finished
