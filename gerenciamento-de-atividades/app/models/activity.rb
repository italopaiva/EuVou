class Activity < ActiveRecord::Base
	validates :name, length: { maximum: 100, too_long: "O nome deve ter no máximo %{count} caracteres"}
	validates_presence_of :name, message: "O nome não pode ser vazio"
	validates_presence_of :description, message: "A descrição não pode ser vazia"
	validates :description, length: { maximum: 500, too_long: "A descrição deve ter no máximo %{count} caracteres" }
	validates_presence_of :activity_type, message: "Por favor, selecione o tipo da atividade"

	def self.search(search)
	  where("name LIKE ?", "%#{search}%") 
	end
end
