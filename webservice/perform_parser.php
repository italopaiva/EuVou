<?php

require_once("parser_interface.php");

$local_parser = new LocalParser();

$parse_was_done = $local_parser->parse();

if($parse_was_done){
	echo "Parse realizado com sucesso!";
}else{
	echo "Algo deu errado...";
}