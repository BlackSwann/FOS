class CreateTableStates < ActiveRecord::Migration
  def change
    create_table :table_states do |t|
      t.string :name

      t.timestamps null: false
    end
  end
end
