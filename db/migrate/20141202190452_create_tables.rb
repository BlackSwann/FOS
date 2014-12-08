class CreateTables < ActiveRecord::Migration
  def change
    create_table :tables do |t|
      t.integer :number
      t.references :table_state, index: true

      t.timestamps null: false
    end
  end
end
