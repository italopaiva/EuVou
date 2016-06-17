class CreateActivities < ActiveRecord::Migration
  def change
    create_table :activities do |t|
      t.string :name
      t.text :description
      t.string :activity_type

      t.timestamps null: false
    end
  end
end
