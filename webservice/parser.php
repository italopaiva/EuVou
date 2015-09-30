<?php
	require_once 'parsecsv.lib.php';
	require_once 'ParserException.php';

	class Parser{

		private $data;

		public function __construct($file_name){

			$csv = new parseCSV($file_name);

			if(!empty($csv)){

				$this->set_data($csv);
			}else{
				throw new ParserException("Não foi possível ler o arquivo informado.");
			}
		}

		private function set_data($data){
			$this->data = $data;
		}

		public function data(){
			return $this->data;
		}
	}
?>