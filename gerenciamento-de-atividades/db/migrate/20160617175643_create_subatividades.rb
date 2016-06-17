class CreateSubatividades < ActiveRecord::Migration
  def change
    create_table :subatividades do |t|
      t.string :name
      t.text :description
      t.integer :duration
      t.string :location
      t.string :progress

      t.timestamps null: false
    end
  end
end
